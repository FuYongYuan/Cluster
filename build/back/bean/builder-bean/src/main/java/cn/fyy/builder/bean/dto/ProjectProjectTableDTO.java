package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.ProjectProjectTableBO;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 项目项目表关系
 *
 * @author fuyy
 */
@Schema(name = "project_project_table", description = "项目项目表关系 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProjectTableDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger", nullable = false)
    private BigInteger id;

    /**
     * 项目ID
     */
    @Schema(name = "projectId", description = "项目ID", type = "BigInteger")
    private BigInteger projectId;

    /**
     * 项目表ID
     */
    @Schema(name = "projectTableId", description = "项目表ID", type = "BigInteger")
    private BigInteger projectTableId;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

    /**
     * 创建人ID
     */
    @Schema(name = "creatorId", description = "创建人ID", type = "BigInteger")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Schema(name = "creatorName", description = "创建人名称", type = "String")
    private String creatorName;

    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "Date")
    private Date updateTime;

    /**
     * 修改人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "BigInteger")
    private BigInteger updaterId;

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
     * 项目项目表关系 BO 转换 DTO
     */
    public static ProjectProjectTableDTO toDTO(ProjectProjectTableBO bo) {
        if (bo != null) {
            return ProjectProjectTableDTO.builder()
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
     * 项目项目表关系 BO 数组 转换 DTO List
     */
    public static List<ProjectProjectTableDTO> toDTO(ProjectProjectTableBO[] bos) {
        if (bos != null) {
            List<ProjectProjectTableDTO> dtos = new ArrayList<>();
            for (ProjectProjectTableBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 BO List 转换 DTO List
     */
    public static List<ProjectProjectTableDTO> toDTO(List<ProjectProjectTableBO> bos) {
        if (bos != null) {
            List<ProjectProjectTableDTO> dtos = new ArrayList<>();
            for (ProjectProjectTableBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 DTO 转换 BO
     */
    public static ProjectProjectTableBO toBO(ProjectProjectTableDTO dto) {
        if (dto != null) {
            return ProjectProjectTableBO.builder()
                    .id(dto.getId())
                    .projectId(dto.getProjectId())
                    .projectTableId(dto.getProjectTableId())
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
     * 项目项目表关系 DTO List 转换 BO List
     */
    public static List<ProjectProjectTableBO> toBO(List<ProjectProjectTableDTO> dtos) {
        if (dtos != null) {
            List<ProjectProjectTableBO> bos = new ArrayList<>();
            for (ProjectProjectTableDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目项目表关系 DTO 转换 BO
     */
    public ProjectProjectTableBO toBO() {
        return ProjectProjectTableBO.builder()
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