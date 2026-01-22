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
 * 角色管理员关系
 *
 * @author fuyy
 */
@Entity
@Table(name = "role_manager", schema = "authorization")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleManagerPO extends BasePO implements Serializable {

    /**
     * 管理员 ID
     */
    @Column(name = "manager_id")
    private Long managerId;

    /**
     * 角色 ID
     */
    @Column(name = "role_id")
    private Long roleId;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleManagerPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(managerId);
        result = 31 * result + Objects.hashCode(roleId);
        return result;
    }
}
