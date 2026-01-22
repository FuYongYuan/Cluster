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
 * 角色
 *
 * @author fuyy
 */
@Entity
@Table(name = "role", schema = "authorization")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RolePO extends BasePO implements Serializable {

    /**
     * 名称
     */
    @Column(name = "role_name", length = 200)
    private String roleName;

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
        if (!(o instanceof RolePO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(roleName, that.roleName) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(roleName);
        result = 31 * result + Objects.hashCode(remark);
        return result;
    }
}
