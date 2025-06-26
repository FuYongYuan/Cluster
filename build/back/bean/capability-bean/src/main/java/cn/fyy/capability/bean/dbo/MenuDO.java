package cn.fyy.capability.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 菜单
 *
 * @author fuyy
 */
@Entity
@Table(name = "menu", schema = "capability")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuDO implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "menu_name", length = 200)
    private String menuName;

    /**
     * 路径
     */
    @Column(name = "menu_url", length = 500)
    private String menuUrl;

    /**
     * 菜单图标
     */
    @Column(name = "menu_icon", length = 200)
    private String menuIcon;

    /**
     * 页面名称
     */
    @Column(name = "page_name", length = 200)
    private String pageName;

    /**
     * 父级ID
     */
    @Column(name = "parent_id", length = 32)
    private Long parentId;

    /**
     * 是否跳转  (0.否 1.是)
     */
    @Column(name = "is_turn")
    private Integer isTurn;

    /**
     * 是否首页  (0.否 1.是)
     */
    @Column(name = "is_home")
    private Integer isHome;

    /**
     * 排序(按数字从小到大)
     */
    @Column(name = "menu_order")
    private Integer menuOrder;

    /**
     * 备注
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 修改人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Column(name = "state")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuDO menuDO)) {
            return false;
        }

        if (!id.equals(menuDO.id)) {
            return false;
        }
        if (!Objects.equals(menuName, menuDO.menuName)) {
            return false;
        }
        if (!Objects.equals(menuUrl, menuDO.menuUrl)) {
            return false;
        }
        if (!Objects.equals(menuIcon, menuDO.menuIcon)) {
            return false;
        }
        if (!Objects.equals(pageName, menuDO.pageName)) {
            return false;
        }
        if (!Objects.equals(parentId, menuDO.parentId)) {
            return false;
        }
        if (!Objects.equals(isTurn, menuDO.isTurn)) {
            return false;
        }
        if (!Objects.equals(isHome, menuDO.isHome)) {
            return false;
        }
        if (!Objects.equals(menuOrder, menuDO.menuOrder)) {
            return false;
        }
        if (!Objects.equals(remark, menuDO.remark)) {
            return false;
        }
        if (!Objects.equals(createTime, menuDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, menuDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, menuDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, menuDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, menuDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, menuDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, menuDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (menuUrl != null ? menuUrl.hashCode() : 0);
        result = 31 * result + (menuIcon != null ? menuIcon.hashCode() : 0);
        result = 31 * result + (pageName != null ? pageName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (isTurn != null ? isTurn.hashCode() : 0);
        result = 31 * result + (isHome != null ? isHome.hashCode() : 0);
        result = 31 * result + (menuOrder != null ? menuOrder.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updaterName != null ? updaterName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
