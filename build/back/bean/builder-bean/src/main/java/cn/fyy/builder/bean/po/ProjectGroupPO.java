package cn.fyy.builder.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * 项目群
 *
 * @author fyy
 */
@Entity
@Table(name = "project_group", schema = "builder")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroupPO extends BasePO implements Serializable {

    /**
     * 管理员 ID
     */
    @Column(name = "manager_id")
    private Long managerId;

    /**
     * 项目群名称
     */
    @Column(name = "project_group_name", length = 200)
    private String projectGroupName;

    /**
     * 组 ID
     */
    @Column(name = "group_id", length = 100)
    private String groupId;

    /**
     * 工件 ID
     */
    @Column(name = "artifact_id", length = 100)
    private String artifactId;

    /**
     * 项目群版本号
     */
    @Column(name = "group_version", length = 100)
    private String groupVersion;

    /**
     * JDK 版本
     */
    @Column(name = "jdk_version", length = 100)
    private String jdkVersion;

    /**
     * SpringCloudVersion
     */
    @Column(name = "spring_cloud_version", length = 100)
    private String springCloudVersion;

    /**
     * SpringCloudAlibabaVersion
     */
    @Column(name = "spring_cloud_alibaba_version", length = 100)
    private String springCloudAlibabaVersion;

    /**
     * SpringBootVersion
     */
    @Column(name = "spring_boot_version", length = 100)
    private String springBootVersion;

    /**
     * API 文档组件
     */
    @Column(name = "open_api_version", length = 100)
    private String openApiVersion;

    /**
     * 简化对象 lombok
     */
    @Column(name = "lombok_version", length = 100)
    private String lombokVersion;

    /**
     * 数据访问监控 Druid
     */
    @Column(name = "druid_version", length = 100)
    private String druidVersion;

    /**
     * 数据库框架 MyBatis 版本
     */
    @Column(name = "mybatis_version", length = 100)
    private String mybatisVersion;

    /**
     * 备注
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectGroupPO that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(projectGroupName, that.projectGroupName) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(artifactId, that.artifactId) &&
                Objects.equals(groupVersion, that.groupVersion) &&
                Objects.equals(jdkVersion, that.jdkVersion) &&
                Objects.equals(springCloudVersion, that.springCloudVersion) &&
                Objects.equals(springCloudAlibabaVersion, that.springCloudAlibabaVersion) &&
                Objects.equals(springBootVersion, that.springBootVersion) &&
                Objects.equals(openApiVersion, that.openApiVersion) &&
                Objects.equals(lombokVersion, that.lombokVersion) &&
                Objects.equals(druidVersion, that.druidVersion) &&
                Objects.equals(mybatisVersion, that.mybatisVersion) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(managerId);
        result = 31 * result + Objects.hashCode(projectGroupName);
        result = 31 * result + Objects.hashCode(groupId);
        result = 31 * result + Objects.hashCode(artifactId);
        result = 31 * result + Objects.hashCode(groupVersion);
        result = 31 * result + Objects.hashCode(jdkVersion);
        result = 31 * result + Objects.hashCode(springCloudVersion);
        result = 31 * result + Objects.hashCode(springCloudAlibabaVersion);
        result = 31 * result + Objects.hashCode(springBootVersion);
        result = 31 * result + Objects.hashCode(openApiVersion);
        result = 31 * result + Objects.hashCode(lombokVersion);
        result = 31 * result + Objects.hashCode(druidVersion);
        result = 31 * result + Objects.hashCode(mybatisVersion);
        result = 31 * result + Objects.hashCode(remark);
        return result;
    }
}