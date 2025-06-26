package cn.fyy.data.service.impl;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.data.service.UploadService;
import cn.fyy.minio.service.MinioService;
import encrypt.UUIDUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 上传 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    //------------------------------------------------------------------------------------------------------------------Operate
    /**
     * 文件服务器操作
     */
    @Resource
    private MinioService minioServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 上传文件
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param uploadFile   文件集合
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    @Override
    public Map<String, String> fileUpload(Long managerId, String businessType, MultipartFile[] uploadFile) throws BusinessException {
        Map<String, String> result = new LinkedHashMap<>();
        try {
            if (uploadFile != null) {
                for (MultipartFile file : uploadFile) {
                    if (!file.isEmpty()) {
                        if (StringUtils.hasText(file.getOriginalFilename())) {
                            String suffix = file.getOriginalFilename()
                                    .substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

                            String uploadFileName = UUIDUtil.get32() + "." + suffix;

                            String minioUrl = managerId + "/" + businessType + "/" + uploadFileName;

                            minioServiceImpl.putObject(minioUrl, file.getInputStream());

                            result.put(file.getOriginalFilename(), this.ocrTransfer(minioUrl));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException("上传错误", e);
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    @Override
    public String fileUploadReturnUrl(
            Long managerId,
            String businessType,
            MultipartFile uploadFile
    ) throws BusinessException {
        try {
            if (uploadFile != null) {
                if (StringUtils.hasText(uploadFile.getOriginalFilename())) {
                    String suffix = uploadFile.getOriginalFilename()
                            .substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

                    String uploadFileName = UUIDUtil.get32() + "." + suffix;

                    String minioUrl = managerId + "/" + businessType + "/" + uploadFileName;

                    minioServiceImpl.putObject(minioUrl, uploadFile.getInputStream());

                    return this.ocrTransfer(minioUrl);
                }
            }
        } catch (Exception e) {
            throw new BusinessException("上传错误", e);
        }
        return null;
    }

    /**
     * 查询新上传的图片Url
     *
     * @param minioUrl 图片目录
     * @return 图片Url
     * @throws Exception 错误
     */
    private String ocrTransfer(String minioUrl) throws Exception {
        String url = minioServiceImpl.searchObject(minioUrl);
        if (url == null || url.isEmpty()) {
            Thread.sleep(1000);
            ocrTransfer(minioUrl);
        } else {
            return url;
        }
        return ocrTransfer(minioUrl);
    }
}
