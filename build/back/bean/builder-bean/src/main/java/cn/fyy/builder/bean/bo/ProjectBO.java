package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.ProjectDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目群ID
     */
    private Long projectGroupId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 工件ID
     */
    private String artifactId;

    /**
     * 项目包目录
     */
    private String packageName;

    /**
     * 项目版本
     */
    private String projectVersion;

    /**
     * 项目的描述
     */
    private String projectDescription;

    /**
     * 项目访问路径
     */
    private String projectUrl;

    /**
     * 项目访问端口
     */
    private String projectPort;

    /**
     * 是否Service创建接口(0.否 1.是)
     */
    private Integer isServiceInterface;

    /**
     * 是否去除BO层(0.否 1.是)
     */
    private Integer isDeleteBo;

    /**
     * 是否生成基础方法(0.否 1.是)
     */
    private Integer isBasisMethod;

    /**
     * 是否使用Redis缓存(0.否 1.是)
     */
    private Integer isRedis;

    /**
     * 是否使用MinIO文件服务器(0.否 1.是)
     */
    private Integer isMinio;

    /**
     * 是否使用Mail能力(0.否 1.是)
     */
    private Integer isMail;

    /**
     * 是否使用内容处理能力(0.否 1.是)
     */
    private Integer isDispose;

    /**
     * 是否使用加密能力(0.否 1.是)
     */
    private Integer isEncrypt;

    /**
     * 是否使用Excel能力(0.否 1.是)
     */
    private Integer isExcel;

    /**
     * Druid访问路径
     */
    private String druidUrl;

    /**
     * Druid登录账号
     */
    private String druidAccount;

    /**
     * Druid登录密码
     */
    private String druidPassword;

    /**
     * 是否去除无主键的表(0.否 1.是)
     */
    private Integer isDeleteNotPk;

    /**
     * 是否吧主键生成时放第一位(0.否 1.是)
     */
    private Integer isOrderKey;

    /**
     * 数据层框架类型(0.不使用数据源 1.JPA 2.Mybatis)
     */
    private Integer dataBaseFramework;

    /**
     * 数据库格式设定(0.没有格式转换 1.X_X_Xxxx => XXXXxxx)
     */
    private Integer dataBaseFormat;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long creatorId;

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
    private Long updaterId;

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
     * 项目 DO 转换 BO
     */
    public static ProjectBO toBO(ProjectDO dbo) {
        if (dbo != null) {
            return ProjectBO.builder()
                    .id(dbo.getId())
                    .projectGroupId(dbo.getProjectGroupId())
                    .projectName(dbo.getProjectName())
                    .artifactId(dbo.getArtifactId())
                    .packageName(dbo.getPackageName())
                    .projectVersion(dbo.getProjectVersion())
                    .projectDescription(dbo.getProjectDescription())
                    .projectUrl(dbo.getProjectUrl())
                    .projectPort(dbo.getProjectPort())
                    .isServiceInterface(dbo.getIsServiceInterface())
                    .isDeleteBo(dbo.getIsDeleteBo())
                    .isBasisMethod(dbo.getIsBasisMethod())
                    .isRedis(dbo.getIsRedis())
                    .isMinio(dbo.getIsMinio())
                    .isMail(dbo.getIsMail())
                    .isDispose(dbo.getIsDispose())
                    .isEncrypt(dbo.getIsEncrypt())
                    .isExcel(dbo.getIsExcel())
                    .druidUrl(dbo.getDruidUrl())
                    .druidAccount(dbo.getDruidAccount())
                    .druidPassword(dbo.getDruidPassword())
                    .isDeleteNotPk(dbo.getIsDeleteNotPk())
                    .isOrderKey(dbo.getIsOrderKey())
                    .dataBaseFramework(dbo.getDataBaseFramework())
                    .dataBaseFormat(dbo.getDataBaseFormat())
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
     * 项目 DO 数组 转换 BO List
     */
    public static List<ProjectBO> toBO(ProjectDO[] dbos) {
        if (dbos != null) {
            List<ProjectBO> bos = new ArrayList<>();
            for (ProjectDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目 DO List 转换 BO List
     */
    public static List<ProjectBO> toBO(List<ProjectDO> dbos) {
        if (dbos != null) {
            List<ProjectBO> bos = new ArrayList<>();
            for (ProjectDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目 BO 转换 DO
     */
    public static ProjectDO toDO(ProjectBO bo) {
        if (bo != null) {
            return ProjectDO.builder()
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
     * 项目 BO List 转换 DO List
     */
    public static List<ProjectDO> toDO(List<ProjectBO> bos) {
        if (bos != null) {
            List<ProjectDO> dbos = new ArrayList<>();
            for (ProjectBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 项目 BO 转换 DO
     */
    public ProjectDO toDO() {
        return ProjectDO.builder()
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