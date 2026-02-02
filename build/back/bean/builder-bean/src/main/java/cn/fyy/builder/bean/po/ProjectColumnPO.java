package cn.fyy.builder.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * 项目表字段
 *
 * @author fyy
 */
@Entity
@Table(name = "project_column", schema = "builder")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnPO extends BasePO implements Serializable {

    /**
     * 项目表 ID
     */
    @Column(name = "project_table_id")
    private Long projectTableId;

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
     * 数据长度 varchar
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
     * PRI 为主键，MUL 为外键
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

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectColumnPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(projectTableId, that.projectTableId) &&
                Objects.equals(tableSchema, that.tableSchema) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(columnName, that.columnName) &&
                Objects.equals(isNullable, that.isNullable) &&
                Objects.equals(dataType, that.dataType) &&
                Objects.equals(characterMaximumLength, that.characterMaximumLength) &&
                Objects.equals(numericPrecision, that.numericPrecision) &&
                Objects.equals(numericScale, that.numericScale) &&
                Objects.equals(columnType, that.columnType) &&
                Objects.equals(columnKey, that.columnKey) &&
                Objects.equals(extra, that.extra) &&
                Objects.equals(columnComment, that.columnComment);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(projectTableId);
        result = 31 * result + Objects.hashCode(tableSchema);
        result = 31 * result + Objects.hashCode(tableName);
        result = 31 * result + Objects.hashCode(columnName);
        result = 31 * result + Objects.hashCode(isNullable);
        result = 31 * result + Objects.hashCode(dataType);
        result = 31 * result + Objects.hashCode(characterMaximumLength);
        result = 31 * result + Objects.hashCode(numericPrecision);
        result = 31 * result + Objects.hashCode(numericScale);
        result = 31 * result + Objects.hashCode(columnType);
        result = 31 * result + Objects.hashCode(columnKey);
        result = 31 * result + Objects.hashCode(extra);
        result = 31 * result + Objects.hashCode(columnComment);
        return result;
    }
}