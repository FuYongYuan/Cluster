package cn.fyy.capability.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuPO extends BasePO implements Serializable {

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
     * 父级 ID
     */
    @Column(name = "parent_id", length = 32)
    private Long parentId;

    /**
     * 是否跳转;（0.否、1.是）
     */
    @Column(name = "is_turn")
    private Byte isTurn;

    /**
     * 是否首页;（0.否、1.是）
     */
    @Column(name = "is_home")
    private Byte isHome;

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

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(menuName, that.menuName) &&
                Objects.equals(menuUrl, that.menuUrl) &&
                Objects.equals(menuIcon, that.menuIcon) &&
                Objects.equals(pageName, that.pageName) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(isTurn, that.isTurn) &&
                Objects.equals(isHome, that.isHome) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(menuName);
        result = 31 * result + Objects.hashCode(menuUrl);
        result = 31 * result + Objects.hashCode(menuIcon);
        result = 31 * result + Objects.hashCode(pageName);
        result = 31 * result + Objects.hashCode(parentId);
        result = 31 * result + Objects.hashCode(isTurn);
        result = 31 * result + Objects.hashCode(isHome);
        result = 31 * result + Objects.hashCode(menuOrder);
        result = 31 * result + Objects.hashCode(remark);
        return result;
    }
}
