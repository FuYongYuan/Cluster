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
 * 项目项目表关系
 *
 * @author fuyy
 */
@Entity
@Table(name = "project_project_table", schema = "builder")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProjectTablePO extends BasePO implements Serializable {

    /**
     * 项目 ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目表 ID
     */
    @Column(name = "project_table_id")
    private Long projectTableId;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectProjectTablePO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectTableId, that.projectTableId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(projectId);
        result = 31 * result + Objects.hashCode(projectTableId);
        return result;
    }
}