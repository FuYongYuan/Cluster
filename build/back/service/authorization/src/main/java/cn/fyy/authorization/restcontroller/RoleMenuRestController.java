package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.RoleMenuDTO;
import cn.fyy.authorization.service.RoleMenuService;
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

import java.util.List;

/**
 * 菜单角色关系 RestController
 *
 * @author fyy
 */
@Tag(name = "RoleMenuRestController", description = "菜单角色关系 RestController")
@RestController
@RequestMapping(value = "/role/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleMenuRestController extends BaseRestController {
    /**
     * 菜单角色关系 Service
     */
    @Resource
    private RoleMenuService roleMenuServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存菜单角色关系
     *
     * @param dto 菜单角色关系对象
     * @return 菜单角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存菜单角色关系", description = "RoleMenuDTO 菜单角色关系对象",
            parameters = {
                    @Parameter(name = "dto", description = "菜单角色关系对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody RoleMenuDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleMenuServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存菜单角色关系集合
     *
     * @param roleId  角色 ID
     * @param menuIds 菜单 ID 集合
     * @return 菜单角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存菜单角色关系集合", description = "RoleMenuDTO 菜单角色关系对象",
            parameters = {
                    @Parameter(name = "roleId", description = "角色 ID", required = true),
                    @Parameter(name = "menuIds", description = "菜单 ID 集合", required = true)
            }
    )
    @PostMapping(value = "/save/list/{roleId}/{menuIds}")
    public ResultMessage<String> saveList(
            HttpServletRequest request,
            @PathVariable Long roleId,
            @PathVariable String menuIds
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleMenuServiceImpl.saveList(roleId, menuIds, managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 根据角色 ID 菜单角色关系集合
     *
     * @param roleId 角色 ID
     * @return 菜单角色关系集合
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据角色 ID 菜单角色关系集合", description = "RoleMenuDTO 菜单角色关系集合",
            parameters = {
                    @Parameter(name = "roleId", description = "角色 ID", required = true)
            }
    )
    @GetMapping(value = "/query/list/{roleId}")
    public ResultMessage<List<RoleMenuDTO>> queryByRoleIdOrderByUpdateTimeAsc(
            @PathVariable Long roleId
    ) throws BusinessException {
        return new ResultMessage<>(RoleMenuDTO.toDTO(roleMenuServiceImpl.queryByRoleIdOrderByUpdateTimeAsc(roleId)));
    }
}
