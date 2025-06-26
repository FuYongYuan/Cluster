package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.bo.RoleBO;
import cn.fyy.authorization.bean.dto.RoleDTO;
import cn.fyy.authorization.service.RoleService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色 RestController
 *
 * @author fuyy
 */
@Tag(name = "RoleRestController", description = "角色 RestController")
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleRestController {
    /**
     * 角色 Service
     */
    @Resource
    private RoleService roleServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据名称查询角色列表
     *
     * @param roleName 角色名称
     * @return TRoleDTO 角色对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据名称查询角色列表", description = "TRoleDTO 角色对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "pageSort", description = "排序"),
                    @Parameter(name = "roleName", description = "角色名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    public ResultMessage<PageImpl<RoleDTO>> queryPageByRoleNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "pageSort", required = false) String pageSort,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        Page<RoleBO> boPage = roleServiceImpl.queryPageByRoleNameAndState(
                currentPage,
                eachPageSize,
                pageSort,
                roleName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(RoleDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
    }

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
            HttpServletRequest request,
            @PathVariable("ids") String ids
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        int i = roleServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
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
     * @return 角色对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TRoleDTO 角色对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<RoleDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(RoleDTO.toDTO(roleServiceImpl.getById(id)));
    }

    /**
     * 保存角色
     *
     * @param dto 角色对象
     * @return 角色对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存角色", description = "TRoleDTO 角色对象",
            parameters = {
                    @Parameter(name = "dto", description = "角色对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody RoleDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return roleServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 查询角色列表
     *
     * @return TRoleDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "查询全部角色列表", description = "TRoleDTO 角色对象集合"
    )
    @GetMapping(value = "/query/all")
    public ResultMessage<List<RoleDTO>> queryAll(
    ) throws BusinessException {
        return new ResultMessage<>(RoleDTO.toDTO(roleServiceImpl.queryAll()));
    }

    /**
     * 根据用户查询现有角色关系列表
     *
     * @param managerId 管理员主键ID
     * @return 用户角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据管理员ID查询拥有的角色列表", description = "TRoleDTO 角色对象集合",
            parameters = {
                    @Parameter(name = "managerId", description = "管理员ID", required = true)
            }
    )
    @GetMapping(value = "/query/manager/have/role/{managerId}")
    public ResultMessage<List<RoleDTO>> queryManagerHaveRoleByManagerId(
            @PathVariable("managerId") Long managerId
    ) throws BusinessException {
        return new ResultMessage<>(RoleDTO.toDTO(roleServiceImpl.queryManagerHaveRoleByManagerId(managerId)));
    }
}
