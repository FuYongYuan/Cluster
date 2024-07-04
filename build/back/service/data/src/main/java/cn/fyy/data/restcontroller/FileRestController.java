package cn.fyy.data.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import cn.fyy.data.service.UploadService;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 数据相关
 *
 * @author fuyy
 */
@Tag(name = "FileRestController", description = "数据 RestController")
@RestController
@RequestMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileRestController {
    /**
     * 上传 Service
     */
    @Resource
    private UploadService uploadServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * JWT 配置
     */
    @Resource
    private JwtProperties jwtProperties;

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
            @PathVariable("businessType") String businessType,
            @RequestPart("uploadFile") MultipartFile[] uploadFile
    ) throws BusinessException {
        Map<String, String> fileUrlMap = uploadServiceImpl.fileUpload(jwtTokenUtil.getManagerIdFromToken(ConstantService.getRequestToken(jwtProperties)), businessType, uploadFile);
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
    @PostMapping(value = "/upload/get/url/{businessType}")
    public ResultMessage<String> fileUploadReturnUrl(
            @PathVariable("businessType") String businessType,
            @RequestPart("uploadFile") MultipartFile uploadFile
    ) throws BusinessException {
        String fileUrl = uploadServiceImpl.fileUploadReturnUrl(jwtTokenUtil.getManagerIdFromToken(ConstantService.getRequestToken(jwtProperties)), businessType, uploadFile);
        if (StringUtils.hasText(fileUrl)) {
            return new ResultMessage<>(fileUrl);
        } else {
            return new ResultMessage<>(1, "上传失败！");
        }
    }
}
