package cn.fyy.authorization.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * 菜单角色关系
 *
 * @author fuyy
 */
@Entity
@Table(name = "role_menu", schema = "authorization")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuPO extends BasePO implements Serializable {

    /**
     * 角色 ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单 ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleMenuPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(roleId);
        result = 31 * result + Objects.hashCode(menuId);
        return result;
    }
}
