package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.ProjectTableDO;
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
    private BigInteger id;

    /**
     * 项目群ID
     */
    private BigInteger projectGroupId;

    /**
     * 数据库设计文件ID
     */
    private BigInteger dataBaseFileId;

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
     * 项目表 DO 转换 BO
     */
    public static ProjectTableBO toBO(ProjectTableDO dbo) {
        if (dbo != null) {
            return ProjectTableBO.builder()
                    .id(dbo.getId())
                    .projectGroupId(dbo.getProjectGroupId())
                    .dataBaseFileId(dbo.getDataBaseFileId())
                    .tableSchema(dbo.getTableSchema())
                    .tableName(dbo.getTableName())
                    .tableComment(dbo.getTableComment())
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
     * 项目表 DO 数组 转换 BO List
     */
    public static List<ProjectTableBO> toBO(ProjectTableDO[] dbos) {
        if (dbos != null) {
            List<ProjectTableBO> bos = new ArrayList<>();
            for (ProjectTableDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 DO List 转换 BO List
     */
    public static List<ProjectTableBO> toBO(List<ProjectTableDO> dbos) {
        if (dbos != null) {
            List<ProjectTableBO> bos = new ArrayList<>();
            for (ProjectTableDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 BO 转换 DO
     */
    public static ProjectTableDO toDO(ProjectTableBO bo) {
        if (bo != null) {
            return ProjectTableDO.builder()
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
     * 项目表 BO List 转换 DO List
     */
    public static List<ProjectTableDO> toDO(List<ProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectTableDO> dbos = new ArrayList<>();
            for (ProjectTableBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 BO 转换 DO
     */
    public ProjectTableDO toDO() {
        return ProjectTableDO.builder()
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