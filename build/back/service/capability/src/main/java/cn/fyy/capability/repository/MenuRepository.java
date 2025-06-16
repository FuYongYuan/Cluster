package cn.fyy.capability.repository;

import cn.fyy.capability.bean.dbo.MenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单 Repository
 *
 * @author fuyy
 */
@Repository
public interface MenuRepository extends JpaRepository<MenuDO, BigInteger>, JpaSpecificationExecutor<MenuDO> {

    /**
     * 根据ids保存状态
     *
     * @param ids                主键ID(可用,分割传入多个)
     * @param state              状态
     * @param currentManagerId   当前登陆管理员id
     * @param currentManagerName 当前登陆管理员名称
     * @param updateTime         当前时间
     * @return 受影响行数
     */
    @Modifying
    @Query(value = "UPDATE MenuDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Integer state, BigInteger currentManagerId, String currentManagerName, LocalDateTime updateTime, List<BigInteger> ids);

    /**
     * 根据菜单ID集合查询菜单
     *
     * @param menuIdList 菜单ID集合
     * @param parentId   父级id
     * @param state      状态
     * @return 能够使用的菜单列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM menu " +
                    "WHERE id IN ?1 " +
                    "AND IF(?2!='',parent_id = ?2,parent_id IS NULL) " +
                    "AND state = ?3 " +
                    "ORDER BY menu_order"
    )
    List<MenuDO> queryHierarchyMenuByMenuIdListAndParentIdAndState(List<BigInteger> menuIdList, BigInteger parentId, int state);

    /**
     * 根据菜单ID集合查询菜单
     *
     * @param menuIdList 菜单ID集合
     * @param state      状态
     * @return 能够使用的菜单列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM menu " +
                    "WHERE id IN ?1 " +
                    "AND state = ?2 " +
                    "ORDER BY menu_order"
    )
    List<MenuDO> queryHierarchyMenuByMenuIdListAndState(List<BigInteger> menuIdList, int state);

    /**
     * 查询所有可用菜单
     *
     * @return 能够使用的菜单列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM menu " +
                    "WHERE state = 0"
    )
    List<MenuDO> queryAll();

    /**
     * 查询所有可用父级菜单
     *
     * @return 能够使用的菜单列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM menu " +
                    "WHERE parent_id IS NULL " +
                    "AND state = 0"
    )
    List<MenuDO> queryAllParent();
}
