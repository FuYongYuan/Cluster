package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.RoleManagerDTO;
import cn.fyy.authorization.service.RoleManagerService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理员关系 RestController
 *
 * @author fuyy
 */
@Tag(name = "RoleManagerRestController", description = "角色管理员关系 RestController")
@RestController
@RequestMapping(value = "/role/manager", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleManagerRestController {
    /**
     * 角色管理员关系 Service
     */
    @Resource
    private RoleManagerService roleManagerServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;


    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存角色管理员关系
     *
     * @param dto 角色管理员关系对象
     * @return 角色管理员关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存角色管理员关系", description = "RoleManagerDTO 角色管理员关系对象",
            parameters = {
                    @Parameter(name = "dto", description = "角色管理员关系对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody RoleManagerDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return roleManagerServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存角色管理员关系集合
     *
     * @param managerId 管理员ID
     * @param roleIds   角色ID集合
     * @return 角色管理员关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存角色管理员关系集合", description = "RoleManagerDTO 角色管理员关系对象",
            parameters = {
                    @Parameter(name = "managerId", description = "管理员ID", required = true),
                    @Parameter(name = "roleIds", description = "角色ID集合", required = true)
            }
    )
    @PostMapping(value = "/save/list/{managerId}/{roleIds}")
    public ResultMessage<String> saveList(
            HttpServletRequest request,
            @PathVariable("managerId") Long managerId,
            @PathVariable("roleIds") String roleIds
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return roleManagerServiceImpl.saveList(managerId, roleIds, managerMessage.getManagerId(), managerMessage.getManagerName());
    }

}
