package cn.fyy.builder.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 项目
 *
 * @author fuyy
 */
@Entity
@Table(name = "project", schema = "builder")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    /**
     * 项目群ID
     */
    @Column(name = "project_group_id")
    private BigInteger projectGroupId;

    /**
     * 项目名称
     */
    @Column(name = "project_name", length = 200)
    private String projectName;

    /**
     * 工件ID
     */
    @Column(name = "artifact_id", length = 100)
    private String artifactId;

    /**
     * 项目包目录
     */
    @Column(name = "package_name", length = 100)
    private String packageName;

    /**
     * 项目版本
     */
    @Column(name = "project_version", length = 100)
    private String projectVersion;

    /**
     * 项目的描述
     */
    @Column(name = "project_description", length = 4000)
    private String projectDescription;

    /**
     * 项目访问路径
     */
    @Column(name = "project_url", length = 100)
    private String projectUrl;

    /**
     * 项目访问端口
     */
    @Column(name = "project_port", length = 5)
    private String projectPort;

    /**
     * 是否Service创建接口(0.否 1.是)
     */
    @Column(name = "is_service_interface")
    private Integer isServiceInterface;

    /**
     * 是否去除BO层(0.否 1.是)
     */
    @Column(name = "is_delete_bo")
    private Integer isDeleteBo;

    /**
     * 是否生成基础方法(0.否 1.是)
     */
    @Column(name = "is_basis_method")
    private Integer isBasisMethod;

    /**
     * 是否使用Redis缓存(0.否 1.是)
     */
    @Column(name = "is_redis")
    private Integer isRedis;

    /**
     * 是否使用MinIO文件服务器(0.否 1.是)
     */
    @Column(name = "is_minio")
    private Integer isMinio;

    /**
     * 是否使用Mail能力(0.否 1.是)
     */
    @Column(name = "is_mail")
    private Integer isMail;

    /**
     * 是否使用内容处理能力(0.否 1.是)
     */
    @Column(name = "is_dispose")
    private Integer isDispose;

    /**
     * 是否使用加密能力(0.否 1.是)
     */
    @Column(name = "is_encrypt")
    private Integer isEncrypt;

    /**
     * 是否使用Excel能力(0.否 1.是)
     */
    @Column(name = "is_excel")
    private Integer isExcel;

    /**
     * Druid访问路径
     */
    @Column(name = "druid_url", length = 200)
    private String druidUrl;

    /**
     * Druid登录账号
     */
    @Column(name = "druid_account", length = 50)
    private String druidAccount;

    /**
     * Druid登录密码
     */
    @Column(name = "druid_password", length = 50)
    private String druidPassword;

    /**
     * 是否去除无主键的表(0.否 1.是)
     */
    @Column(name = "is_delete_not_pk")
    private Integer isDeleteNotPk;

    /**
     * 是否吧主键生成时放第一位(0.否 1.是)
     */
    @Column(name = "is_order_key")
    private Integer isOrderKey;

    /**
     * 数据层框架类型(0.不使用数据源 1.JPA 2.Mybatis)
     */
    @Column(name = "data_base_framework")
    private Integer dataBaseFramework;

    /**
     * 数据库格式设定(0.没有格式转换 1.X_X_Xxxx => XXXXxxx)
     */
    @Column(name = "data_base_format")
    private Integer dataBaseFormat;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private BigInteger creatorId;

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
    private BigInteger updaterId;

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
        if (!(o instanceof ProjectDO projectDO)) {
            return false;
        }

        if (!id.equals(projectDO.id)) {
            return false;
        }
        if (!Objects.equals(projectGroupId, projectDO.projectGroupId)) {
            return false;
        }
        if (!Objects.equals(projectName, projectDO.projectName)) {
            return false;
        }
        if (!Objects.equals(artifactId, projectDO.artifactId)) {
            return false;
        }
        if (!Objects.equals(packageName, projectDO.packageName)) {
            return false;
        }
        if (!Objects.equals(projectVersion, projectDO.projectVersion)) {
            return false;
        }
        if (!Objects.equals(projectDescription, projectDO.projectDescription)) {
            return false;
        }
        if (!Objects.equals(projectUrl, projectDO.projectUrl)) {
            return false;
        }
        if (!Objects.equals(projectPort, projectDO.projectPort)) {
            return false;
        }
        if (!Objects.equals(isServiceInterface, projectDO.isServiceInterface)) {
            return false;
        }
        if (!Objects.equals(isDeleteBo, projectDO.isDeleteBo)) {
            return false;
        }
        if (!Objects.equals(isBasisMethod, projectDO.isBasisMethod)) {
            return false;
        }
        if (!Objects.equals(isRedis, projectDO.isRedis)) {
            return false;
        }
        if (!Objects.equals(isMinio, projectDO.isMinio)) {
            return false;
        }
        if (!Objects.equals(isMail, projectDO.isMail)) {
            return false;
        }
        if (!Objects.equals(isDispose, projectDO.isDispose)) {
            return false;
        }
        if (!Objects.equals(isEncrypt, projectDO.isEncrypt)) {
            return false;
        }
        if (!Objects.equals(isExcel, projectDO.isExcel)) {
            return false;
        }
        if (!Objects.equals(druidUrl, projectDO.druidUrl)) {
            return false;
        }
        if (!Objects.equals(druidAccount, projectDO.druidAccount)) {
            return false;
        }
        if (!Objects.equals(druidPassword, projectDO.druidPassword)) {
            return false;
        }
        if (!Objects.equals(isDeleteNotPk, projectDO.isDeleteNotPk)) {
            return false;
        }
        if (!Objects.equals(isOrderKey, projectDO.isOrderKey)) {
            return false;
        }
        if (!Objects.equals(dataBaseFramework, projectDO.dataBaseFramework)) {
            return false;
        }
        if (!Objects.equals(dataBaseFormat, projectDO.dataBaseFormat)) {
            return false;
        }
        if (!Objects.equals(createTime, projectDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, projectDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, projectDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, projectDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, projectDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, projectDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, projectDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (projectGroupId != null ? projectGroupId.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        result = 31 * result + (projectVersion != null ? projectVersion.hashCode() : 0);
        result = 31 * result + (projectDescription != null ? projectDescription.hashCode() : 0);
        result = 31 * result + (projectUrl != null ? projectUrl.hashCode() : 0);
        result = 31 * result + (projectPort != null ? projectPort.hashCode() : 0);
        result = 31 * result + (isServiceInterface != null ? isServiceInterface.hashCode() : 0);
        result = 31 * result + (isDeleteBo != null ? isDeleteBo.hashCode() : 0);
        result = 31 * result + (isBasisMethod != null ? isBasisMethod.hashCode() : 0);
        result = 31 * result + (isRedis != null ? isRedis.hashCode() : 0);
        result = 31 * result + (isMinio != null ? isMinio.hashCode() : 0);
        result = 31 * result + (isMail != null ? isMail.hashCode() : 0);
        result = 31 * result + (isDispose != null ? isDispose.hashCode() : 0);
        result = 31 * result + (isEncrypt != null ? isEncrypt.hashCode() : 0);
        result = 31 * result + (isExcel != null ? isExcel.hashCode() : 0);
        result = 31 * result + (druidUrl != null ? druidUrl.hashCode() : 0);
        result = 31 * result + (druidAccount != null ? druidAccount.hashCode() : 0);
        result = 31 * result + (druidPassword != null ? druidPassword.hashCode() : 0);
        result = 31 * result + (isDeleteNotPk != null ? isDeleteNotPk.hashCode() : 0);
        result = 31 * result + (isOrderKey != null ? isOrderKey.hashCode() : 0);
        result = 31 * result + (dataBaseFramework != null ? dataBaseFramework.hashCode() : 0);
        result = 31 * result + (dataBaseFormat != null ? dataBaseFormat.hashCode() : 0);
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