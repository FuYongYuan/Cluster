package cn.fyy.capability.bean.bo;

import cn.fyy.capability.bean.dbo.ButtonDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
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
    private BigInteger id;

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
    private BigInteger menuId;

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
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 按钮 DO 转换 BO
     */
    public static ButtonBO toBO(ButtonDO dbo) {
        if (dbo != null) {
            return ButtonBO.builder()
                    .id(dbo.getId())
                    .buttonName(dbo.getButtonName())
                    .buttonSign(dbo.getButtonSign())
                    .onClick(dbo.getOnClick())
                    .menuId(dbo.getMenuId())
                    .buttonOrder(dbo.getButtonOrder())
                    .remark(dbo.getRemark())
                    .createTime(dbo.getCreateTime())
                    .creatorId(dbo.getCreatorId())
                    .creatorName(dbo.getCreatorName())
                    .updateTime(dbo.getUpdateTime())
                    .updaterId(dbo.getUpdaterId())
                    .updaterName(dbo.getUpdaterName())
                    .state(dbo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 按钮 DO 数组 转换 BO List
     */
    public static List<ButtonBO> toBO(ButtonDO[] dbos) {
        if (dbos != null) {
            List<ButtonBO> bos = new ArrayList<>();
            for (ButtonDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 DO List 转换 BO List
     */
    public static List<ButtonBO> toBO(List<ButtonDO> dbos) {
        if (dbos != null) {
            List<ButtonBO> bos = new ArrayList<>();
            for (ButtonDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO 转换 DO
     */
    public static ButtonDO toDO(ButtonBO bo) {
        if (bo != null) {
            return ButtonDO.builder()
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
     * 按钮 BO List 转换 DO List
     */
    public static List<ButtonDO> toDO(List<ButtonBO> bos) {
        if (bos != null) {
            List<ButtonDO> dbos = new ArrayList<>();
            for (ButtonBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO 转换 DO
     */
    public ButtonDO toDO() {
        return ButtonDO.builder()
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
