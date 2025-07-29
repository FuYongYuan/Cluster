package cn.fyy.capability.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 按钮
 *
 * @author fuyy
 */
@Entity
@Table(name = "button", schema = "capability")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ButtonDO implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 菜单ID
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

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Column(name = "state")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ButtonDO buttonDO)) {
            return false;
        }

        if (!id.equals(buttonDO.id)) {
            return false;
        }
        if (!Objects.equals(buttonName, buttonDO.buttonName)) {
            return false;
        }
        if (!Objects.equals(buttonSign, buttonDO.buttonSign)) {
            return false;
        }
        if (!Objects.equals(onClick, buttonDO.onClick)) {
            return false;
        }
        if (!Objects.equals(menuId, buttonDO.menuId)) {
            return false;
        }
        if (!Objects.equals(buttonOrder, buttonDO.buttonOrder)) {
            return false;
        }
        if (!Objects.equals(remark, buttonDO.remark)) {
            return false;
        }
        if (!Objects.equals(createTime, buttonDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, buttonDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, buttonDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, buttonDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, buttonDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, buttonDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, buttonDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (buttonName != null ? buttonName.hashCode() : 0);
        result = 31 * result + (buttonSign != null ? buttonSign.hashCode() : 0);
        result = 31 * result + (onClick != null ? onClick.hashCode() : 0);
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        result = 31 * result + (buttonOrder != null ? buttonOrder.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
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
