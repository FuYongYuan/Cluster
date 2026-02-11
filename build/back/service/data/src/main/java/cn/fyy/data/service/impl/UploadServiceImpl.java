package cn.fyy.data.service.impl;

import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.data.config.properties.AesProperties;
import cn.fyy.data.service.UploadService;
import cn.fyy.rustfs.service.RustfsService;
import encrypt.AesUtil;
import encrypt.UUIDUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 上传 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    //------------------------------------------------------------------------------------------------------------------Operate
    /**
     * 文件服务器操作
     */
    @Resource
    private RustfsService rustfsServiceImpl;
    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes 加密信息
     */
    @Resource
    private AesProperties aesProperties;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 上传文件
     *
     * @param managerId    上传人 ID
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

                            String rustfsUrl = ConstantParameter.UPLOAD_USER_DATA_PATH_NAME + "/" + managerId + "/" + businessType + "/" + uploadFileName;

                            rustfsServiceImpl.putObject(rustfsUrl, file.getInputStream(), file.getSize());

                            result.put(file.getOriginalFilename(), this.ocrTransfer(rustfsUrl));
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
     * @param managerId    上传人 ID
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

                    String rustfsUrl = ConstantParameter.UPLOAD_USER_DATA_PATH_NAME + "/" + managerId + "/" + businessType + "/" + uploadFileName;

                    rustfsServiceImpl.putObject(rustfsUrl, uploadFile.getInputStream(), uploadFile.getSize());

                    return this.ocrTransfer(rustfsUrl);
                }
            }
        } catch (Exception e) {
            throw new BusinessException("上传错误", e);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param managerId    上传人 ID
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件名称
     * @throws BusinessException 错误
     */
    @Override
    public String fileUploadReturnFileName(
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

                    String rustfsUrl = ConstantParameter.UPLOAD_USER_DATA_PATH_NAME + "/" + managerId + "/" + businessType + "/" + uploadFileName;

                    rustfsServiceImpl.putObject(rustfsUrl, uploadFile.getInputStream(), uploadFile.getSize());

                    return uploadFileName;
                }
            }
        } catch (Exception e) {
            throw new BusinessException("上传错误", e);
        }
        return null;
    }

    /**
     * 查询文件临时访问地址
     *
     * @param managerId    上传人 ID
     * @param businessType 业务类型
     * @param fileName     文件名称
     * @param duration     签名有效期(分钟)
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    @Override
    public String getFileTemporaryUrl(Long managerId, String businessType, String fileName, Long duration) throws BusinessException {
        String rustfsUrl = ConstantParameter.UPLOAD_USER_DATA_PATH_NAME + "/" + managerId + "/" + businessType + "/" + fileName;
        return rustfsServiceImpl.presignGetObject(rustfsUrl, Duration.ofMinutes(duration));
    }

    /**
     * 查询文件临时访问地址
     *
     * @param managerId      上传人 ID
     * @param businessType   业务类型
     * @param fileName       文件名称
     * @param duration       签名有效期(分钟)
     * @param authentication 鉴权串
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<String> feignGetFileTemporaryUrl(Long managerId, String businessType, String fileName, Long duration, String authentication) throws BusinessException {
        String encryptString = managerId + businessType + fileName + duration;
        String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
        String decrypt = AesUtil.decryptString(authentication, aesProperties.getAesKey());
        if (encrypt.equals(authentication) && decrypt.equals(encryptString)) {
            String rustfsUrl = ConstantParameter.UPLOAD_USER_DATA_PATH_NAME + "/" + managerId + "/" + businessType + "/" + fileName;
            return new ResultMessage<>(rustfsServiceImpl.presignGetObject(rustfsUrl, Duration.ofMinutes(duration)));
        } else {
            return new ResultMessage<>(1, "试图篡改信息拒绝请求！");
        }
    }


    /**
     * 查询新上传的图片 Url
     *
     * @param rustfsUrl 图片目录
     * @return 图片 Url
     * @throws Exception 错误
     */
    private String ocrTransfer(String rustfsUrl) throws Exception {
        String url = rustfsServiceImpl.presignGetObject(rustfsUrl, Duration.ofHours(1));
        if (url == null || url.isEmpty()) {
            Thread.sleep(1000);
            ocrTransfer(rustfsUrl);
        } else {
            return url;
        }
        return ocrTransfer(rustfsUrl);
    }
}
