package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.CommonlyVersionDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
    private BigInteger id;

    /**
     * 管理员ID
     */
    private BigInteger managerId;

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
    private Date createTime;

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
    private Date updateTime;

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
     * 常用版本 DO 转换 BO
     */
    public static CommonlyVersionBO toBO(CommonlyVersionDO dbo) {
        if (dbo != null) {
            return CommonlyVersionBO.builder()
                    .id(dbo.getId())
                    .managerId(dbo.getManagerId())
                    .jdkVersion(dbo.getJdkVersion())
                    .springCloudVersion(dbo.getSpringCloudVersion())
                    .springCloudAlibabaVersion(dbo.getSpringCloudAlibabaVersion())
                    .springBootVersion(dbo.getSpringBootVersion())
                    .openApiVersion(dbo.getOpenApiVersion())
                    .lombokVersion(dbo.getLombokVersion())
                    .druidVersion(dbo.getDruidVersion())
                    .mybatisVersion(dbo.getMybatisVersion())
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
     * 常用版本 DO 数组 转换 BO List
     */
    public static List<CommonlyVersionBO> toBO(CommonlyVersionDO[] dbos) {
        if (dbos != null) {
            List<CommonlyVersionBO> bos = new ArrayList<>();
            for (CommonlyVersionDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 DO List 转换 BO List
     */
    public static List<CommonlyVersionBO> toBO(List<CommonlyVersionDO> dbos) {
        if (dbos != null) {
            List<CommonlyVersionBO> bos = new ArrayList<>();
            for (CommonlyVersionDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 BO 转换 DO
     */
    public static CommonlyVersionDO toDO(CommonlyVersionBO bo) {
        if (bo != null) {
            return CommonlyVersionDO.builder()
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
     * 常用版本 BO List 转换 DO List
     */
    public static List<CommonlyVersionDO> toDO(List<CommonlyVersionBO> bos) {
        if (bos != null) {
            List<CommonlyVersionDO> dbos = new ArrayList<>();
            for (CommonlyVersionBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 常用版本 BO 转换 DO
     */
    public CommonlyVersionDO toDO() {
        return CommonlyVersionDO.builder()
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