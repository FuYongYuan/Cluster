package cn.fyy.data.service;

import cn.fyy.common.bean.bo.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Map;

/**
 * 上传 Service
 *
 * @author fuyy
 */
public interface UploadService {
    /**
     * 上传文件
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param uploadFile   文件集合
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    Map<String, String> fileUpload(
            BigInteger managerId,
            String businessType,
            MultipartFile[] uploadFile
    ) throws BusinessException;

    /**
     * 上传文件
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    String fileUploadReturnUrl(
            BigInteger managerId,
            String businessType,
            MultipartFile uploadFile
    ) throws BusinessException;
}
