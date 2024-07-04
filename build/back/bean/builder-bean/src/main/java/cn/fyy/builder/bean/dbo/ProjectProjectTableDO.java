package cn.fyy.builder.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 * 项目项目表关系
 *
 * @author fuyy
 */
@Entity
@Table(name = "project_project_table", schema = "builder")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProjectTableDO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private BigInteger projectId;

    /**
     * 项目表ID
     */
    @Column(name = "project_table_id")
    private BigInteger projectTableId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "updater_id")
    private BigInteger updaterId;

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
        if (!(o instanceof ProjectProjectTableDO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(projectId, that.projectId)) {
            return false;
        }
        if (!Objects.equals(projectTableId, that.projectTableId)) {
            return false;
        }
        if (!Objects.equals(createTime, that.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, that.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, that.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, that.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, that.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, that.updaterName)) {
            return false;
        }
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (projectTableId != null ? projectTableId.hashCode() : 0);
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