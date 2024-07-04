package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.dbo.RoleManagerDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
    private BigInteger id;

    /**
     * 管理员ID
     */
    private BigInteger managerId;

    /**
     * 角色ID
     */
    private BigInteger roleId;

    /**
     * 创建时间
     */
    private Date createTime;

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
    private Date updateTime;

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
     * 角色管理员关系 DO 转换 BO
     */
    public static RoleManagerBO toBO(RoleManagerDO dbo) {
        if (dbo != null) {
            return RoleManagerBO.builder()
                    .id(dbo.getId())
                    .managerId(dbo.getManagerId())
                    .roleId(dbo.getRoleId())
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
     * 角色管理员关系 DO 数组 转换 BO List
     */
    public static List<RoleManagerBO> toBO(RoleManagerDO[] dbos) {
        if (dbos != null) {
            List<RoleManagerBO> bos = new ArrayList<>();
            for (RoleManagerDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 DO List 转换 BO List
     */
    public static List<RoleManagerBO> toBO(List<RoleManagerDO> dbos) {
        if (dbos != null) {
            List<RoleManagerBO> bos = new ArrayList<>();
            for (RoleManagerDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 BO 转换 DO
     */
    public static RoleManagerDO toDO(RoleManagerBO bo) {
        if (bo != null) {
            return RoleManagerDO.builder()
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
     * 角色管理员关系 BO List 转换 DO List
     */
    public static List<RoleManagerDO> toDO(List<RoleManagerBO> bos) {
        if (bos != null) {
            List<RoleManagerDO> dos = new ArrayList<>();
            for (RoleManagerBO bo : bos) {
                dos.add(toDO(bo));
            }
            return dos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 BO 转换 DO
     */
    public RoleManagerDO toDO() {
        return RoleManagerDO.builder()
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
