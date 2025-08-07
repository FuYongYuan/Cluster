package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.po.RoleButtonPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 按钮角色关系
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleButtonBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 按钮ID
     */
    private Long buttonId;

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
     * 按钮角色关系 PO 转换 BO
     */
    public static RoleButtonBO toBO(RoleButtonPO po) {
        if (po != null) {
            return RoleButtonBO.builder()
                    .id(po.getId())
                    .roleId(po.getRoleId())
                    .buttonId(po.getButtonId())
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
     * 按钮角色关系 PO 数组 转换 BO List
     */
    public static List<RoleButtonBO> toBO(RoleButtonPO[] pos) {
        if (pos != null) {
            List<RoleButtonBO> bos = new ArrayList<>();
            for (RoleButtonPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 PO List 转换 BO List
     */
    public static List<RoleButtonBO> toBO(List<RoleButtonPO> pos) {
        if (pos != null) {
            List<RoleButtonBO> bos = new ArrayList<>();
            for (RoleButtonPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 BO 转换 PO
     */
    public static RoleButtonPO toPO(RoleButtonBO bo) {
        if (bo != null) {
            return RoleButtonPO.builder()
                    .id(bo.getId())
                    .roleId(bo.getRoleId())
                    .buttonId(bo.getButtonId())
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
     * 按钮角色关系 BO List 转换 PO List
     */
    public static List<RoleButtonPO> toPO(List<RoleButtonBO> bos) {
        if (bos != null) {
            List<RoleButtonPO> dos = new ArrayList<>();
            for (RoleButtonBO bo : bos) {
                dos.add(toPO(bo));
            }
            return dos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 BO 转换 PO
     */
    public RoleButtonPO toPO() {
        return RoleButtonPO.builder()
                .id(this.getId())
                .roleId(this.getRoleId())
                .buttonId(this.getButtonId())
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
