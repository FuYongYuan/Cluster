package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.po.RolePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String roleName;

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

    /**
     * 菜单ID集合
     */
    private String menuIds;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 角色 PO 转换 BO
     */
    public static RoleBO toBO(RolePO po) {
        if (po != null) {
            return RoleBO.builder()
                    .id(po.getId())
                    .roleName(po.getRoleName())
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
     * 角色 PO 数组 转换 BO List
     */
    public static List<RoleBO> toBO(RolePO[] pos) {
        if (pos != null) {
            List<RoleBO> bos = new ArrayList<>();
            for (RolePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色 PO List 转换 BO List
     */
    public static List<RoleBO> toBO(List<RolePO> pos) {
        if (pos != null) {
            List<RoleBO> bos = new ArrayList<>();
            for (RolePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色 BO 转换 PO
     */
    public static RolePO toPO(RoleBO bo) {
        if (bo != null) {
            return RolePO.builder()
                    .id(bo.getId())
                    .roleName(bo.getRoleName())
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
     * 角色 BO List 转换 PO List
     */
    public static List<RolePO> toPO(List<RoleBO> bos) {
        if (bos != null) {
            List<RolePO> pos = new ArrayList<>();
            for (RoleBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 角色 BO 转换 PO
     */
    public RolePO toPO() {
        return RolePO.builder()
                .id(this.getId())
                .roleName(this.getRoleName())
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
