package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.ProjectBO;
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
 * 项目
 *
 * @author fuyy
 */
@Schema(name = "project", description = "项目 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger", nullable = false)
    private BigInteger id;

    /**
     * 项目群ID
     */
    @Schema(name = "projectGroupId", description = "项目群ID", type = "BigInteger")
    private BigInteger projectGroupId;

    /**
     * 项目名称
     */
    @Schema(name = "projectName", description = "项目名称", type = "String")
    private String projectName;

    /**
     * 工件ID
     */
    @Schema(name = "artifactId", description = "工件ID", type = "String")
    private String artifactId;

    /**
     * 项目包目录
     */
    @Schema(name = "packageName", description = "项目包目录", type = "String")
    private String packageName;

    /**
     * 项目版本
     */
    @Schema(name = "projectVersion", description = "项目版本", type = "String")
    private String projectVersion;

    /**
     * 项目的描述
     */
    @Schema(name = "projectDescription", description = "项目的描述", type = "String")
    private String projectDescription;

    /**
     * 项目访问路径
     */
    @Schema(name = "projectUrl", description = "项目访问路径", type = "String")
    private String projectUrl;

    /**
     * 项目访问端口
     */
    @Schema(name = "projectPort", description = "项目访问端口", type = "String")
    private String projectPort;

    /**
     * 是否Service创建接口(0.否 1.是)
     */
    @Schema(name = "isServiceInterface", description = "是否Service创建接口(0.否 1.是)", type = "Integer")
    private Integer isServiceInterface;

    /**
     * 是否去除BO层(0.否 1.是)
     */
    @Schema(name = "isDeleteBo", description = "是否去除BO层(0.否 1.是)", type = "Integer")
    private Integer isDeleteBo;

    /**
     * 是否生成基础方法(0.否 1.是)
     */
    @Schema(name = "isBasisMethod", description = "是否生成基础方法(0.否 1.是)", type = "Integer")
    private Integer isBasisMethod;

    /**
     * 是否使用Redis缓存(0.否 1.是)
     */
    @Schema(name = "isRedis", description = "是否使用Redis缓存(0.否 1.是)", type = "Integer")
    private Integer isRedis;

    /**
     * 是否使用MinIO文件服务器(0.否 1.是)
     */
    @Schema(name = "isMinio", description = "是否使用MinIO文件服务器(0.否 1.是)", type = "Integer")
    private Integer isMinio;

    /**
     * 是否使用Mail能力(0.否 1.是)
     */
    @Schema(name = "isMail", description = "是否使用Mail能力(0.否 1.是)", type = "Integer")
    private Integer isMail;

    /**
     * 是否使用内容处理能力(0.否 1.是)
     */
    @Schema(name = "isDispose", description = "是否使用内容处理能力(0.否 1.是)", type = "Integer")
    private Integer isDispose;

    /**
     * 是否使用加密能力(0.否 1.是)
     */
    @Schema(name = "isEncrypt", description = "是否使用加密能力(0.否 1.是)", type = "Integer")
    private Integer isEncrypt;

    /**
     * 是否使用Excel能力(0.否 1.是)
     */
    @Schema(name = "isExcel", description = "是否使用Excel能力(0.否 1.是)", type = "Integer")
    private Integer isExcel;

    /**
     * Druid访问路径
     */
    @Schema(name = "druidUrl", description = "Druid访问路径", type = "String")
    private String druidUrl;

    /**
     * Druid登录账号
     */
    @Schema(name = "druidAccount", description = "Druid登录账号", type = "String")
    private String druidAccount;

    /**
     * Druid登录密码
     */
    @Schema(name = "druidPassword", description = "Druid登录密码", type = "String")
    private String druidPassword;

    /**
     * 是否去除无主键的表(0.否 1.是)
     */
    @Schema(name = "isDeleteNotPk", description = "是否去除无主键的表(0.否 1.是)", type = "Integer")
    private Integer isDeleteNotPk;

    /**
     * 是否吧主键生成时放第一位(0.否 1.是)
     */
    @Schema(name = "isOrderKey", description = "是否吧主键生成时放第一位(0.否 1.是)", type = "Integer")
    private Integer isOrderKey;

    /**
     * 数据层框架类型(0.不使用数据源 1.JPA 2.Mybatis)
     */
    @Schema(name = "dataBaseFramework", description = "数据层框架类型(0.不使用数据源 1.JPA 2.Mybatis)", type = "Integer")
    private Integer dataBaseFramework;

    /**
     * 数据库格式设定(0.没有格式转换 1.X_X_Xxxx => XXXXxxx)
     */
    @Schema(name = "dataBaseFormat", description = "数据库格式设定(0.没有格式转换 1.X_X_Xxxx => XXXXxxx)", type = "Integer")
    private Integer dataBaseFormat;

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
     * 项目 BO 转换 DTO
     */
    public static ProjectDTO toDTO(ProjectBO bo) {
        if (bo != null) {
            return ProjectDTO.builder()
                    .id(bo.getId())
                    .projectGroupId(bo.getProjectGroupId())
                    .projectName(bo.getProjectName())
                    .artifactId(bo.getArtifactId())
                    .packageName(bo.getPackageName())
                    .projectVersion(bo.getProjectVersion())
                    .projectDescription(bo.getProjectDescription())
                    .projectUrl(bo.getProjectUrl())
                    .projectPort(bo.getProjectPort())
                    .isServiceInterface(bo.getIsServiceInterface())
                    .isDeleteBo(bo.getIsDeleteBo())
                    .isBasisMethod(bo.getIsBasisMethod())
                    .isRedis(bo.getIsRedis())
                    .isMinio(bo.getIsMinio())
                    .isMail(bo.getIsMail())
                    .isDispose(bo.getIsDispose())
                    .isEncrypt(bo.getIsEncrypt())
                    .isExcel(bo.getIsExcel())
                    .druidUrl(bo.getDruidUrl())
                    .druidAccount(bo.getDruidAccount())
                    .druidPassword(bo.getDruidPassword())
                    .isDeleteNotPk(bo.getIsDeleteNotPk())
                    .isOrderKey(bo.getIsOrderKey())
                    .dataBaseFramework(bo.getDataBaseFramework())
                    .dataBaseFormat(bo.getDataBaseFormat())
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
     * 项目 BO 数组 转换 DTO List
     */
    public static List<ProjectDTO> toDTO(ProjectBO[] bos) {
        if (bos != null) {
            List<ProjectDTO> dtos = new ArrayList<>();
            for (ProjectBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目 BO List 转换 DTO List
     */
    public static List<ProjectDTO> toDTO(List<ProjectBO> bos) {
        if (bos != null) {
            List<ProjectDTO> dtos = new ArrayList<>();
            for (ProjectBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目 DTO 转换 BO
     */
    public static ProjectBO toBO(ProjectDTO dto) {
        if (dto != null) {
            return ProjectBO.builder()
                    .id(dto.getId())
                    .projectGroupId(dto.getProjectGroupId())
                    .projectName(dto.getProjectName())
                    .artifactId(dto.getArtifactId())
                    .packageName(dto.getPackageName())
                    .projectVersion(dto.getProjectVersion())
                    .projectDescription(dto.getProjectDescription())
                    .projectUrl(dto.getProjectUrl())
                    .projectPort(dto.getProjectPort())
                    .isServiceInterface(dto.getIsServiceInterface())
                    .isDeleteBo(dto.getIsDeleteBo())
                    .isBasisMethod(dto.getIsBasisMethod())
                    .isRedis(dto.getIsRedis())
                    .isMinio(dto.getIsMinio())
                    .isMail(dto.getIsMail())
                    .isDispose(dto.getIsDispose())
                    .isEncrypt(dto.getIsEncrypt())
                    .isExcel(dto.getIsExcel())
                    .druidUrl(dto.getDruidUrl())
                    .druidAccount(dto.getDruidAccount())
                    .druidPassword(dto.getDruidPassword())
                    .isDeleteNotPk(dto.getIsDeleteNotPk())
                    .isOrderKey(dto.getIsOrderKey())
                    .dataBaseFramework(dto.getDataBaseFramework())
                    .dataBaseFormat(dto.getDataBaseFormat())
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
     * 项目 DTO List 转换 BO List
     */
    public static List<ProjectBO> toBO(List<ProjectDTO> dtos) {
        if (dtos != null) {
            List<ProjectBO> bos = new ArrayList<>();
            for (ProjectDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目 DTO 转换 BO
     */
    public ProjectBO toBO() {
        return ProjectBO.builder()
                .id(this.getId())
                .projectGroupId(this.getProjectGroupId())
                .projectName(this.getProjectName())
                .artifactId(this.getArtifactId())
                .packageName(this.getPackageName())
                .projectVersion(this.getProjectVersion())
                .projectDescription(this.getProjectDescription())
                .projectUrl(this.getProjectUrl())
                .projectPort(this.getProjectPort())
                .isServiceInterface(this.getIsServiceInterface())
                .isDeleteBo(this.getIsDeleteBo())
                .isBasisMethod(this.getIsBasisMethod())
                .isRedis(this.getIsRedis())
                .isMinio(this.getIsMinio())
                .isMail(this.getIsMail())
                .isDispose(this.getIsDispose())
                .isEncrypt(this.getIsEncrypt())
                .isExcel(this.getIsExcel())
                .druidUrl(this.getDruidUrl())
                .druidAccount(this.getDruidAccount())
                .druidPassword(this.getDruidPassword())
                .isDeleteNotPk(this.getIsDeleteNotPk())
                .isOrderKey(this.getIsOrderKey())
                .dataBaseFramework(this.getDataBaseFramework())
                .dataBaseFormat(this.getDataBaseFormat())
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