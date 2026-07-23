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
 * 角色API接口访问关系;数据表的PO对象
 *
 * @author : fyy
 */
@Entity
@Table(name = "role_api", schema = "authorization")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleApiPO extends BasePO implements Serializable {
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * API_ID
     */
    @Column(name = "api_id")
    private Long apiId;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleApiPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(apiId, that.apiId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(roleId);
        result = 31 * result + Objects.hashCode(apiId);
        return result;
    }
}