package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.ProjectGroupBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 项目群
 *
 * @author fuyy
 */
@Schema(name = "project_group", description = "项目群 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroupDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger", nullable = false)
    private BigInteger id;

    /**
     * 管理员ID
     */
    @Schema(name = "managerId", description = "管理员ID", type = "BigInteger")
    private BigInteger managerId;

    /**
     * 项目群名称
     */
    @Schema(name = "projectGroupName", description = "项目群名称", type = "String")
    private String projectGroupName;

    /**
     * 组ID
     */
    @Schema(name = "groupId", description = "组ID", type = "String")
    private String groupId;

    /**
     * 工件ID
     */
    @Schema(name = "artifactId", description = "工件ID", type = "String")
    private String artifactId;

    /**
     * 项目群版本号
     */
    @Schema(name = "version", description = "项目群版本号", type = "String")
    private String version;

    /**
     * JDK版本
     */
    @Schema(name = "jdkVersion", description = "JDK版本", type = "String")
    private String jdkVersion;

    /**
     * SpringCloudVersion
     */
    @Schema(name = "springCloudVersion", description = "SpringCloudVersion", type = "String")
    private String springCloudVersion;

    /**
     * SpringCloudAlibabaVersion
     */
    @Schema(name = "springCloudAlibabaVersion", description = "SpringCloudAlibabaVersion", type = "String")
    private String springCloudAlibabaVersion;

    /**
     * SpringBootVersion
     */
    @Schema(name = "springBootVersion", description = "SpringBootVersion", type = "String")
    private String springBootVersion;

    /**
     * API文档组件
     */
    @Schema(name = "openApiVersion", description = "API文档组件", type = "String")
    private String openApiVersion;

    /**
     * 简化对象lombok
     */
    @Schema(name = "lombokVersion", description = "简化对象lombok", type = "String")
    private String lombokVersion;

    /**
     * 数据访问监控Druid
     */
    @Schema(name = "druidVersion", description = "数据访问监控Druid", type = "String")
    private String druidVersion;

    /**
     * 数据库框架MyBatis版本
     */
    @Schema(name = "mybatisVersion", description = "数据库框架MyBatis版本", type = "String")
    private String mybatisVersion;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注", type = "String")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

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
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

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
     * 项目群 BO 转换 DTO
     */
    public static ProjectGroupDTO toDTO(ProjectGroupBO bo) {
        if (bo != null) {
            return ProjectGroupDTO.builder()
                    .id(bo.getId())
                    .managerId(bo.getManagerId())
                    .projectGroupName(bo.getProjectGroupName())
                    .groupId(bo.getGroupId())
                    .artifactId(bo.getArtifactId())
                    .version(bo.getVersion())
                    .jdkVersion(bo.getJdkVersion())
                    .springCloudVersion(bo.getSpringCloudVersion())
                    .springCloudAlibabaVersion(bo.getSpringCloudAlibabaVersion())
                    .springBootVersion(bo.getSpringBootVersion())
                    .openApiVersion(bo.getOpenApiVersion())
                    .lombokVersion(bo.getLombokVersion())
                    .druidVersion(bo.getDruidVersion())
                    .mybatisVersion(bo.getMybatisVersion())
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
     * 项目群 BO 数组 转换 DTO List
     */
    public static List<ProjectGroupDTO> toDTO(ProjectGroupBO[] bos) {
        if (bos != null) {
            List<ProjectGroupDTO> dtos = new ArrayList<>();
            for (ProjectGroupBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 BO List 转换 DTO List
     */
    public static List<ProjectGroupDTO> toDTO(List<ProjectGroupBO> bos) {
        if (bos != null) {
            List<ProjectGroupDTO> dtos = new ArrayList<>();
            for (ProjectGroupBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 DTO 转换 BO
     */
    public static ProjectGroupBO toBO(ProjectGroupDTO dto) {
        if (dto != null) {
            return ProjectGroupBO.builder()
                    .id(dto.getId())
                    .managerId(dto.getManagerId())
                    .projectGroupName(dto.getProjectGroupName())
                    .groupId(dto.getGroupId())
                    .artifactId(dto.getArtifactId())
                    .version(dto.getVersion())
                    .jdkVersion(dto.getJdkVersion())
                    .springCloudVersion(dto.getSpringCloudVersion())
                    .springCloudAlibabaVersion(dto.getSpringCloudAlibabaVersion())
                    .springBootVersion(dto.getSpringBootVersion())
                    .openApiVersion(dto.getOpenApiVersion())
                    .lombokVersion(dto.getLombokVersion())
                    .druidVersion(dto.getDruidVersion())
                    .mybatisVersion(dto.getMybatisVersion())
                    .remark(dto.getRemark())
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
     * 项目群 DTO List 转换 BO List
     */
    public static List<ProjectGroupBO> toBO(List<ProjectGroupDTO> dtos) {
        if (dtos != null) {
            List<ProjectGroupBO> bos = new ArrayList<>();
            for (ProjectGroupDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 DTO 转换 BO
     */
    public ProjectGroupBO toBO() {
        return ProjectGroupBO.builder()
                .id(this.getId())
                .managerId(this.getManagerId())
                .projectGroupName(this.getProjectGroupName())
                .groupId(this.getGroupId())
                .artifactId(this.getArtifactId())
                .version(this.getVersion())
                .jdkVersion(this.getJdkVersion())
                .springCloudVersion(this.getSpringCloudVersion())
                .springCloudAlibabaVersion(this.getSpringCloudAlibabaVersion())
                .springBootVersion(this.getSpringBootVersion())
                .openApiVersion(this.getOpenApiVersion())
                .lombokVersion(this.getLombokVersion())
                .druidVersion(this.getDruidVersion())
                .mybatisVersion(this.getMybatisVersion())
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