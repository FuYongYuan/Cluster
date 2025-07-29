package cn.fyy.capability.bean.dto;

import cn.fyy.capability.bean.bo.ButtonBO;
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
 * 按钮
 *
 * @author fuyy
 */
@Schema(name = "RoleButtonDTO", description = "按钮 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ButtonDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long")
    private Long id;

    /**
     * 名称
     */
    @Schema(name = "buttonName", description = "名称", type = "String")
    private String buttonName;

    /**
     * 标识
     */
    @Schema(name = "buttonSign", description = "标识", type = "String", nullable = true)
    private String buttonSign;

    /**
     * 点击事件
     */
    @Schema(name = "onClick", description = "点击事件", type = "String", nullable = true)
    private String onClick;

    /**
     * 菜单ID
     */
    @Schema(name = "menuId", description = "菜单ID", type = "Long")
    private Long menuId;

    /**
     * 排序(按数字从小到大)
     */
    @Schema(name = "buttonOrder", description = "排序(按数字从小到大)", type = "Integer")
    private Integer buttonOrder;

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
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Byte")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 按钮 BO 转换 DTO
     */
    public static ButtonDTO toDTO(ButtonBO bo) {
        if (bo != null) {
            return ButtonDTO.builder()
                    .id(bo.getId())
                    .buttonName(bo.getButtonName())
                    .buttonSign(bo.getButtonSign())
                    .onClick(bo.getOnClick())
                    .menuId(bo.getMenuId())
                    .buttonOrder(bo.getButtonOrder())
                    .remark(bo.getRemark())
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
     * 按钮 BO 数组 转换 DTO List
     */
    public static List<ButtonDTO> toDTO(ButtonBO[] bos) {
        if (bos != null) {
            List<ButtonDTO> dtos = new ArrayList<>();
            for (ButtonBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 BO List 转换 DTO List
     */
    public static List<ButtonDTO> toDTO(List<ButtonBO> bos) {
        if (bos != null) {
            List<ButtonDTO> dtos = new ArrayList<>();
            for (ButtonBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 DTO 转换 BO
     */
    public static ButtonBO toBO(ButtonDTO dto) {
        if (dto != null) {
            return ButtonBO.builder()
                    .id(dto.getId())
                    .buttonName(dto.getButtonName())
                    .buttonSign(dto.getButtonSign())
                    .onClick(dto.getOnClick())
                    .menuId(dto.getMenuId())
                    .buttonOrder(dto.getButtonOrder())
                    .remark(dto.getRemark())
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
     * 按钮 DTO List 转换 BO List
     */
    public static List<ButtonBO> toBO(List<ButtonDTO> dtos) {
        if (dtos != null) {
            List<ButtonBO> bos = new ArrayList<>();
            for (ButtonDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 按钮 DTO 转换 BO
     */
    public ButtonBO toBO() {
        return ButtonBO.builder()
                .id(this.getId())
                .buttonName(this.getButtonName())
                .buttonSign(this.getButtonSign())
                .onClick(this.getOnClick())
                .menuId(this.getMenuId())
                .buttonOrder(this.getButtonOrder())
                .remark(this.getRemark())
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
