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
 * 项目
 *
 * @author fuyy
 */
@Entity
@Table(name = "project", schema = "builder")
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPO extends BasePO implements Serializable {

    /**
     * 项目群 ID
     */
    @Column(name = "project_group_id")
    private Long projectGroupId;

    /**
     * 项目名称
     */
    @Column(name = "project_name", length = 200)
    private String projectName;

    /**
     * 工件 ID
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
     * 是否 Service 创建接口;（0.否、1.是）
     */
    @Column(name = "is_service_interface")
    private Byte isServiceInterface;

    /**
     * 是否去除 BO 层;（0.否、1.是）
     */
    @Column(name = "is_delete_bo")
    private Byte isDeleteBo;

    /**
     * 是否生成基础方法;（0.否、1.是）
     */
    @Column(name = "is_basis_method")
    private Byte isBasisMethod;

    /**
     * 是否使用 Redis 缓存;（0.否、1.是）
     */
    @Column(name = "is_redis")
    private Byte isRedis;

    /**
     * 是否使用 RustFS 文件服务器;（0.否、1.是）
     */
    @Column(name = "is_rustfs")
    private Byte isRustfs;

    /**
     * 是否使用 Mail 能力;（0.否、1.是）
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
     * 是否使用 Excel 能力;（0.否、1.是）
     */
    @Column(name = "is_excel")
    private Byte isExcel;

    /**
     * Druid 访问路径
     */
    @Column(name = "druid_url", length = 200)
    private String druidUrl;

    /**
     * Druid 登录账号
     */
    @Column(name = "druid_account", length = 50)
    private String druidAccount;

    /**
     * Druid 登录密码
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

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(projectGroupId, that.projectGroupId) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(artifactId, that.artifactId) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(projectVersion, that.projectVersion) &&
                Objects.equals(projectDescription, that.projectDescription) &&
                Objects.equals(projectUrl, that.projectUrl) &&
                Objects.equals(projectPort, that.projectPort) &&
                Objects.equals(isServiceInterface, that.isServiceInterface) &&
                Objects.equals(isDeleteBo, that.isDeleteBo) &&
                Objects.equals(isBasisMethod, that.isBasisMethod) &&
                Objects.equals(isRedis, that.isRedis) &&
                Objects.equals(isRustfs, that.isRustfs) &&
                Objects.equals(isMail, that.isMail) &&
                Objects.equals(isDispose, that.isDispose) &&
                Objects.equals(isEncrypt, that.isEncrypt) &&
                Objects.equals(isExcel, that.isExcel) &&
                Objects.equals(druidUrl, that.druidUrl) &&
                Objects.equals(druidAccount, that.druidAccount) &&
                Objects.equals(druidPassword, that.druidPassword) &&
                Objects.equals(isDeleteNotPk, that.isDeleteNotPk) &&
                Objects.equals(isOrderKey, that.isOrderKey) &&
                Objects.equals(dataBaseFramework, that.dataBaseFramework) &&
                Objects.equals(dataBaseFormat, that.dataBaseFormat);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(projectGroupId);
        result = 31 * result + Objects.hashCode(projectName);
        result = 31 * result + Objects.hashCode(artifactId);
        result = 31 * result + Objects.hashCode(packageName);
        result = 31 * result + Objects.hashCode(projectVersion);
        result = 31 * result + Objects.hashCode(projectDescription);
        result = 31 * result + Objects.hashCode(projectUrl);
        result = 31 * result + Objects.hashCode(projectPort);
        result = 31 * result + Objects.hashCode(isServiceInterface);
        result = 31 * result + Objects.hashCode(isDeleteBo);
        result = 31 * result + Objects.hashCode(isBasisMethod);
        result = 31 * result + Objects.hashCode(isRedis);
        result = 31 * result + Objects.hashCode(isRustfs);
        result = 31 * result + Objects.hashCode(isMail);
        result = 31 * result + Objects.hashCode(isDispose);
        result = 31 * result + Objects.hashCode(isEncrypt);
        result = 31 * result + Objects.hashCode(isExcel);
        result = 31 * result + Objects.hashCode(druidUrl);
        result = 31 * result + Objects.hashCode(druidAccount);
        result = 31 * result + Objects.hashCode(druidPassword);
        result = 31 * result + Objects.hashCode(isDeleteNotPk);
        result = 31 * result + Objects.hashCode(isOrderKey);
        result = 31 * result + Objects.hashCode(dataBaseFramework);
        result = 31 * result + Objects.hashCode(dataBaseFormat);
        return result;
    }
}