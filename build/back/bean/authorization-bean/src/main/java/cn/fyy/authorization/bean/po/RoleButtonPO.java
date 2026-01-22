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
 * 按钮角色关系
 *
 * @author fuyy
 */
@Entity
@Table(name = "role_button", schema = "authorization")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleButtonPO extends BasePO implements Serializable {

    /**
     * 角色 ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 按钮 ID
     */
    @Column(name = "button_id")
    private Long buttonId;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleButtonPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(buttonId, that.buttonId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(roleId);
        result = 31 * result + Objects.hashCode(buttonId);
        return result;
    }
}
