package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.ProjectGroupPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private Long id;

    /**
     * 管理员ID
     */
    private Long managerId;

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
     * 项目群 PO 转换 BO
     */
    public static ProjectGroupBO toBO(ProjectGroupPO po) {
        if (po != null) {
            return ProjectGroupBO.builder()
                    .id(po.getId())
                    .managerId(po.getManagerId())
                    .projectGroupName(po.getProjectGroupName())
                    .groupId(po.getGroupId())
                    .artifactId(po.getArtifactId())
                    .version(po.getVersion())
                    .jdkVersion(po.getJdkVersion())
                    .springCloudVersion(po.getSpringCloudVersion())
                    .springCloudAlibabaVersion(po.getSpringCloudAlibabaVersion())
                    .springBootVersion(po.getSpringBootVersion())
                    .openApiVersion(po.getOpenApiVersion())
                    .lombokVersion(po.getLombokVersion())
                    .druidVersion(po.getDruidVersion())
                    .mybatisVersion(po.getMybatisVersion())
                    .remark(po.getRemark())
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
     * 项目群 PO 数组 转换 BO List
     */
    public static List<ProjectGroupBO> toBO(ProjectGroupPO[] pos) {
        if (pos != null) {
            List<ProjectGroupBO> bos = new ArrayList<>();
            for (ProjectGroupPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 PO List 转换 BO List
     */
    public static List<ProjectGroupBO> toBO(List<ProjectGroupPO> pos) {
        if (pos != null) {
            List<ProjectGroupBO> bos = new ArrayList<>();
            for (ProjectGroupPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 BO 转换 PO
     */
    public static ProjectGroupPO toPO(ProjectGroupBO bo) {
        if (bo != null) {
            return ProjectGroupPO.builder()
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
     * 项目群 BO List 转换 PO List
     */
    public static List<ProjectGroupPO> toPO(List<ProjectGroupBO> bos) {
        if (bos != null) {
            List<ProjectGroupPO> pos = new ArrayList<>();
            for (ProjectGroupBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 项目群 BO 转换 PO
     */
    public ProjectGroupPO toPO() {
        return ProjectGroupPO.builder()
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