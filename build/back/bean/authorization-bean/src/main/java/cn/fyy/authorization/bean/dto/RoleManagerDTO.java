package cn.fyy.authorization.bean.dto;

import cn.fyy.authorization.bean.bo.RoleManagerBO;
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
 * 角色管理员关系
 *
 * @author fuyy
 */
@Schema(name = "RoleManagerDTO", description = "角色管理员关系 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleManagerDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long")
    private Long id;

    /**
     * 管理员ID
     */
    @Schema(name = "managerId", description = "管理员ID", type = "Long")
    private Long managerId;

    /**
     * 角色ID
     */
    @Schema(name = "roleId", description = "角色ID", type = "Long")
    private Long roleId;

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
     * 角色管理员关系 BO 转换 DTO
     */
    public static RoleManagerDTO toDTO(RoleManagerBO bo) {
        if (bo != null) {
            return RoleManagerDTO.builder()
                    .id(bo.getId())
                    .managerId(bo.getManagerId())
                    .roleId(bo.getRoleId())
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
     * 角色管理员关系 BO 数组 转换 DTO List
     */
    public static List<RoleManagerDTO> toDTO(RoleManagerBO[] bos) {
        if (bos != null) {
            List<RoleManagerDTO> dtos = new ArrayList<>();
            for (RoleManagerBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 BO List 转换 DTO List
     */
    public static List<RoleManagerDTO> toDTO(List<RoleManagerBO> bos) {
        if (bos != null) {
            List<RoleManagerDTO> dtos = new ArrayList<>();
            for (RoleManagerBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 DTO 转换 BO
     */
    public static RoleManagerBO toBO(RoleManagerDTO dto) {
        if (dto != null) {
            return RoleManagerBO.builder()
                    .id(dto.getId())
                    .managerId(dto.getManagerId())
                    .roleId(dto.getRoleId())
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
     * 角色管理员关系 DTO List 转换 BO List
     */
    public static List<RoleManagerBO> toBO(List<RoleManagerDTO> dtos) {
        if (dtos != null) {
            List<RoleManagerBO> bos = new ArrayList<>();
            for (RoleManagerDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色管理员关系 DTO 转换 BO
     */
    public RoleManagerBO toBO() {
        return RoleManagerBO.builder()
                .id(this.getId())
                .managerId(this.getManagerId())
                .roleId(this.getRoleId())
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
