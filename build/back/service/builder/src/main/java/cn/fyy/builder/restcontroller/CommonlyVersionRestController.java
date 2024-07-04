package cn.fyy.builder.restcontroller;

import cn.fyy.builder.bean.dto.CommonlyVersionDTO;
import cn.fyy.builder.service.CommonlyVersionService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import cn.fyy.jwt.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * 常用版本 RestController
 *
 * @author fuyy
 */
@Tag(name = "CommonlyVersionRestController", description = "常用版本 RestController")
@RestController
@RequestMapping(value = "/commonly/version", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonlyVersionRestController {
    /**
     * 常用版本 Service
     */
    @Resource
    private CommonlyVersionService commonlyVersionServiceImpl;

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
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键删除 主键可以是多个用,分割", description = "受影响行数",
            parameters = {
                    @Parameter(name = "ids", description = "删除ID", required = true)
            }
    )
    @DeleteMapping(value = "/delete/{ids}")
    public ResultMessage<Integer> delete(
            @PathVariable("ids") String ids
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        int i = commonlyVersionServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
        if (i > 0) {
            return new ResultMessage<>(i);
        } else {
            return new ResultMessage<>(1, "删除失败!");
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 常用版本对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TCommonlyVersionDTO 常用版本对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<CommonlyVersionDTO> get(
            @PathVariable("id") BigInteger id
    ) throws BusinessException {
        return new ResultMessage<>(CommonlyVersionDTO.toDTO(commonlyVersionServiceImpl.getById(id)));
    }

    /**
     * 保存常用版本
     *
     * @param dto 常用版本对象
     * @return 常用版本对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存常用版本", description = "TCommonlyVersionDTO 常用版本对象",
            parameters = {
                    @Parameter(name = "dto", description = "常用版本对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            @RequestBody CommonlyVersionDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        return commonlyVersionServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 根据用户主键查询
     *
     * @return 常用版本对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据用户主键查询", description = "TCommonlyVersionDTO 常用版本对象"
    )
    @GetMapping(value = "/get/login")
    public ResultMessage<CommonlyVersionDTO> getBySecurityMessage(
    ) throws BusinessException {
        return new ResultMessage<>(CommonlyVersionDTO.toDTO(commonlyVersionServiceImpl.getByManagerId(jwtTokenUtil.getManagerIdFromToken(ConstantService.getRequestToken(jwtProperties)))));
    }
}
