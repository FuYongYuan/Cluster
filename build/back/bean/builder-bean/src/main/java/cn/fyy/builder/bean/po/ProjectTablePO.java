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
 * 项目表
 *
 * @author fyy
 */
@Entity
@Table(name = "project_table", schema = "builder")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTablePO extends BasePO implements Serializable {

    /**
     * 项目群 ID
     */
    @Column(name = "project_group_id")
    private Long projectGroupId;

    /**
     * 数据库设计文件 ID
     */
    @Column(name = "data_base_file_id")
    private Long dataBaseFileId;

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

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectTablePO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(projectGroupId, that.projectGroupId) &&
                Objects.equals(dataBaseFileId, that.dataBaseFileId) &&
                Objects.equals(tableSchema, that.tableSchema) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(tableComment, that.tableComment);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(projectGroupId);
        result = 31 * result + Objects.hashCode(dataBaseFileId);
        result = 31 * result + Objects.hashCode(tableSchema);
        result = 31 * result + Objects.hashCode(tableName);
        result = 31 * result + Objects.hashCode(tableComment);
        return result;
    }
}