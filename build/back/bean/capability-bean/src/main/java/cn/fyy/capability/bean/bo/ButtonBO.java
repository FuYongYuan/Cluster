package cn.fyy.capability.bean.bo;

import cn.fyy.capability.bean.po.ButtonPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 按钮
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ButtonBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String buttonName;

    /**
     * 标识
     */
    private String buttonSign;

    /**
     * 点击事件
     */
    private String onClick;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 排序(按数字从小到大)
     */
    private Integer buttonOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 更新人名称
     */
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 按钮 PO 转换 BO
     */
    public static ButtonBO toBO(ButtonPO po) {
        if (po != null) {
            return ButtonBO.builder()
                    .id(po.getId())
                    .buttonName(po.getButtonName())
                    .buttonSign(po.getButtonSign())
                    .onClick(po.getOnClick())
                    .menuId(po.getMenuId())
                    .buttonOrder(po.getButtonOrder())
                    .remark(po.getRemark())
                    .createTime(po.getCreateTime())
                    .creatorId(po.getCreatorId())
                    .creatorName(po.getCreatorName())
                    .updateTime(po.getUpdateTime())
                    .updaterId(po.getUpdaterId())
                    .updaterName(po.getUpdaterName())
                    .state(po.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 按钮 PO 数组 转换 BO List
     */
    public static List<ButtonBO> toBO(ButtonPO[] pos) {
        if (pos != null) {
            List<ButtonBO> bos = new ArrayList<>();
            for (ButtonPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 PO List 转换 BO List
     */
    public static List<ButtonBO> toBO(List<ButtonPO> pos) {
        if (pos != null) {
            List<ButtonBO> bos = new ArrayList<>();
            for (ButtonPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO 转换 PO
     */
    public static ButtonPO toPO(ButtonBO bo) {
        if (bo != null) {
            return ButtonPO.builder()
                    .id(bo.getId())
                    .buttonName(bo.getButtonName())
                    .buttonSign(bo.getButtonSign())
                    .onClick(bo.getOnClick())
                    .menuId(bo.getMenuId())
                    .buttonOrder(bo.getButtonOrder())
                    .remark(bo.getRemark())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO List 转换 PO List
     */
    public static List<ButtonPO> toPO(List<ButtonBO> bos) {
        if (bos != null) {
            List<ButtonPO> pos = new ArrayList<>();
            for (ButtonBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO 转换 PO
     */
    public ButtonPO toPO() {
        return ButtonPO.builder()
                .id(this.getId())
                .buttonName(this.getButtonName())
                .buttonSign(this.getButtonSign())
                .onClick(this.getOnClick())
                .menuId(this.getMenuId())
                .buttonOrder(this.getButtonOrder())
                .remark(this.getRemark())
                .createTime(this.getCreateTime())
                .creatorId(this.getCreatorId())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterId(this.getUpdaterId())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .build();
    }

}
