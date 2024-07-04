package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.RoleMenuDTO;
import cn.fyy.authorization.service.RoleMenuService;
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
import java.util.List;

/**
 * 菜单角色关系 RestController
 *
 * @author fuyy
 */
@Tag(name = "RoleMenuRestController", description = "菜单角色关系 RestController")
@RestController
@RequestMapping(value = "/role/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleMenuRestController {
    /**
     * 菜单角色关系 Service
     */
    @Resource
    private RoleMenuService roleMenuServiceImpl;

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
            @RequestBody RoleMenuDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        return roleMenuServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存菜单角色关系集合
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return 菜单角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存菜单角色关系集合", description = "RoleMenuDTO 菜单角色关系对象",
            parameters = {
                    @Parameter(name = "roleId", description = "角色ID", required = true),
                    @Parameter(name = "menuIds", description = "菜单ID集合", required = true)
            }
    )
    @PostMapping(value = "/save/list/{roleId}/{menuIds}")
    public ResultMessage<String> saveList(
            @PathVariable("roleId") BigInteger roleId,
            @PathVariable("menuIds") String menuIds
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        return roleMenuServiceImpl.saveList(roleId, menuIds, managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 根据角色ID菜单角色关系集合
     *
     * @param roleId 角色ID
     * @return 菜单角色关系集合
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据角色ID菜单角色关系集合", description = "RoleMenuDTO 菜单角色关系集合",
            parameters = {
                    @Parameter(name = "roleId", description = "角色ID", required = true)
            }
    )
    @GetMapping(value = "/query/list/{roleId}")
    public ResultMessage<List<RoleMenuDTO>> queryByRoleIdOrderByUpdateTimeAsc(
            @PathVariable("roleId") BigInteger roleId
    ) throws BusinessException {
        return new ResultMessage<>(RoleMenuDTO.toDTO(roleMenuServiceImpl.queryByRoleIdOrderByUpdateTimeAsc(roleId)));
    }
}
