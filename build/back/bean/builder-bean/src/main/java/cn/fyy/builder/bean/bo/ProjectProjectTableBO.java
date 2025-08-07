package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.ProjectProjectTablePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目项目表关系
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProjectTableBO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 项目表ID
     */
    private Long projectTableId;

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
     * 项目项目表关系 PO 转换 BO
     */
    public static ProjectProjectTableBO toBO(ProjectProjectTablePO po) {
        if (po != null) {
            return ProjectProjectTableBO.builder()
                    .id(po.getId())
                    .projectId(po.getProjectId())
                    .projectTableId(po.getProjectTableId())
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
     * 项目项目表关系 PO 数组 转换 BO List
     */
    public static List<ProjectProjectTableBO> toBO(ProjectProjectTablePO[] pos) {
        if (pos != null) {
            List<ProjectProjectTableBO> bos = new ArrayList<>();
            for (ProjectProjectTablePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 PO List 转换 BO List
     */
    public static List<ProjectProjectTableBO> toBO(List<ProjectProjectTablePO> pos) {
        if (pos != null) {
            List<ProjectProjectTableBO> bos = new ArrayList<>();
            for (ProjectProjectTablePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 BO 转换 PO
     */
    public static ProjectProjectTablePO toPO(ProjectProjectTableBO bo) {
        if (bo != null) {
            return ProjectProjectTablePO.builder()
                    .id(bo.getId())
                    .projectId(bo.getProjectId())
                    .projectTableId(bo.getProjectTableId())
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
     * 项目项目表关系 BO List 转换 PO List
     */
    public static List<ProjectProjectTablePO> toPO(List<ProjectProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectProjectTablePO> pos = new ArrayList<>();
            for (ProjectProjectTableBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 BO 转换 PO
     */
    public ProjectProjectTablePO toPO() {
        return ProjectProjectTablePO.builder()
                .id(this.getId())
                .projectId(this.getProjectId())
                .projectTableId(this.getProjectTableId())
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