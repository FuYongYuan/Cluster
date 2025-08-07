package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.CommonlyVersionPO;
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
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonlyVersionBO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 管理员ID
     */
    private Long managerId;

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
     * 常用版本 PO 转换 BO
     */
    public static CommonlyVersionBO toBO(CommonlyVersionPO po) {
        if (po != null) {
            return CommonlyVersionBO.builder()
                    .id(po.getId())
                    .managerId(po.getManagerId())
                    .jdkVersion(po.getJdkVersion())
                    .springCloudVersion(po.getSpringCloudVersion())
                    .springCloudAlibabaVersion(po.getSpringCloudAlibabaVersion())
                    .springBootVersion(po.getSpringBootVersion())
                    .openApiVersion(po.getOpenApiVersion())
                    .lombokVersion(po.getLombokVersion())
                    .druidVersion(po.getDruidVersion())
                    .mybatisVersion(po.getMybatisVersion())
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
     * 常用版本 PO 数组 转换 BO List
     */
    public static List<CommonlyVersionBO> toBO(CommonlyVersionPO[] pos) {
        if (pos != null) {
            List<CommonlyVersionBO> bos = new ArrayList<>();
            for (CommonlyVersionPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 PO List 转换 BO List
     */
    public static List<CommonlyVersionBO> toBO(List<CommonlyVersionPO> pos) {
        if (pos != null) {
            List<CommonlyVersionBO> bos = new ArrayList<>();
            for (CommonlyVersionPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 BO 转换 PO
     */
    public static CommonlyVersionPO toPO(CommonlyVersionBO bo) {
        if (bo != null) {
            return CommonlyVersionPO.builder()
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
     * 常用版本 BO List 转换 PO List
     */
    public static List<CommonlyVersionPO> toPO(List<CommonlyVersionBO> bos) {
        if (bos != null) {
            List<CommonlyVersionPO> pos = new ArrayList<>();
            for (CommonlyVersionBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 BO 转换 PO
     */
    public CommonlyVersionPO toPO() {
        return CommonlyVersionPO.builder()
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