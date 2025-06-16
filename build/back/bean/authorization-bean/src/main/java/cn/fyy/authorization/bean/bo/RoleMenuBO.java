package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.dbo.RoleMenuDO;
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
    private BigInteger id;

    /**
     * 角色ID
     */
    private BigInteger roleId;

    /**
     * 菜单ID
     */
    private BigInteger menuId;

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
     * 菜单角色关系 DO 转换 BO
     */
    public static RoleMenuBO toBO(RoleMenuDO dbo) {
        if (dbo != null) {
            return RoleMenuBO.builder()
                    .id(dbo.getId())
                    .roleId(dbo.getRoleId())
                    .menuId(dbo.getMenuId())
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
     * 菜单角色关系 DO 数组 转换 BO List
     */
    public static List<RoleMenuBO> toBO(RoleMenuDO[] dbos) {
        if (dbos != null) {
            List<RoleMenuBO> bos = new ArrayList<>();
            for (RoleMenuDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 DO List 转换 BO List
     */
    public static List<RoleMenuBO> toBO(List<RoleMenuDO> dbos) {
        if (dbos != null) {
            List<RoleMenuBO> bos = new ArrayList<>();
            for (RoleMenuDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 BO 转换 DO
     */
    public static RoleMenuDO toDO(RoleMenuBO bo) {
        if (bo != null) {
            return RoleMenuDO.builder()
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
     * 菜单角色关系 BO List 转换 DO List
     */
    public static List<RoleMenuDO> toDO(List<RoleMenuBO> bos) {
        if (bos != null) {
            List<RoleMenuDO> dbos = new ArrayList<>();
            for (RoleMenuBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 菜单角色关系 BO 转换 DO
     */
    public RoleMenuDO toDO() {
        return RoleMenuDO.builder()
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
