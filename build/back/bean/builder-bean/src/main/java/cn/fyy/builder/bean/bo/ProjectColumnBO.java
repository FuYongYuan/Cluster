package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.ProjectColumnDO;
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
 * 项目表字段
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnBO implements Serializable {
    /**
     * 主键ID
     */
    private BigInteger id;

    /**
     * 项目表ID
     */
    private BigInteger projectTableId;

    /**
     * 所属的库
     */
    private String tableSchema;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 是否可以为空
     */
    private String isNullable;

    /**
     * 数据类型(不包括长度)
     */
    private String dataType;

    /**
     * 数据长度varchar
     */
    private Integer characterMaximumLength;

    /**
     * 数字类型的整型部分长度
     */
    private Integer numericPrecision;

    /**
     * 数字类型的小数部分长度
     */
    private Integer numericScale;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * PRI为主键，MUL为外键
     */
    private String columnKey;

    /**
     * 自增标识
     */
    private String extra;

    /**
     * 字段注释
     */
    private String columnComment;

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
     * 项目表字段 DO 转换 BO
     */
    public static ProjectColumnBO toBO(ProjectColumnDO dbo) {
        if (dbo != null) {
            return ProjectColumnBO.builder()
                    .id(dbo.getId())
                    .projectTableId(dbo.getProjectTableId())
                    .tableSchema(dbo.getTableSchema())
                    .tableName(dbo.getTableName())
                    .columnName(dbo.getColumnName())
                    .isNullable(dbo.getIsNullable())
                    .dataType(dbo.getDataType())
                    .characterMaximumLength(dbo.getCharacterMaximumLength())
                    .numericPrecision(dbo.getNumericPrecision())
                    .numericScale(dbo.getNumericScale())
                    .columnType(dbo.getColumnType())
                    .columnKey(dbo.getColumnKey())
                    .extra(dbo.getExtra())
                    .columnComment(dbo.getColumnComment())
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
     * 项目表字段 DO 数组 转换 BO List
     */
    public static List<ProjectColumnBO> toBO(ProjectColumnDO[] dbos) {
        if (dbos != null) {
            List<ProjectColumnBO> bos = new ArrayList<>();
            for (ProjectColumnDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 DO List 转换 BO List
     */
    public static List<ProjectColumnBO> toBO(List<ProjectColumnDO> dbos) {
        if (dbos != null) {
            List<ProjectColumnBO> bos = new ArrayList<>();
            for (ProjectColumnDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 BO 转换 DO
     */
    public static ProjectColumnDO toDO(ProjectColumnBO bo) {
        if (bo != null) {
            return ProjectColumnDO.builder()
                    .id(bo.getId())
                    .projectTableId(bo.getProjectTableId())
                    .tableSchema(bo.getTableSchema())
                    .tableName(bo.getTableName())
                    .columnName(bo.getColumnName())
                    .isNullable(bo.getIsNullable())
                    .dataType(bo.getDataType())
                    .characterMaximumLength(bo.getCharacterMaximumLength())
                    .numericPrecision(bo.getNumericPrecision())
                    .numericScale(bo.getNumericScale())
                    .columnType(bo.getColumnType())
                    .columnKey(bo.getColumnKey())
                    .extra(bo.getExtra())
                    .columnComment(bo.getColumnComment())
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
     * 项目表字段 BO List 转换 DO List
     */
    public static List<ProjectColumnDO> toDO(List<ProjectColumnBO> bos) {
        if (bos != null) {
            List<ProjectColumnDO> dbos = new ArrayList<>();
            for (ProjectColumnBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 BO 转换 DO
     */
    public ProjectColumnDO toDO() {
        return ProjectColumnDO.builder()
                .id(this.getId())
                .projectTableId(this.getProjectTableId())
                .tableSchema(this.getTableSchema())
                .tableName(this.getTableName())
                .columnName(this.getColumnName())
                .isNullable(this.getIsNullable())
                .dataType(this.getDataType())
                .characterMaximumLength(this.getCharacterMaximumLength())
                .numericPrecision(this.getNumericPrecision())
                .numericScale(this.getNumericScale())
                .columnType(this.getColumnType())
                .columnKey(this.getColumnKey())
                .extra(this.getExtra())
                .columnComment(this.getColumnComment())
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