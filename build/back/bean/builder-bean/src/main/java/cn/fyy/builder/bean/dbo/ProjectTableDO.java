package cn.fyy.builder.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 项目表
 *
 * @author fuyy
 */
@Entity
@Table(name = "project_table", schema = "builder")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTableDO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    /**
     * 项目群ID
     */
    @Column(name = "project_group_id")
    private BigInteger projectGroupId;

    /**
     * 数据库设计文件ID
     */
    @Column(name = "data_base_file_id")
    private BigInteger dataBaseFileId;

    /**
     * 所属的库
     */
    @Column(name = "table_schema", length = 200)
    private String tableSchema;

    /**
     * 表名称
     */
    @Column(name = "table_name", length = 200)
    private String tableName;

    /**
     * 表注释
     */
    @Column(name = "table_comment", length = 1000)
    private String tableComment;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

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
    private LocalDateTime updateTime;

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
        if (!(o instanceof ProjectTableDO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(projectGroupId, that.projectGroupId)) {
            return false;
        }
        if (!Objects.equals(dataBaseFileId, that.dataBaseFileId)) {
            return false;
        }
        if (!Objects.equals(tableSchema, that.tableSchema)) {
            return false;
        }
        if (!Objects.equals(tableName, that.tableName)) {
            return false;
        }
        if (!Objects.equals(tableComment, that.tableComment)) {
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
        result = 31 * result + (projectGroupId != null ? projectGroupId.hashCode() : 0);
        result = 31 * result + (dataBaseFileId != null ? dataBaseFileId.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (tableComment != null ? tableComment.hashCode() : 0);
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