package cn.fyy.authorization.bean.dto;

import cn.fyy.authorization.bean.bo.RoleButtonBO;
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
 * 按钮角色关系
 *
 * @author fuyy
 */
@Schema(name = "RoleButtonDTO", description = "按钮角色关系 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleButtonDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long")
    private Long id;

    /**
     * 角色ID
     */
    @Schema(name = "roleId", description = "角色ID", type = "Long")
    private Long roleId;

    /**
     * 按钮ID
     */
    @Schema(name = "buttonId", description = "按钮ID", type = "Long")
    private Long buttonId;

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
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "Long")
    private Long updaterId;

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
     * 按钮角色关系 BO 转换 DTO
     */
    public static RoleButtonDTO toDTO(RoleButtonBO bo) {
        if (bo != null) {
            return RoleButtonDTO.builder()
                    .id(bo.getId())
                    .roleId(bo.getRoleId())
                    .buttonId(bo.getButtonId())
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
     * 按钮角色关系 BO 数组 转换 DTO List
     */
    public static List<RoleButtonDTO> toDTO(RoleButtonBO[] bos) {
        if (bos != null) {
            List<RoleButtonDTO> dtos = new ArrayList<>();
            for (RoleButtonBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 BO List 转换 DTO List
     */
    public static List<RoleButtonDTO> toDTO(List<RoleButtonBO> bos) {
        if (bos != null) {
            List<RoleButtonDTO> dtos = new ArrayList<>();
            for (RoleButtonBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 DTO 转换 BO
     */
    public static RoleButtonBO toBO(RoleButtonDTO dto) {
        if (dto != null) {
            return RoleButtonBO.builder()
                    .id(dto.getId())
                    .roleId(dto.getRoleId())
                    .buttonId(dto.getButtonId())
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
     * 按钮角色关系 DTO List 转换 BO List
     */
    public static List<RoleButtonBO> toBO(List<RoleButtonDTO> dtos) {
        if (dtos != null) {
            List<RoleButtonBO> bos = new ArrayList<>();
            for (RoleButtonDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮角色关系 DTO 转换 BO
     */
    public RoleButtonBO toBO() {
        return RoleButtonBO.builder()
                .id(this.getId())
                .roleId(this.getRoleId())
                .buttonId(this.getButtonId())
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
