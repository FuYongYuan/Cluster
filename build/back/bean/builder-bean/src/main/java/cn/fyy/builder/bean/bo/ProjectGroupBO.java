package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.ProjectGroupDO;
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
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroupBO implements Serializable {
    /**
     * 主键ID
     */
    private BigInteger id;

    /**
     * 管理员ID
     */
    private BigInteger managerId;

    /**
     * 项目群名称
     */
    private String projectGroupName;

    /**
     * 组ID
     */
    private String groupId;

    /**
     * 工件ID
     */
    private String artifactId;

    /**
     * 项目群版本号
     */
    private String version;

    /**
     * JDK版本
     */
    private String jdkVersion;

    /**
     * SpringCloudVersion
     */
    private String springCloudVersion;

    /**
     * SpringCloudAlibabaVersion
     */
    private String springCloudAlibabaVersion;

    /**
     * SpringBootVersion
     */
    private String springBootVersion;

    /**
     * API文档组件
     */
    private String openApiVersion;

    /**
     * 简化对象lombok
     */
    private String lombokVersion;

    /**
     * 数据访问监控Druid
     */
    private String druidVersion;

    /**
     * 数据库框架MyBatis版本
     */
    private String mybatisVersion;

    /**
     * 备注
     */
    private String remark;

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
     * 项目群 DO 转换 BO
     */
    public static ProjectGroupBO toBO(ProjectGroupDO dbo) {
        if (dbo != null) {
            return ProjectGroupBO.builder()
                    .id(dbo.getId())
                    .managerId(dbo.getManagerId())
                    .projectGroupName(dbo.getProjectGroupName())
                    .groupId(dbo.getGroupId())
                    .artifactId(dbo.getArtifactId())
                    .version(dbo.getVersion())
                    .jdkVersion(dbo.getJdkVersion())
                    .springCloudVersion(dbo.getSpringCloudVersion())
                    .springCloudAlibabaVersion(dbo.getSpringCloudAlibabaVersion())
                    .springBootVersion(dbo.getSpringBootVersion())
                    .openApiVersion(dbo.getOpenApiVersion())
                    .lombokVersion(dbo.getLombokVersion())
                    .druidVersion(dbo.getDruidVersion())
                    .mybatisVersion(dbo.getMybatisVersion())
                    .remark(dbo.getRemark())
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
     * 项目群 DO 数组 转换 BO List
     */
    public static List<ProjectGroupBO> toBO(ProjectGroupDO[] dbos) {
        if (dbos != null) {
            List<ProjectGroupBO> bos = new ArrayList<>();
            for (ProjectGroupDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 DO List 转换 BO List
     */
    public static List<ProjectGroupBO> toBO(List<ProjectGroupDO> dbos) {
        if (dbos != null) {
            List<ProjectGroupBO> bos = new ArrayList<>();
            for (ProjectGroupDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 BO 转换 DO
     */
    public static ProjectGroupDO toDO(ProjectGroupBO bo) {
        if (bo != null) {
            return ProjectGroupDO.builder()
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
     * 项目群 BO List 转换 DO List
     */
    public static List<ProjectGroupDO> toDO(List<ProjectGroupBO> bos) {
        if (bos != null) {
            List<ProjectGroupDO> dbos = new ArrayList<>();
            for (ProjectGroupBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 BO 转换 DO
     */
    public ProjectGroupDO toDO() {
        return ProjectGroupDO.builder()
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