package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.ProjectTablePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目表
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTableBO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目群ID
     */
    private Long projectGroupId;

    /**
     * 数据库设计文件ID
     */
    private Long dataBaseFileId;

    /**
     * 所属的库
     */
    private String tableSchema;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

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
     * 项目表 PO 转换 BO
     */
    public static ProjectTableBO toBO(ProjectTablePO po) {
        if (po != null) {
            return ProjectTableBO.builder()
                    .id(po.getId())
                    .projectGroupId(po.getProjectGroupId())
                    .dataBaseFileId(po.getDataBaseFileId())
                    .tableSchema(po.getTableSchema())
                    .tableName(po.getTableName())
                    .tableComment(po.getTableComment())
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
     * 项目表 PO 数组 转换 BO List
     */
    public static List<ProjectTableBO> toBO(ProjectTablePO[] pos) {
        if (pos != null) {
            List<ProjectTableBO> bos = new ArrayList<>();
            for (ProjectTablePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 PO List 转换 BO List
     */
    public static List<ProjectTableBO> toBO(List<ProjectTablePO> pos) {
        if (pos != null) {
            List<ProjectTableBO> bos = new ArrayList<>();
            for (ProjectTablePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 BO 转换 PO
     */
    public static ProjectTablePO toPO(ProjectTableBO bo) {
        if (bo != null) {
            return ProjectTablePO.builder()
                    .id(bo.getId())
                    .projectGroupId(bo.getProjectGroupId())
                    .dataBaseFileId(bo.getDataBaseFileId())
                    .tableSchema(bo.getTableSchema())
                    .tableName(bo.getTableName())
                    .tableComment(bo.getTableComment())
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
     * 项目表 BO List 转换 PO List
     */
    public static List<ProjectTablePO> toPO(List<ProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectTablePO> pos = new ArrayList<>();
            for (ProjectTableBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 BO 转换 PO
     */
    public ProjectTablePO toPO() {
        return ProjectTablePO.builder()
                .id(this.getId())
                .projectGroupId(this.getProjectGroupId())
                .dataBaseFileId(this.getDataBaseFileId())
                .tableSchema(this.getTableSchema())
                .tableName(this.getTableName())
                .tableComment(this.getTableComment())
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