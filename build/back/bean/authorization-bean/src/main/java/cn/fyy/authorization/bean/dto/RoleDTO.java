package cn.fyy.authorization.bean.dto;

import cn.fyy.authorization.bean.bo.RoleBO;
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
 * 角色
 *
 * @author fuyy
 */
@Schema(name = "RoleDTO", description = "角色 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long")
    private Long id;

    /**
     * 名称
     */
    @Schema(name = "roleName", description = "名称", type = "String")
    private String roleName;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注", type = "String", nullable = true)
    private String remark;

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
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Integer")
    private Integer state;

    /**
     * 菜单ID集合
     */
    @Schema(name = "menuIds", description = "菜单ID集合", type = "String", nullable = true)
    private String menuIds;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 角色 BO 转换 DTO
     */
    public static RoleDTO toDTO(RoleBO bo) {
        if (bo != null) {
            return RoleDTO.builder()
                    .id(bo.getId())
                    .roleName(bo.getRoleName())
                    .remark(bo.getRemark())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .menuIds(bo.getMenuIds())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 角色 BO 数组 转换 DTO List
     */
    public static List<RoleDTO> toDTO(RoleBO[] bos) {
        if (bos != null) {
            List<RoleDTO> dtos = new ArrayList<>();
            for (RoleBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 角色 BO List 转换 DTO List
     */
    public static List<RoleDTO> toDTO(List<RoleBO> bos) {
        if (bos != null) {
            List<RoleDTO> dtos = new ArrayList<>();
            for (RoleBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 角色 DTO 转换 BO
     */
    public static RoleBO toBO(RoleDTO dto) {
        if (dto != null) {
            return RoleBO.builder()
                    .id(dto.getId())
                    .roleName(dto.getRoleName())
                    .remark(dto.getRemark())
                    .createTime(dto.getCreateTime())
                    .creatorId(dto.getCreatorId())
                    .creatorName(dto.getCreatorName())
                    .updateTime(dto.getUpdateTime())
                    .updaterId(dto.getUpdaterId())
                    .updaterName(dto.getUpdaterName())
                    .state(dto.getState())
                    .menuIds(dto.getMenuIds())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 角色 DTO List 转换 BO List
     */
    public static List<RoleBO> toBO(List<RoleDTO> dtos) {
        if (dtos != null) {
            List<RoleBO> bos = new ArrayList<>();
            for (RoleDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 角色 DTO 转换 BO
     */
    public RoleBO toBO() {
        return RoleBO.builder()
                .id(this.getId())
                .roleName(this.getRoleName())
                .remark(this.getRemark())
                .createTime(this.getCreateTime())
                .creatorId(this.getCreatorId())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterId(this.getUpdaterId())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .menuIds(this.getMenuIds())
                .build();
    }

}
