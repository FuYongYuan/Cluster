package cn.fyy.data.service;

import cn.fyy.common.bean.bo.BusinessException;
import org.springframework.web.multipart.MultipartFile;

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
            Long managerId,
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
            Long managerId,
            String businessType,
            MultipartFile uploadFile
    ) throws BusinessException;

    /**
     * 上传文件
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件名称
     * @throws BusinessException 错误
     */
    String fileUploadReturnFileName(
            Long managerId,
            String businessType,
            MultipartFile uploadFile
    ) throws BusinessException;


    /**
     * 查询文件临时访问地址
     *
     * @param managerId    上传人Id
     * @param businessType 业务类型
     * @param fileName     文件名称
     * @param duration     签名有效期(分钟)
     * @return 文件访问地址
     * @throws BusinessException 错误
     */
    String getFileTemporaryUrl(
            Long managerId,
            String businessType,
            String fileName,
            Long duration
    ) throws BusinessException;
}
