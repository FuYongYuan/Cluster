package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.dbo.RoleButtonDO;
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
    private BigInteger id;

    /**
     * 角色ID
     */
    private BigInteger roleId;

    /**
     * 按钮ID
     */
    private BigInteger buttonId;

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
     * 按钮角色关系 DO 转换 BO
     */
    public static RoleButtonBO toBO(RoleButtonDO dbo) {
        if (dbo != null) {
            return RoleButtonBO.builder()
                    .id(dbo.getId())
                    .roleId(dbo.getRoleId())
                    .buttonId(dbo.getButtonId())
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
     * 按钮角色关系 DO 数组 转换 BO List
     */
    public static List<RoleButtonBO> toBO(RoleButtonDO[] dbos) {
        if (dbos != null) {
            List<RoleButtonBO> bos = new ArrayList<>();
            for (RoleButtonDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 DO List 转换 BO List
     */
    public static List<RoleButtonBO> toBO(List<RoleButtonDO> dbos) {
        if (dbos != null) {
            List<RoleButtonBO> bos = new ArrayList<>();
            for (RoleButtonDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 BO 转换 DO
     */
    public static RoleButtonDO toDO(RoleButtonBO bo) {
        if (bo != null) {
            return RoleButtonDO.builder()
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
     * 按钮角色关系 BO List 转换 DO List
     */
    public static List<RoleButtonDO> toDO(List<RoleButtonBO> bos) {
        if (bos != null) {
            List<RoleButtonDO> dos = new ArrayList<>();
            for (RoleButtonBO bo : bos) {
                dos.add(toDO(bo));
            }
            return dos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 BO 转换 DO
     */
    public RoleButtonDO toDO() {
        return RoleButtonDO.builder()
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
