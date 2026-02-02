package cn.fyy.data.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.controller.BaseRestController;
import cn.fyy.data.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 数据相关 RestController
 *
 * @author fyy
 */
@Tag(name = "FileRestController", description = "数据 RestController")
@RestController
@RequestMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileRestController extends BaseRestController {
    /**
     * 上传 Service
     */
    @Resource
    private UploadService uploadServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 上传后的获取路径
     */
    @Operation(
            summary = "上传文件", description = "文件访问路径",
            parameters = {
                    @Parameter(name = "businessType", description = "业务类型", required = true),
                    @Parameter(name = "uploadFile", description = "文件内容", required = true)
            }
    )
    @PostMapping(value = "/upload/{businessType}")
    public ResultMessage<Map<String, String>> fileUpload(
            HttpServletRequest request,
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile[] uploadFile
    ) throws BusinessException {
        Map<String, String> fileUrlMap = uploadServiceImpl.fileUpload(jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)), businessType, uploadFile);
        if (!fileUrlMap.isEmpty()) {
            return new ResultMessage<>(fileUrlMap);
        } else {
            return new ResultMessage<>(1, "上传失败！");
        }
    }

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 上传后的获取路径
     */
    @Operation(
            summary = "上传文件", description = "文件访问路径",
            parameters = {
                    @Parameter(name = "businessType", description = "业务类型", required = true),
                    @Parameter(name = "uploadFile", description = "文件内容", required = true)
            }
    )
    @PostMapping(value = "/upload/return/url/{businessType}")
    public ResultMessage<String> fileUploadReturnUrl(
            HttpServletRequest request,
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile uploadFile
    ) throws BusinessException {
        String fileUrl = uploadServiceImpl.fileUploadReturnUrl(jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)), businessType, uploadFile);
        if (StringUtils.hasText(fileUrl)) {
            return new ResultMessage<>(fileUrl);
        } else {
            return new ResultMessage<>(1, "上传失败！");
        }
    }

    /**
     * 上传文件
     *
     * @param businessType 业务类型
     * @param uploadFile   文件
     * @return 文件名称
     */
    @Operation(
            summary = "上传文件", description = "文件名称",
            parameters = {
                    @Parameter(name = "businessType", description = "业务类型", required = true),
                    @Parameter(name = "uploadFile", description = "文件内容", required = true)
            }
    )
    @PostMapping(value = "/upload/return/file/name/{businessType}")
    public ResultMessage<String> fileUploadReturnFileName(
            HttpServletRequest request,
            @PathVariable String businessType,
            @RequestPart("uploadFile") MultipartFile uploadFile
    ) throws BusinessException {
        String fileUrl = uploadServiceImpl.fileUploadReturnFileName(jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)), businessType, uploadFile);
        if (StringUtils.hasText(fileUrl)) {
            return new ResultMessage<>(fileUrl);
        } else {
            return new ResultMessage<>(1, "上传失败！");
        }
    }

    /**
     * 查询文件临时访问地址
     *
     * @param businessType 业务类型
     * @param fileName     文件名称
     * @param duration     签名有效期(分钟)
     * @return 文件临时访问地址
     */
    @Operation(
            summary = "查询文件临时访问地址", description = "文件临时访问地址",
            parameters = {
                    @Parameter(name = "businessType", description = "业务类型", required = true),
                    @Parameter(name = "fileName", description = "文件名称", required = true),
                    @Parameter(name = "duration", description = "签名有效期(分钟)", required = true)
            }
    )
    @GetMapping(value = "/get/file/temporary/url/{businessType}/{fileName}/{duration}")
    public ResultMessage<String> getFileTemporaryUrl(
            HttpServletRequest request,
            @PathVariable String businessType,
            @PathVariable String fileName,
            @PathVariable Long duration
    ) throws BusinessException {
        String fileUrl = uploadServiceImpl.getFileTemporaryUrl(jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)), businessType, fileName, duration);
        if (StringUtils.hasText(fileUrl)) {
            return new ResultMessage<>(fileUrl);
        } else {
            return new ResultMessage<>(1, "查询文件临时访问地址失败！");
        }
    }
}
