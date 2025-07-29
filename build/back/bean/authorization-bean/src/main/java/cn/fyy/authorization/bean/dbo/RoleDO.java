package cn.fyy.authorization.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDO implements Serializable {

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
    @Column(name = "role_name", length = 200)
    private String roleName;

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
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Column(name = "state")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDO roleDO)) {
            return false;
        }

        if (!id.equals(roleDO.id)) {
            return false;
        }
        if (!Objects.equals(roleName, roleDO.roleName)) {
            return false;
        }
        if (!Objects.equals(remark, roleDO.remark)) {
            return false;
        }
        if (!Objects.equals(createTime, roleDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, roleDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, roleDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, roleDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, roleDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, roleDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, roleDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
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
