package cn.fyy.jpa.bean.po;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 基础 PO
 *
 * @author fyy
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePO implements Serializable {
    /**
     * 主键 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人 ID
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
     * 更新人 ID
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

    /**
     * 数据版本号 - 用于乐观锁控制
     * 初始化为0，每次更新时自动递增
     */
    @Version
    @Column(name = "version")
    private Long version = 0L;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BasePO that)) {
            return false;
        }

        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(creatorName, that.creatorName) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updaterId, that.updaterId) &&
                Objects.equals(updaterName, that.updaterName) &&
                Objects.equals(state, that.state) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Objects.hashCode(createTime);
        result = 31 * result + Objects.hashCode(creatorId);
        result = 31 * result + Objects.hashCode(creatorName);
        result = 31 * result + Objects.hashCode(updateTime);
        result = 31 * result + Objects.hashCode(updaterId);
        result = 31 * result + Objects.hashCode(updaterName);
        result = 31 * result + Objects.hashCode(state);
        result = 31 * result + Objects.hashCode(version);
        return result;
    }

}
