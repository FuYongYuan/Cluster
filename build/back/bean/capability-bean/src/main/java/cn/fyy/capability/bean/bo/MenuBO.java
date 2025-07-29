package cn.fyy.capability.bean.bo;

import cn.fyy.capability.bean.dbo.MenuDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 路径
     */
    private String menuUrl;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 是否跳转;（0.否、1.是）
     */
    private Byte isTurn;

    /**
     * 是否首页;（0.否、1.是）
     */
    private Byte isHome;

    /**
     * 排序(按数字从小到大)
     */
    private Integer menuOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 更新人名称
     */
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    private Byte state;

    /**
     * 父级对象
     */
    private MenuBO parent;

    /**
     * 子级菜单
     */
    private List<MenuBO> sub;

    //------------------------------------------------------------------------------------------------------------------构造

    /**
     * 构造
     */
    public MenuBO(Long id, String menuName, String menuUrl, String menuIcon, String pageName, Byte isTurn, Byte isHome, Integer menuOrder, String remark, LocalDateTime createTime, Long creatorId, String creatorName, LocalDateTime updateTime, Long updaterId, String updaterName, Byte state
            , Long parentId, String parentMenuName, String parentMenuUrl, String parentMenuIcon, String parentPageName, Long parentParentId, Byte parentIsTurn, Byte parentIsHome, Integer parentMenuOrder, String parentRemark, LocalDateTime parentCreateTime, Long parentCreatorId, String parentCreatorName, LocalDateTime parentUpdateTime, Long parentUpdaterId, String parentUpdaterName, Byte parentState) {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.pageName = pageName;
        this.parentId = parentId;
        this.isTurn = isTurn;
        this.isHome = isHome;
        this.menuOrder = menuOrder;
        this.remark = remark;
        this.createTime = createTime;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.updateTime = updateTime;
        this.updaterId = updaterId;
        this.updaterName = updaterName;
        this.state = state;
        this.parent = MenuBO.builder()
                .id(parentId)
                .menuName(parentMenuName)
                .menuUrl(parentMenuUrl)
                .menuIcon(parentMenuIcon)
                .pageName(parentPageName)
                .parentId(parentParentId)
                .isTurn(parentIsTurn)
                .isHome(parentIsHome)
                .menuOrder(parentMenuOrder)
                .remark(parentRemark)
                .createTime(parentCreateTime)
                .creatorId(parentCreatorId)
                .creatorName(parentCreatorName)
                .updateTime(parentUpdateTime)
                .updaterId(parentUpdaterId)
                .updaterName(parentUpdaterName)
                .state(parentState)
                .build();

    }
    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 菜单 DO 转换 BO
     */
    public static MenuBO toBO(MenuDO dbo) {
        if (dbo != null) {
            return MenuBO.builder()
                    .id(dbo.getId())
                    .menuName(dbo.getMenuName())
                    .menuUrl(dbo.getMenuUrl())
                    .menuIcon(dbo.getMenuIcon())
                    .pageName(dbo.getPageName())
                    .parentId(dbo.getParentId())
                    .isTurn(dbo.getIsTurn())
                    .isHome(dbo.getIsHome())
                    .menuOrder(dbo.getMenuOrder())
                    .remark(dbo.getRemark())
                    .createTime(dbo.getCreateTime())
                    .creatorId(dbo.getCreatorId())
                    .creatorName(dbo.getCreatorName())
                    .updateTime(dbo.getUpdateTime())
                    .updaterId(dbo.getUpdaterId())
                    .updaterName(dbo.getUpdaterName())
                    .state(dbo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 菜单 DO 数组 转换 BO List
     */
    public static List<MenuBO> toBO(MenuDO[] dbos) {
        if (dbos != null) {
            List<MenuBO> bos = new ArrayList<>();
            for (MenuDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 DO List 转换 BO List
     */
    public static List<MenuBO> toBO(List<MenuDO> dbos) {
        if (dbos != null) {
            List<MenuBO> bos = new ArrayList<>();
            for (MenuDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 BO 转换 DO
     */
    public static MenuDO toDO(MenuBO bo) {
        if (bo != null) {
            return MenuDO.builder()
                    .id(bo.getId())
                    .menuName(bo.getMenuName())
                    .menuUrl(bo.getMenuUrl())
                    .menuIcon(bo.getMenuIcon())
                    .pageName(bo.getPageName())
                    .parentId(bo.getParentId())
                    .isTurn(bo.getIsTurn())
                    .isHome(bo.getIsHome())
                    .menuOrder(bo.getMenuOrder())
                    .remark(bo.getRemark())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 菜单 BO List 转换 DO List
     */
    public static List<MenuDO> toDO(List<MenuBO> bos) {
        if (bos != null) {
            List<MenuDO> dbos = new ArrayList<>();
            for (MenuBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 BO 转换 DO
     */
    public MenuDO toDO() {
        return MenuDO.builder()
                .id(this.getId())
                .menuName(this.getMenuName())
                .menuUrl(this.getMenuUrl())
                .menuIcon(this.getMenuIcon())
                .pageName(this.getPageName())
                .parentId(this.getParentId())
                .isTurn(this.getIsTurn())
                .isHome(this.getIsHome())
                .menuOrder(this.getMenuOrder())
                .remark(this.getRemark())
                .createTime(this.getCreateTime())
                .creatorId(this.getCreatorId())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterId(this.getUpdaterId())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .build();
    }

}
