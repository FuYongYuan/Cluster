package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.po.RoleManagerPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理员关系
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleManagerBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 管理员ID
     */
    private Long managerId;

    /**
     * 角色ID
     */
    private Long roleId;

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
     * 角色管理员关系 PO 转换 BO
     */
    public static RoleManagerBO toBO(RoleManagerPO po) {
        if (po != null) {
            return RoleManagerBO.builder()
                    .id(po.getId())
                    .managerId(po.getManagerId())
                    .roleId(po.getRoleId())
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
     * 角色管理员关系 PO 数组 转换 BO List
     */
    public static List<RoleManagerBO> toBO(RoleManagerPO[] pos) {
        if (pos != null) {
            List<RoleManagerBO> bos = new ArrayList<>();
            for (RoleManagerPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 PO List 转换 BO List
     */
    public static List<RoleManagerBO> toBO(List<RoleManagerPO> pos) {
        if (pos != null) {
            List<RoleManagerBO> bos = new ArrayList<>();
            for (RoleManagerPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 BO 转换 PO
     */
    public static RoleManagerPO toPO(RoleManagerBO bo) {
        if (bo != null) {
            return RoleManagerPO.builder()
                    .id(bo.getId())
                    .managerId(bo.getManagerId())
                    .roleId(bo.getRoleId())
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
     * 角色管理员关系 BO List 转换 PO List
     */
    public static List<RoleManagerPO> toPO(List<RoleManagerBO> bos) {
        if (bos != null) {
            List<RoleManagerPO> dos = new ArrayList<>();
            for (RoleManagerBO bo : bos) {
                dos.add(toPO(bo));
            }
            return dos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 BO 转换 PO
     */
    public RoleManagerPO toPO() {
        return RoleManagerPO.builder()
                .id(this.getId())
                .managerId(this.getManagerId())
                .roleId(this.getRoleId())
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
