package cn.fyy.authorization.bean.bo;

import cn.fyy.capability.bean.dto.MenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限前置准备缓存
 *
 * @author fyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityPrepareCacheBO {
    /**
     * 管理员ID
     */
    private Long managerId;
    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;
    /**
     * 菜单ID列表
     */
    private List<Long> menuIdList;
    /**
     * 菜单列表
     */
    private List<MenuDTO> menuList;
}