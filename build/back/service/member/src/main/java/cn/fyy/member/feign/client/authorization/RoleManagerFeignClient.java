package cn.fyy.member.feign.client.authorization;

import cn.fyy.authorization.bean.dto.RoleManagerDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 角色管理员关系 RestController
 *
 * @author fuyy
 */
@FeignClient(name = "authorization", contextId = "RoleManager", path = "/roleManager")
public interface RoleManagerFeignClient {
    /**
     * 保存角色管理员关系
     *
     * @param dto 角色管理员关系对象
     * @return 角色管理员关系对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody RoleManagerDTO dto
    ) throws BusinessException;

    /**
     * 保存角色管理员关系集合
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return 角色管理员关系对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save/list/{roleId}/{menuIds}")
    ResultMessage<String> saveList(
            @PathVariable("roleId") Long roleId,
            @PathVariable("menuIds") String menuIds
    ) throws BusinessException;

}
