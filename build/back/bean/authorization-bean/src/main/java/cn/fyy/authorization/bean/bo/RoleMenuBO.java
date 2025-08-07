package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.po.RoleMenuPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单角色关系
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

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
     * 菜单角色关系 PO 转换 BO
     */
    public static RoleMenuBO toBO(RoleMenuPO po) {
        if (po != null) {
            return RoleMenuBO.builder()
                    .id(po.getId())
                    .roleId(po.getRoleId())
                    .menuId(po.getMenuId())
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
     * 菜单角色关系 PO 数组 转换 BO List
     */
    public static List<RoleMenuBO> toBO(RoleMenuPO[] pos) {
        if (pos != null) {
            List<RoleMenuBO> bos = new ArrayList<>();
            for (RoleMenuPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 PO List 转换 BO List
     */
    public static List<RoleMenuBO> toBO(List<RoleMenuPO> pos) {
        if (pos != null) {
            List<RoleMenuBO> bos = new ArrayList<>();
            for (RoleMenuPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 BO 转换 PO
     */
    public static RoleMenuPO toPO(RoleMenuBO bo) {
        if (bo != null) {
            return RoleMenuPO.builder()
                    .id(bo.getId())
                    .roleId(bo.getRoleId())
                    .menuId(bo.getMenuId())
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
     * 菜单角色关系 BO List 转换 PO List
     */
    public static List<RoleMenuPO> toPO(List<RoleMenuBO> bos) {
        if (bos != null) {
            List<RoleMenuPO> pos = new ArrayList<>();
            for (RoleMenuBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 BO 转换 PO
     */
    public RoleMenuPO toPO() {
        return RoleMenuPO.builder()
                .id(this.getId())
                .roleId(this.getRoleId())
                .menuId(this.getMenuId())
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
