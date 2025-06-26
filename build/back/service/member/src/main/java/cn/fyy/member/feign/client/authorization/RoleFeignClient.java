package cn.fyy.member.feign.client.authorization;

import cn.fyy.authorization.bean.dto.RoleDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色 RestController
 *
 * @author fuyy
 */
@FeignClient(name = "authorization", contextId = "Role", path = "/role")
public interface RoleFeignClient {

    /**
     * 根据名称查询角色列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param roleName     角色名称
     * @param state        状态
     * @return TRoleDTO 角色对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    ResultMessage<PageImpl<RoleDTO>> queryPageByRoleNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @DeleteMapping(value = "/delete/{ids}")
    ResultMessage<Integer> delete(
            @PathVariable("ids") String ids
    ) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 角色对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/{id}")
    ResultMessage<RoleDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException;

    /**
     * 保存角色
     *
     * @param dto 角色对象
     * @return 角色对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody RoleDTO dto
    ) throws BusinessException;

    /**
     * 查询角色列表
     *
     * @return TRoleDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/all")
    ResultMessage<List<RoleDTO>> queryAll(
    ) throws BusinessException;

    /**
     * 根据用户查询现有角色关系列表
     *
     * @param managerId 管理员主键ID
     * @return 用户角色关系对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/manager/have/role/{managerId}")
    ResultMessage<List<RoleDTO>> queryManagerHaveRoleByManagerId(
            @PathVariable("managerId") Long managerId
    ) throws BusinessException;
}
