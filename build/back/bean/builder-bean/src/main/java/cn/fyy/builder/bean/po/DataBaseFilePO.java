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
 * 数据库设计文件
 *
 * @author fyy
 */
@Entity
@Table(name = "data_base_file", schema = "builder")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseFilePO extends BasePO implements Serializable {

    /**
     * 项目群 ID
     */
    @Column(name = "project_group_id")
    private Long projectGroupId;

    /**
     * 文件地址
     */
    @Column(name = "file_url", length = 200)
    private String fileUrl;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataBaseFilePO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(projectGroupId, that.projectGroupId) &&
                Objects.equals(fileUrl, that.fileUrl);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(projectGroupId);
        result = 31 * result + Objects.hashCode(fileUrl);
        return result;
    }
}