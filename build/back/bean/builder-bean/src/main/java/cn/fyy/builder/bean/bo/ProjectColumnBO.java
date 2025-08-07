package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.ProjectColumnPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Long id;

    /**
     * 项目表ID
     */
    private Long projectTableId;

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
     * 项目表字段 PO 转换 BO
     */
    public static ProjectColumnBO toBO(ProjectColumnPO po) {
        if (po != null) {
            return ProjectColumnBO.builder()
                    .id(po.getId())
                    .projectTableId(po.getProjectTableId())
                    .tableSchema(po.getTableSchema())
                    .tableName(po.getTableName())
                    .columnName(po.getColumnName())
                    .isNullable(po.getIsNullable())
                    .dataType(po.getDataType())
                    .characterMaximumLength(po.getCharacterMaximumLength())
                    .numericPrecision(po.getNumericPrecision())
                    .numericScale(po.getNumericScale())
                    .columnType(po.getColumnType())
                    .columnKey(po.getColumnKey())
                    .extra(po.getExtra())
                    .columnComment(po.getColumnComment())
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
     * 项目表字段 PO 数组 转换 BO List
     */
    public static List<ProjectColumnBO> toBO(ProjectColumnPO[] pos) {
        if (pos != null) {
            List<ProjectColumnBO> bos = new ArrayList<>();
            for (ProjectColumnPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 PO List 转换 BO List
     */
    public static List<ProjectColumnBO> toBO(List<ProjectColumnPO> pos) {
        if (pos != null) {
            List<ProjectColumnBO> bos = new ArrayList<>();
            for (ProjectColumnPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 BO 转换 PO
     */
    public static ProjectColumnPO toPO(ProjectColumnBO bo) {
        if (bo != null) {
            return ProjectColumnPO.builder()
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
     * 项目表字段 BO List 转换 PO List
     */
    public static List<ProjectColumnPO> toPO(List<ProjectColumnBO> bos) {
        if (bos != null) {
            List<ProjectColumnPO> pos = new ArrayList<>();
            for (ProjectColumnBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 BO 转换 PO
     */
    public ProjectColumnPO toPO() {
        return ProjectColumnPO.builder()
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