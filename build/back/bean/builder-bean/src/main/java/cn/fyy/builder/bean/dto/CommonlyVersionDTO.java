package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.CommonlyVersionBO;
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
 * 常用版本
 *
 * @author fuyy
 */
@Schema(name = "commonly_version", description = "常用版本 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonlyVersionDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long", nullable = false)
    private Long id;

    /**
     * 管理员ID
     */
    @Schema(name = "managerId", description = "管理员ID", type = "Long")
    private Long managerId;

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
     * 更新时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "Long")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Schema(name = "updaterName", description = "修改人名称", type = "String")
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Byte")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 常用版本 BO 转换 DTO
     */
    public static CommonlyVersionDTO toDTO(CommonlyVersionBO bo) {
        if (bo != null) {
            return CommonlyVersionDTO.builder()
                    .id(bo.getId())
                    .managerId(bo.getManagerId())
                    .jdkVersion(bo.getJdkVersion())
                    .springCloudVersion(bo.getSpringCloudVersion())
                    .springCloudAlibabaVersion(bo.getSpringCloudAlibabaVersion())
                    .springBootVersion(bo.getSpringBootVersion())
                    .openApiVersion(bo.getOpenApiVersion())
                    .lombokVersion(bo.getLombokVersion())
                    .druidVersion(bo.getDruidVersion())
                    .mybatisVersion(bo.getMybatisVersion())
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
     * 常用版本 BO 数组 转换 DTO List
     */
    public static List<CommonlyVersionDTO> toDTO(CommonlyVersionBO[] bos) {
        if (bos != null) {
            List<CommonlyVersionDTO> dtos = new ArrayList<>();
            for (CommonlyVersionBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 BO List 转换 DTO List
     */
    public static List<CommonlyVersionDTO> toDTO(List<CommonlyVersionBO> bos) {
        if (bos != null) {
            List<CommonlyVersionDTO> dtos = new ArrayList<>();
            for (CommonlyVersionBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 DTO 转换 BO
     */
    public static CommonlyVersionBO toBO(CommonlyVersionDTO dto) {
        if (dto != null) {
            return CommonlyVersionBO.builder()
                    .id(dto.getId())
                    .managerId(dto.getManagerId())
                    .jdkVersion(dto.getJdkVersion())
                    .springCloudVersion(dto.getSpringCloudVersion())
                    .springCloudAlibabaVersion(dto.getSpringCloudAlibabaVersion())
                    .springBootVersion(dto.getSpringBootVersion())
                    .openApiVersion(dto.getOpenApiVersion())
                    .lombokVersion(dto.getLombokVersion())
                    .druidVersion(dto.getDruidVersion())
                    .mybatisVersion(dto.getMybatisVersion())
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
     * 常用版本 DTO List 转换 BO List
     */
    public static List<CommonlyVersionBO> toBO(List<CommonlyVersionDTO> dtos) {
        if (dtos != null) {
            List<CommonlyVersionBO> bos = new ArrayList<>();
            for (CommonlyVersionDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 DTO 转换 BO
     */
    public CommonlyVersionBO toBO() {
        return CommonlyVersionBO.builder()
                .id(this.getId())
                .managerId(this.getManagerId())
                .jdkVersion(this.getJdkVersion())
                .springCloudVersion(this.getSpringCloudVersion())
                .springCloudAlibabaVersion(this.getSpringCloudAlibabaVersion())
                .springBootVersion(this.getSpringBootVersion())
                .openApiVersion(this.getOpenApiVersion())
                .lombokVersion(this.getLombokVersion())
                .druidVersion(this.getDruidVersion())
                .mybatisVersion(this.getMybatisVersion())
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