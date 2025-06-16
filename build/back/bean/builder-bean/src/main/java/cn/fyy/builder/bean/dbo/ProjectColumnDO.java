package cn.fyy.builder.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 项目表字段
 *
 * @author fuyy
 */
@Entity
@Table(name = "project_column", schema = "builder")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnDO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    /**
     * 项目表ID
     */
    @Column(name = "project_table_id")
    private BigInteger projectTableId;

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
     * 字段名
     */
    @Column(name = "column_name", length = 200)
    private String columnName;

    /**
     * 是否可以为空
     */
    @Column(name = "is_nullable", length = 5)
    private String isNullable;

    /**
     * 数据类型(不包括长度)
     */
    @Column(name = "data_type", length = 20)
    private String dataType;

    /**
     * 数据长度varchar
     */
    @Column(name = "character_maximum_length")
    private Integer characterMaximumLength;

    /**
     * 数字类型的整型部分长度
     */
    @Column(name = "numeric_precision")
    private Integer numericPrecision;

    /**
     * 数字类型的小数部分长度
     */
    @Column(name = "numeric_scale")
    private Integer numericScale;

    /**
     * 字段类型
     */
    @Column(name = "column_type", length = 200)
    private String columnType;

    /**
     * PRI为主键，MUL为外键
     */
    @Column(name = "column_key", length = 5)
    private String columnKey;

    /**
     * 自增标识
     */
    @Column(name = "extra", length = 200)
    private String extra;

    /**
     * 字段注释
     */
    @Column(name = "column_comment", length = 1000)
    private String columnComment;

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
        if (!(o instanceof ProjectColumnDO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(projectTableId, that.projectTableId)) {
            return false;
        }
        if (!Objects.equals(tableSchema, that.tableSchema)) {
            return false;
        }
        if (!Objects.equals(tableName, that.tableName)) {
            return false;
        }
        if (!Objects.equals(columnName, that.columnName)) {
            return false;
        }
        if (!Objects.equals(isNullable, that.isNullable)) {
            return false;
        }
        if (!Objects.equals(dataType, that.dataType)) {
            return false;
        }
        if (!Objects.equals(characterMaximumLength, that.characterMaximumLength)) {
            return false;
        }
        if (!Objects.equals(numericPrecision, that.numericPrecision)) {
            return false;
        }
        if (!Objects.equals(numericScale, that.numericScale)) {
            return false;
        }
        if (!Objects.equals(columnType, that.columnType)) {
            return false;
        }
        if (!Objects.equals(columnKey, that.columnKey)) {
            return false;
        }
        if (!Objects.equals(extra, that.extra)) {
            return false;
        }
        if (!Objects.equals(columnComment, that.columnComment)) {
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
        result = 31 * result + (projectTableId != null ? projectTableId.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (isNullable != null ? isNullable.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (characterMaximumLength != null ? characterMaximumLength.hashCode() : 0);
        result = 31 * result + (numericPrecision != null ? numericPrecision.hashCode() : 0);
        result = 31 * result + (numericScale != null ? numericScale.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (columnKey != null ? columnKey.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        result = 31 * result + (columnComment != null ? columnComment.hashCode() : 0);
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