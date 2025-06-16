package cn.fyy.dictionary.bean.dto;

import cn.fyy.dictionary.bean.bo.ParameterBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数
 *
 * @author fuyy
 */
@Schema(name = "ParameterDTO", description = "参数 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger")
    private BigInteger id;

    /**
     * 参数代码
     */
    @Schema(name = "parameterCode", description = "参数代码", type = "String")
    private String parameterCode;

    /**
     * 参数名称
     */
    @Schema(name = "parameterName", description = "参数名称", type = "String")
    private String parameterName;

    /**
     * 参数值
     */
    @Schema(name = "parameterValue", description = "参数值", type = "String", nullable = true)
    private String parameterValue;

    /**
     * 参数说明
     */
    @Schema(name = "parameterExplain", description = "参数说明", type = "String", nullable = true)
    private String parameterExplain;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Schema(name = "creatorId", description = "创建人ID", type = "BigInteger")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Schema(name = "creatorName", description = "创建人名称", type = "String")
    private String creatorName;

    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "BigInteger")
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    @Schema(name = "updaterName", description = "修改人名称", type = "String")
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Integer")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 参数 BO 转换 DTO
     */
    public static ParameterDTO toDTO(ParameterBO bo) {
        if (bo != null) {
            return ParameterDTO.builder()
                    .id(bo.getId())
                    .parameterCode(bo.getParameterCode())
                    .parameterName(bo.getParameterName())
                    .parameterValue(bo.getParameterValue())
                    .parameterExplain(bo.getParameterExplain())
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
     * 参数 BO 数组 转换 DTO List
     */
    public static List<ParameterDTO> toDTO(ParameterBO[] bos) {
        if (bos != null) {
            List<ParameterDTO> dtos = new ArrayList<>();
            for (ParameterBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO List 转换 DTO List
     */
    public static List<ParameterDTO> toDTO(List<ParameterBO> bos) {
        if (bos != null) {
            List<ParameterDTO> dtos = new ArrayList<>();
            for (ParameterBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 参数 DTO 转换 BO
     */
    public static ParameterBO toBO(ParameterDTO dto) {
        if (dto != null) {
            return ParameterBO.builder()
                    .id(dto.getId())
                    .parameterCode(dto.getParameterCode())
                    .parameterName(dto.getParameterName())
                    .parameterValue(dto.getParameterValue())
                    .parameterExplain(dto.getParameterExplain())
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
     * 参数 DTO List 转换 BO List
     */
    public static List<ParameterBO> toBO(List<ParameterDTO> dtos) {
        if (dtos != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 DTO 转换 BO
     */
    public ParameterBO toBO() {
        return ParameterBO.builder()
                .id(this.getId())
                .parameterCode(this.getParameterCode())
                .parameterName(this.getParameterName())
                .parameterValue(this.getParameterValue())
                .parameterExplain(this.getParameterExplain())
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
