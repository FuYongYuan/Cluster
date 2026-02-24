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
 * 按钮
 *
 * @author fyy
 */
@Entity
@Table(name = "button", schema = "capability")
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ButtonPO extends BasePO implements Serializable {

    /**
     * 名称
     */
    @Column(name = "button_name", length = 200)
    private String buttonName;

    /**
     * 标识
     */
    @Column(name = "button_sign", length = 200)
    private String buttonSign;

    /**
     * 点击事件
     */
    @Column(name = "on_click", length = 200)
    private String onClick;

    /**
     * 菜单 ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 排序(按数字从小到大)
     */
    @Column(name = "button_order")
    private Integer buttonOrder;

    /**
     * 备注
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ButtonPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(buttonName, that.buttonName) &&
                Objects.equals(buttonSign, that.buttonSign) &&
                Objects.equals(onClick, that.onClick) &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(buttonOrder, that.buttonOrder) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(buttonName);
        result = 31 * result + Objects.hashCode(buttonSign);
        result = 31 * result + Objects.hashCode(onClick);
        result = 31 * result + Objects.hashCode(menuId);
        result = 31 * result + Objects.hashCode(buttonOrder);
        result = 31 * result + Objects.hashCode(remark);
        return result;
    }
}
