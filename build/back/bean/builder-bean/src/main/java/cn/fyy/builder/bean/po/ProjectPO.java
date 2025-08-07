package cn.fyy.builder.bean.po;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
public class ProjectPO implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目群ID
     */
    @Column(name = "project_group_id")
    private Long projectGroupId;

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
     * 是否Service创建接口;（0.否、1.是）
     */
    @Column(name = "is_service_interface")
    private Byte isServiceInterface;

    /**
     * 是否去除BO层;（0.否、1.是）
     */
    @Column(name = "is_delete_bo")
    private Byte isDeleteBo;

    /**
     * 是否生成基础方法;（0.否、1.是）
     */
    @Column(name = "is_basis_method")
    private Byte isBasisMethod;

    /**
     * 是否使用Redis缓存;（0.否、1.是）
     */
    @Column(name = "is_redis")
    private Byte isRedis;

    /**
     * 是否使用MinIO文件服务器;（0.否、1.是）
     */
    @Column(name = "is_minio")
    private Byte isMinio;

    /**
     * 是否使用Mail能力;（0.否、1.是）
     */
    @Column(name = "is_mail")
    private Byte isMail;

    /**
     * 是否使用内容处理能力;（0.否、1.是）
     */
    @Column(name = "is_dispose")
    private Byte isDispose;

    /**
     * 是否使用加密能力;（0.否、1.是）
     */
    @Column(name = "is_encrypt")
    private Byte isEncrypt;

    /**
     * 是否使用Excel能力;（0.否、1.是）
     */
    @Column(name = "is_excel")
    private Byte isExcel;

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
     * 是否去除无主键的表;（0.否、1.是）
     */
    @Column(name = "is_delete_not_pk")
    private Byte isDeleteNotPk;

    /**
     * 是否吧主键生成时放第一位;（0.否、1.是）
     */
    @Column(name = "is_order_key")
    private Byte isOrderKey;

    /**
     * 数据层框架类型;（0.不使用数据源、1.JPA、2.Mybatis）
     */
    @Column(name = "data_base_framework")
    private Byte dataBaseFramework;

    /**
     * 数据库格式设定;（0.没有格式转换、1.X_X_Xxxx => XXXXxxx）
     */
    @Column(name = "data_base_format")
    private Byte dataBaseFormat;

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
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Column(name = "state")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectPO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(projectGroupId, that.projectGroupId)) {
            return false;
        }
        if (!Objects.equals(projectName, that.projectName)) {
            return false;
        }
        if (!Objects.equals(artifactId, that.artifactId)) {
            return false;
        }
        if (!Objects.equals(packageName, that.packageName)) {
            return false;
        }
        if (!Objects.equals(projectVersion, that.projectVersion)) {
            return false;
        }
        if (!Objects.equals(projectDescription, that.projectDescription)) {
            return false;
        }
        if (!Objects.equals(projectUrl, that.projectUrl)) {
            return false;
        }
        if (!Objects.equals(projectPort, that.projectPort)) {
            return false;
        }
        if (!Objects.equals(isServiceInterface, that.isServiceInterface)) {
            return false;
        }
        if (!Objects.equals(isDeleteBo, that.isDeleteBo)) {
            return false;
        }
        if (!Objects.equals(isBasisMethod, that.isBasisMethod)) {
            return false;
        }
        if (!Objects.equals(isRedis, that.isRedis)) {
            return false;
        }
        if (!Objects.equals(isMinio, that.isMinio)) {
            return false;
        }
        if (!Objects.equals(isMail, that.isMail)) {
            return false;
        }
        if (!Objects.equals(isDispose, that.isDispose)) {
            return false;
        }
        if (!Objects.equals(isEncrypt, that.isEncrypt)) {
            return false;
        }
        if (!Objects.equals(isExcel, that.isExcel)) {
            return false;
        }
        if (!Objects.equals(druidUrl, that.druidUrl)) {
            return false;
        }
        if (!Objects.equals(druidAccount, that.druidAccount)) {
            return false;
        }
        if (!Objects.equals(druidPassword, that.druidPassword)) {
            return false;
        }
        if (!Objects.equals(isDeleteNotPk, that.isDeleteNotPk)) {
            return false;
        }
        if (!Objects.equals(isOrderKey, that.isOrderKey)) {
            return false;
        }
        if (!Objects.equals(dataBaseFramework, that.dataBaseFramework)) {
            return false;
        }
        if (!Objects.equals(dataBaseFormat, that.dataBaseFormat)) {
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