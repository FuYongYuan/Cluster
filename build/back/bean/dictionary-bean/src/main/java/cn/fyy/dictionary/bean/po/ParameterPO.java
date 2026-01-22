package cn.fyy.dictionary.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * 参数
 *
 * @author fuyy
 */
@Entity
@Table(name = "parameter", schema = "dictionary")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParameterPO extends BasePO implements Serializable {

    /**
     * 参数代码
     */
    @Column(name = "parameter_code", length = 200)
    private String parameterCode;

    /**
     * 参数名称
     */
    @Column(name = "parameter_name", length = 200)
    private String parameterName;

    /**
     * 参数值
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "parameter_value", columnDefinition = "text")
    @ToString.Exclude
    private String parameterValue;

    /**
     * 参数说明
     */
    @Column(name = "parameter_explain", length = 4000)
    private String parameterExplain;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParameterPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(parameterCode, that.parameterCode) &&
                Objects.equals(parameterName, that.parameterName) &&
                Objects.equals(parameterValue, that.parameterValue) &&
                Objects.equals(parameterExplain, that.parameterExplain);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(parameterCode);
        result = 31 * result + Objects.hashCode(parameterName);
        result = 31 * result + Objects.hashCode(parameterValue);
        result = 31 * result + Objects.hashCode(parameterExplain);
        return result;
    }
}
