package cn.fyy.capability.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * API信息;数据表的PO对象
 *
 * @author : fyy
 */
@Entity
@Table(name = "api", schema = "capability")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiPO extends BasePO implements Serializable {

    /**
     * 类名
     */
    @Column(name = "class_name", length = 1000)
    private String className;

    /**
     * 类Mapping值
     */
    @Column(name = "class_mapping", length = 4000)
    private String classMapping;

    /**
     * 类Swagger说明
     */
    @Column(name = "class_explain", length = 1000)
    private String classExplain;

    /**
     * 方法名
     */
    @Column(name = "method_name", length = 200)
    private String methodName;

    /**
     * 方法Mapping值
     */
    @Column(name = "method_mapping", length = 4000)
    private String methodMapping;

    /**
     * 方法Swagger说明
     */
    @Column(name = "method_explain", length = 1000)
    private String methodExplain;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiPO that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        return Objects.equals(className, that.className) &&
                Objects.equals(classMapping, that.classMapping) &&
                Objects.equals(classExplain, that.classExplain) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(methodMapping, that.methodMapping) &&
                Objects.equals(methodExplain, that.methodExplain);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(className);
        result = 31 * result + Objects.hashCode(classMapping);
        result = 31 * result + Objects.hashCode(classExplain);
        result = 31 * result + Objects.hashCode(methodName);
        result = 31 * result + Objects.hashCode(methodMapping);
        result = 31 * result + Objects.hashCode(methodExplain);
        return result;
    }
}