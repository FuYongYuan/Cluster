package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.RoleApiDTO;
import cn.fyy.authorization.service.RoleApiService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.controller.BaseRestController;
import cn.fyy.jwt.bean.bo.ManagerMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 角色API接口访问关系 RestController
 *
 * @author fyy
 */
@Tag(name = "RoleApiRestController", description = "角色API接口访问关系 RestController")
@RestController
@RequestMapping(value = "/role/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleApiRestController extends BaseRestController {
    /**
     * 角色API关系 Service
     */
    @Resource
    private RoleApiService roleApiServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存角色API关系
     *
     * @param dto 角色API关系对象
     * @return 角色API关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存角色API关系", description = "RoleApiDTO 角色API关系对象",
            parameters = {
                    @Parameter(name = "dto", description = "角色API关系对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody RoleApiDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleApiServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存角色API关系集合
     *
     * @param roleId 角色 ID
     * @param apiIds API ID 集合
     * @return 角色API关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存角色API关系集合", description = "RoleApiDTO 角色API关系对象",
            parameters = {
                    @Parameter(name = "roleId", description = "角色 ID", required = true),
                    @Parameter(name = "apiIds", description = "API ID 集合", required = true)
            }
    )
    @PostMapping(value = "/save/list/{roleId}/{apiIds}")
    public ResultMessage<String> saveList(
            HttpServletRequest request,
            @PathVariable Long roleId,
            @PathVariable String apiIds
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleApiServiceImpl.saveList(roleId, apiIds, managerMessage.getManagerId(), managerMessage.getManagerName());
    }

}
