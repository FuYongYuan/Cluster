package cn.fyy.capability.bean.dto;

import cn.fyy.capability.bean.bo.ApiBO;
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
 * API信息 DTO
 *
 * @author fyy
 */
@Schema(name = "ApiDTO", description = "API信息 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiDTO implements Serializable {

    /**
     * 主键 ID
     */
    @Schema(name = "id", description = "主键 ID", type = "Long")
    private Long id;

    /**
     * 类名
     */
    @Schema(name = "className", description = "类名", type = "String")
    private String className;

    /**
     * 方法名
     */
    @Schema(name = "methodName", description = "方法名", type = "String")
    private String methodName;

    /**
     * Mapping值
     */
    @Schema(name = "mapping", description = "Mapping值", type = "String")
    private String mapping;

    /**
     * Swagger说明
     */
    @Schema(name = "summary", description = "Swagger说明", type = "String")
    private String summary;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人 ID
     */
    @Schema(name = "creatorId", description = "创建人 ID", type = "Long")
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
     * 更新人 ID
     */
    @Schema(name = "updaterId", description = "修改人 ID", type = "Long")
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
     * API BO 转换 DTO
     */
    public static ApiDTO toDTO(ApiBO bo) {
        if (bo != null) {
            return ApiDTO.builder()
                    .id(bo.getId())
                    .className(bo.getClassName())
                    .methodName(bo.getMethodName())
                    .mapping(bo.getMapping())
                    .summary(bo.getSummary())
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
     * API BO 数组 转换 DTO List
     */
    public static List<ApiDTO> toDTO(ApiBO[] bos) {
        if (bos != null) {
            List<ApiDTO> dtos = new ArrayList<>();
            for (ApiBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * API BO List 转换 DTO List
     */
    public static List<ApiDTO> toDTO(List<ApiBO> bos) {
        if (bos != null) {
            List<ApiDTO> dtos = new ArrayList<>();
            for (ApiBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * API DTO 转换 BO
     */
    public static ApiBO toBO(ApiDTO dto) {
        if (dto != null) {
            return ApiBO.builder()
                    .id(dto.getId())
                    .className(dto.getClassName())
                    .methodName(dto.getMethodName())
                    .mapping(dto.getMapping())
                    .summary(dto.getSummary())
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
     * API DTO List 转换 BO List
     */
    public static List<ApiBO> toBO(List<ApiDTO> dtos) {
        if (dtos != null) {
            List<ApiBO> bos = new ArrayList<>();
            for (ApiDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * API DTO 转换 BO
     */
    public ApiBO toBO() {
        return ApiBO.builder()
                .id(this.getId())
                .className(this.getClassName())
                .methodName(this.getMethodName())
                .mapping(this.getMapping())
                .summary(this.getSummary())
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
