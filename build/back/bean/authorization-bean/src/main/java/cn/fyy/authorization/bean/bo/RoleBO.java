package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.dbo.RoleDO;
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
    private BigInteger id;

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

    /**
     * 菜单ID集合
     */
    private String menuIds;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 角色 DO 转换 BO
     */
    public static RoleBO toBO(RoleDO dbo) {
        if (dbo != null) {
            return RoleBO.builder()
                    .id(dbo.getId())
                    .roleName(dbo.getRoleName())
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
     * 角色 DO 数组 转换 BO List
     */
    public static List<RoleBO> toBO(RoleDO[] dbos) {
        if (dbos != null) {
            List<RoleBO> bos = new ArrayList<>();
            for (RoleDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色 DO List 转换 BO List
     */
    public static List<RoleBO> toBO(List<RoleDO> dbos) {
        if (dbos != null) {
            List<RoleBO> bos = new ArrayList<>();
            for (RoleDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色 BO 转换 DO
     */
    public static RoleDO toDO(RoleBO bo) {
        if (bo != null) {
            return RoleDO.builder()
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
     * 角色 BO List 转换 DO List
     */
    public static List<RoleDO> toDO(List<RoleBO> bos) {
        if (bos != null) {
            List<RoleDO> dbos = new ArrayList<>();
            for (RoleBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 角色 BO 转换 DO
     */
    public RoleDO toDO() {
        return RoleDO.builder()
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
