package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.ProjectPO;
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
     * 是否Service创建接口;（0.否、1.是）
     */
    private Byte isServiceInterface;

    /**
     * 是否去除BO层;（0.否、1.是）
     */
    private Byte isDeleteBo;

    /**
     * 是否生成基础方法;（0.否、1.是）
     */
    private Byte isBasisMethod;

    /**
     * 是否使用Redis缓存;（0.否、1.是）
     */
    private Byte isRedis;

    /**
     * 是否使用MinIO文件服务器;（0.否、1.是）
     */
    private Byte isMinio;

    /**
     * 是否使用Mail能力;（0.否、1.是）
     */
    private Byte isMail;

    /**
     * 是否使用内容处理能力;（0.否、1.是）
     */
    private Byte isDispose;

    /**
     * 是否使用加密能力;（0.否、1.是）
     */
    private Byte isEncrypt;

    /**
     * 是否使用Excel能力;（0.否、1.是）
     */
    private Byte isExcel;

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
     * 是否去除无主键的表;（0.否、1.是）
     */
    private Byte isDeleteNotPk;

    /**
     * 是否吧主键生成时放第一位;（0.否、1.是）
     */
    private Byte isOrderKey;

    /**
     * 数据层框架类型;（0.不使用数据源、1.JPA、2.Mybatis）
     */
    private Byte dataBaseFramework;

    /**
     * 数据库格式设定;（0.没有格式转换、1.X_X_Xxxx => XXXXxxx）
     */
    private Byte dataBaseFormat;

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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 更新人名称
     */
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 项目 PO 转换 BO
     */
    public static ProjectBO toBO(ProjectPO po) {
        if (po != null) {
            return ProjectBO.builder()
                    .id(po.getId())
                    .projectGroupId(po.getProjectGroupId())
                    .projectName(po.getProjectName())
                    .artifactId(po.getArtifactId())
                    .packageName(po.getPackageName())
                    .projectVersion(po.getProjectVersion())
                    .projectDescription(po.getProjectDescription())
                    .projectUrl(po.getProjectUrl())
                    .projectPort(po.getProjectPort())
                    .isServiceInterface(po.getIsServiceInterface())
                    .isDeleteBo(po.getIsDeleteBo())
                    .isBasisMethod(po.getIsBasisMethod())
                    .isRedis(po.getIsRedis())
                    .isMinio(po.getIsMinio())
                    .isMail(po.getIsMail())
                    .isDispose(po.getIsDispose())
                    .isEncrypt(po.getIsEncrypt())
                    .isExcel(po.getIsExcel())
                    .druidUrl(po.getDruidUrl())
                    .druidAccount(po.getDruidAccount())
                    .druidPassword(po.getDruidPassword())
                    .isDeleteNotPk(po.getIsDeleteNotPk())
                    .isOrderKey(po.getIsOrderKey())
                    .dataBaseFramework(po.getDataBaseFramework())
                    .dataBaseFormat(po.getDataBaseFormat())
                    .createTime(po.getCreateTime())
                    .creatorId(po.getCreatorId())
                    .creatorName(po.getCreatorName())
                    .updateTime(po.getUpdateTime())
                    .updaterId(po.getUpdaterId())
                    .updaterName(po.getUpdaterName())
                    .state(po.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 项目 PO 数组 转换 BO List
     */
    public static List<ProjectBO> toBO(ProjectPO[] pos) {
        if (pos != null) {
            List<ProjectBO> bos = new ArrayList<>();
            for (ProjectPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目 PO List 转换 BO List
     */
    public static List<ProjectBO> toBO(List<ProjectPO> pos) {
        if (pos != null) {
            List<ProjectBO> bos = new ArrayList<>();
            for (ProjectPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目 BO 转换 PO
     */
    public static ProjectPO toPO(ProjectBO bo) {
        if (bo != null) {
            return ProjectPO.builder()
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
     * 项目 BO List 转换 PO List
     */
    public static List<ProjectPO> toPO(List<ProjectBO> bos) {
        if (bos != null) {
            List<ProjectPO> pos = new ArrayList<>();
            for (ProjectBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 项目 BO 转换 PO
     */
    public ProjectPO toPO() {
        return ProjectPO.builder()
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