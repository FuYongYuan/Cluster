package cn.fyy.authorization.feign.client.data;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 数据相关 RestController
 *
 * @author fyy
 */
@FeignClient(name = "data", contextId = "File", path = "/file")
public interface FileFeignClient {

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 上传后的获取路径
     */
    @PostMapping(value = "/upload/{businessType}")
    ResultMessage<Map<String, String>> fileUpload(
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile[] uploadFile
    ) throws BusinessException;

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 上传后的获取路径
     */
    @PostMapping(value = "/upload/return/url/{businessType}")
    ResultMessage<String> fileUploadReturnUrl(
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile uploadFile
    ) throws BusinessException;

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件名称
     */
    @PostMapping(value = "/upload/return/file/name/{businessType}")
    ResultMessage<String> fileUploadReturnFileName(
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile uploadFile
    ) throws BusinessException;

    /**
     * 查询文件临时访问地址
     *
     * @param businessType 业务类型
     * @param fileName     文件名称
     * @param duration     签名有效期(分钟)
     * @return 文件临时访问地址
     */
    @GetMapping(value = "/get/file/temporary/url/{businessType}/{fileName}/{duration}")
    ResultMessage<String> getFileTemporaryUrl(
            @PathVariable String businessType,
            @PathVariable String fileName,
            @PathVariable Long duration
    ) throws BusinessException;

    /**
     * 查询文件临时访问地址
     *
     * @param businessType   业务类型
     * @param fileName       文件名称
     * @param duration       签名有效期(分钟)
     * @param authentication 鉴权串
     * @return 文件临时访问地址
     */
    @GetMapping(value = "/feign/get/file/temporary/url/{managerId}/{businessType}/{fileName}/{duration}/{authentication}")
    ResultMessage<String> feignGetFileTemporaryUrl(
            @PathVariable Long managerId,
            @PathVariable String businessType,
            @PathVariable String fileName,
            @PathVariable Long duration,
            @PathVariable String authentication
    ) throws BusinessException;
}
