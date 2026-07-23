package cn.fyy.authorization.bean.bo;

import cn.fyy.authorization.bean.po.RoleApiPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色API接口访问关系
 *
 * @author fyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleApiBO implements Serializable {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * API_ID
     */
    private Long apiId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人 ID
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
     * 更新人 ID
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
     * 角色API关系 PO 转换 BO
     */
    public static RoleApiBO toBO(RoleApiPO po) {
        if (po != null) {
            return RoleApiBO.builder()
                    .id(po.getId())
                    .roleId(po.getRoleId())
                    .apiId(po.getApiId())
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
     * 角色API关系 PO 数组 转换 BO List
     */
    public static List<RoleApiBO> toBO(RoleApiPO[] pos) {
        if (pos != null) {
            List<RoleApiBO> bos = new ArrayList<>();
            for (RoleApiPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色API关系 PO List 转换 BO List
     */
    public static List<RoleApiBO> toBO(List<RoleApiPO> pos) {
        if (pos != null) {
            List<RoleApiBO> bos = new ArrayList<>();
            for (RoleApiPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色API关系 BO 转换 PO
     */
    public static RoleApiPO toPO(RoleApiBO bo) {
        if (bo != null) {
            return RoleApiPO.builder()
                    .id(bo.getId())
                    .roleId(bo.getRoleId())
                    .apiId(bo.getApiId())
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
     * 角色API关系 BO List 转换 PO List
     */
    public static List<RoleApiPO> toPO(List<RoleApiBO> bos) {
        if (bos != null) {
            List<RoleApiPO> pos = new ArrayList<>();
            for (RoleApiBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 角色API关系 BO 转换 PO
     */
    public RoleApiPO toPO() {
        return RoleApiPO.builder()
                .id(this.getId())
                .roleId(this.getRoleId())
                .apiId(this.getApiId())
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
