package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.ProjectProjectTableDO;
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
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    private Long updaterId;

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
     * 项目项目表关系 DO 转换 BO
     */
    public static ProjectProjectTableBO toBO(ProjectProjectTableDO dbo) {
        if (dbo != null) {
            return ProjectProjectTableBO.builder()
                    .id(dbo.getId())
                    .projectId(dbo.getProjectId())
                    .projectTableId(dbo.getProjectTableId())
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
     * 项目项目表关系 DO 数组 转换 BO List
     */
    public static List<ProjectProjectTableBO> toBO(ProjectProjectTableDO[] dbos) {
        if (dbos != null) {
            List<ProjectProjectTableBO> bos = new ArrayList<>();
            for (ProjectProjectTableDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 DO List 转换 BO List
     */
    public static List<ProjectProjectTableBO> toBO(List<ProjectProjectTableDO> dbos) {
        if (dbos != null) {
            List<ProjectProjectTableBO> bos = new ArrayList<>();
            for (ProjectProjectTableDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 BO 转换 DO
     */
    public static ProjectProjectTableDO toDO(ProjectProjectTableBO bo) {
        if (bo != null) {
            return ProjectProjectTableDO.builder()
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
     * 项目项目表关系 BO List 转换 DO List
     */
    public static List<ProjectProjectTableDO> toDO(List<ProjectProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectProjectTableDO> dbos = new ArrayList<>();
            for (ProjectProjectTableBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 BO 转换 DO
     */
    public ProjectProjectTableDO toDO() {
        return ProjectProjectTableDO.builder()
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