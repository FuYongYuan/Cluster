package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.ProjectTableBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "project_table", description = "项目表 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTableDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long", nullable = false)
    private Long id;

    /**
     * 项目群ID
     */
    @Schema(name = "projectGroupId", description = "项目群ID", type = "Long")
    private Long projectGroupId;

    /**
     * 数据库设计文件ID
     */
    @Schema(name = "dataBaseFileId", description = "数据库设计文件ID", type = "Long")
    private Long dataBaseFileId;

    /**
     * 所属的库
     */
    @Schema(name = "tableSchema", description = "所属的库", type = "String")
    private String tableSchema;

    /**
     * 表名称
     */
    @Schema(name = "tableName", description = "表名称", type = "String")
    private String tableName;

    /**
     * 表注释
     */
    @Schema(name = "tableComment", description = "表注释", type = "String")
    private String tableComment;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Schema(name = "creatorId", description = "创建人ID", type = "Long")
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Schema(name = "creatorName", description = "创建人名称", type = "String")
    private String creatorName;

    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "Long")
    private Long updaterId;

    /**
     * 修改人名称
     */
    @Schema(name = "updaterName", description = "修改人名称", type = "String")
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Integer")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 项目表 BO 转换 DTO
     */
    public static ProjectTableDTO toDTO(ProjectTableBO bo) {
        if (bo != null) {
            return ProjectTableDTO.builder()
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
     * 项目表 BO 数组 转换 DTO List
     */
    public static List<ProjectTableDTO> toDTO(ProjectTableBO[] bos) {
        if (bos != null) {
            List<ProjectTableDTO> dtos = new ArrayList<>();
            for (ProjectTableBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 BO List 转换 DTO List
     */
    public static List<ProjectTableDTO> toDTO(List<ProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectTableDTO> dtos = new ArrayList<>();
            for (ProjectTableBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 DTO 转换 BO
     */
    public static ProjectTableBO toBO(ProjectTableDTO dto) {
        if (dto != null) {
            return ProjectTableBO.builder()
                    .id(dto.getId())
                    .projectGroupId(dto.getProjectGroupId())
                    .dataBaseFileId(dto.getDataBaseFileId())
                    .tableSchema(dto.getTableSchema())
                    .tableName(dto.getTableName())
                    .tableComment(dto.getTableComment())
                    .createTime(dto.getCreateTime())
                    .creatorId(dto.getCreatorId())
                    .creatorName(dto.getCreatorName())
                    .updateTime(dto.getUpdateTime())
                    .updaterId(dto.getUpdaterId())
                    .updaterName(dto.getUpdaterName())
                    .state(dto.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 项目表 DTO List 转换 BO List
     */
    public static List<ProjectTableBO> toBO(List<ProjectTableDTO> dtos) {
        if (dtos != null) {
            List<ProjectTableBO> bos = new ArrayList<>();
            for (ProjectTableDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表 DTO 转换 BO
     */
    public ProjectTableBO toBO() {
        return ProjectTableBO.builder()
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