package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.ProjectColumnBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "project_column", description = "项目表字段 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long", nullable = false)
    private Long id;

    /**
     * 项目表ID
     */
    @Schema(name = "projectTableId", description = "项目表ID", type = "Long")
    private Long projectTableId;

    /**
     * 所属的库
     */
    @Schema(name = "tableSchema", description = "所属的库", type = "String")
    private String tableSchema;

    /**
     * 表名称
     */
    @Schema(name = "tableName", description = "表名称", type = "String")
    private String tableName;

    /**
     * 字段名
     */
    @Schema(name = "columnName", description = "字段名", type = "String")
    private String columnName;

    /**
     * 是否可以为空
     */
    @Schema(name = "isNullable", description = "是否可以为空", type = "String")
    private String isNullable;

    /**
     * 数据类型(不包括长度)
     */
    @Schema(name = "dataType", description = "数据类型(不包括长度)", type = "String")
    private String dataType;

    /**
     * 数据长度varchar
     */
    @Schema(name = "characterMaximumLength", description = "数据长度varchar", type = "Integer")
    private Integer characterMaximumLength;

    /**
     * 数字类型的整型部分长度
     */
    @Schema(name = "numericPrecision", description = "数字类型的整型部分长度", type = "Integer")
    private Integer numericPrecision;

    /**
     * 数字类型的小数部分长度
     */
    @Schema(name = "numericScale", description = "数字类型的小数部分长度", type = "Integer")
    private Integer numericScale;

    /**
     * 字段类型
     */
    @Schema(name = "columnType", description = "字段类型", type = "String")
    private String columnType;

    /**
     * PRI为主键，MUL为外键
     */
    @Schema(name = "columnKey", description = "PRI为主键，MUL为外键", type = "String")
    private String columnKey;

    /**
     * 自增标识
     */
    @Schema(name = "extra", description = "自增标识", type = "String")
    private String extra;

    /**
     * 字段注释
     */
    @Schema(name = "columnComment", description = "字段注释", type = "String")
    private String columnComment;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Schema(name = "creatorId", description = "创建人ID", type = "Long")
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Schema(name = "creatorName", description = "创建人名称", type = "String")
    private String creatorName;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "Long")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Schema(name = "updaterName", description = "修改人名称", type = "String")
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Byte")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 项目表字段 BO 转换 DTO
     */
    public static ProjectColumnDTO toDTO(ProjectColumnBO bo) {
        if (bo != null) {
            return ProjectColumnDTO.builder()
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
     * 项目表字段 BO 数组 转换 DTO List
     */
    public static List<ProjectColumnDTO> toDTO(ProjectColumnBO[] bos) {
        if (bos != null) {
            List<ProjectColumnDTO> dtos = new ArrayList<>();
            for (ProjectColumnBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 BO List 转换 DTO List
     */
    public static List<ProjectColumnDTO> toDTO(List<ProjectColumnBO> bos) {
        if (bos != null) {
            List<ProjectColumnDTO> dtos = new ArrayList<>();
            for (ProjectColumnBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 DTO 转换 BO
     */
    public static ProjectColumnBO toBO(ProjectColumnDTO dto) {
        if (dto != null) {
            return ProjectColumnBO.builder()
                    .id(dto.getId())
                    .projectTableId(dto.getProjectTableId())
                    .tableSchema(dto.getTableSchema())
                    .tableName(dto.getTableName())
                    .columnName(dto.getColumnName())
                    .isNullable(dto.getIsNullable())
                    .dataType(dto.getDataType())
                    .characterMaximumLength(dto.getCharacterMaximumLength())
                    .numericPrecision(dto.getNumericPrecision())
                    .numericScale(dto.getNumericScale())
                    .columnType(dto.getColumnType())
                    .columnKey(dto.getColumnKey())
                    .extra(dto.getExtra())
                    .columnComment(dto.getColumnComment())
                    .createTime(dto.getCreateTime())
                    .creatorId(dto.getCreatorId())
                    .creatorName(dto.getCreatorName())
                    .updateTime(dto.getUpdateTime())
                    .updaterId(dto.getUpdaterId())
                    .updaterName(dto.getUpdaterName())
                    .state(dto.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 DTO List 转换 BO List
     */
    public static List<ProjectColumnBO> toBO(List<ProjectColumnDTO> dtos) {
        if (dtos != null) {
            List<ProjectColumnBO> bos = new ArrayList<>();
            for (ProjectColumnDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 项目表字段 DTO 转换 BO
     */
    public ProjectColumnBO toBO() {
        return ProjectColumnBO.builder()
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