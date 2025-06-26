package cn.fyy.builder.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 常用版本
 *
 * @author fuyy
 */
@Entity
@Table(name = "commonly_version", schema = "builder")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonlyVersionDO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 管理员ID
     */
    @Column(name = "manager_id")
    private Long managerId;

    /**
     * JDK版本
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
     * API文档组件
     */
    @Column(name = "open_api_version", length = 100)
    private String openApiVersion;

    /**
     * 简化对象lombok
     */
    @Column(name = "lombok_version", length = 100)
    private String lombokVersion;

    /**
     * 数据访问监控Druid
     */
    @Column(name = "druid_version", length = 100)
    private String druidVersion;

    /**
     * 数据库框架MyBatis版本
     */
    @Column(name = "mybatis_version", length = 100)
    private String mybatisVersion;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 修改人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Column(name = "state")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommonlyVersionDO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(managerId, that.managerId)) {
            return false;
        }
        if (!Objects.equals(jdkVersion, that.jdkVersion)) {
            return false;
        }
        if (!Objects.equals(springCloudVersion, that.springCloudVersion)) {
            return false;
        }
        if (!Objects.equals(springCloudAlibabaVersion, that.springCloudAlibabaVersion)) {
            return false;
        }
        if (!Objects.equals(springBootVersion, that.springBootVersion)) {
            return false;
        }
        if (!Objects.equals(openApiVersion, that.openApiVersion)) {
            return false;
        }
        if (!Objects.equals(lombokVersion, that.lombokVersion)) {
            return false;
        }
        if (!Objects.equals(druidVersion, that.druidVersion)) {
            return false;
        }
        if (!Objects.equals(mybatisVersion, that.mybatisVersion)) {
            return false;
        }
        if (!Objects.equals(createTime, that.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, that.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, that.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, that.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, that.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, that.updaterName)) {
            return false;
        }
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (jdkVersion != null ? jdkVersion.hashCode() : 0);
        result = 31 * result + (springCloudVersion != null ? springCloudVersion.hashCode() : 0);
        result = 31 * result + (springCloudAlibabaVersion != null ? springCloudAlibabaVersion.hashCode() : 0);
        result = 31 * result + (springBootVersion != null ? springBootVersion.hashCode() : 0);
        result = 31 * result + (openApiVersion != null ? openApiVersion.hashCode() : 0);
        result = 31 * result + (lombokVersion != null ? lombokVersion.hashCode() : 0);
        result = 31 * result + (druidVersion != null ? druidVersion.hashCode() : 0);
        result = 31 * result + (mybatisVersion != null ? mybatisVersion.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updaterName != null ? updaterName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}