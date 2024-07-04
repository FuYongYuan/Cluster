package cn.fyy.capability.bean.dto;

import cn.fyy.capability.bean.bo.MenuBO;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 菜单
 *
 * @author fuyy
 */
@Schema(name = "MenuDTO", description = "菜单 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger")
    private BigInteger id;

    /**
     * 名称
     */
    @Schema(name = "menuName", description = "名称", type = "String")
    private String menuName;

    /**
     * 路径
     */
    @Schema(name = "menuUrl", description = "路径", type = "String", nullable = true)
    private String menuUrl;

    /**
     * 菜单图标
     */
    @Schema(name = "menuIcon", description = "菜单图标", type = "String", nullable = true)
    private String menuIcon;

    /**
     * 页面名称
     */
    @Schema(name = "pageName", description = "页面名称", type = "String", nullable = true)
    private String pageName;

    /**
     * 父级ID
     */
    @Schema(name = "parentId", description = "父级ID", type = "BigInteger", nullable = true)
    private BigInteger parentId;

    /**
     * 是否跳转  (0.否 1.是)
     */
    @Schema(name = "isTurn", description = "是否跳转  (0.否 1.是)", type = "Integer")
    private Integer isTurn;

    /**
     * 是否首页  (0.否 1.是)
     */
    @Schema(name = "isHome", description = "是否首页  (0.否 1.是)", type = "Integer")
    private Integer isHome;

    /**
     * 排序(按数字从小到大)
     */
    @Schema(name = "menuOrder", description = "排序(按数字从小到大)", type = "Integer")
    private Integer menuOrder;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注", type = "String", nullable = true)
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

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
    @Schema(name = "updateTime", description = "修改时间", type = "Date")
    private Date updateTime;

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

    /**
     * 名称
     */
    @Schema(name = "parentMenuName", description = "父级菜单名称", type = "String")
    private String parentMenuName;

    /**
     * 子级菜单
     */
    @Schema(name = "sub", description = "子级菜单", type = "List<MenuBO>")
    private List<MenuDTO> sub;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 菜单 BO 转换 DTO
     */
    public static MenuDTO toDTO(MenuBO bo) {
        if (bo != null) {
            return MenuDTO.builder()
                    .id(bo.getId())
                    .menuName(bo.getMenuName())
                    .menuUrl(bo.getMenuUrl())
                    .menuIcon(bo.getMenuIcon())
                    .pageName(bo.getPageName())
                    .parentId(bo.getParentId())
                    .isTurn(bo.getIsTurn())
                    .isHome(bo.getIsHome())
                    .menuOrder(bo.getMenuOrder())
                    .remark(bo.getRemark())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .sub(MenuDTO.toDTO(bo.getSub()))
                    .parentMenuName(bo.getParent() != null ? bo.getParent().getMenuName() : null)
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 菜单 BO 数组 转换 DTO List
     */
    public static List<MenuDTO> toDTO(MenuBO[] bos) {
        if (bos != null) {
            List<MenuDTO> dtos = new ArrayList<>();
            for (MenuBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 BO List 转换 DTO List
     */
    public static List<MenuDTO> toDTO(List<MenuBO> bos) {
        if (bos != null) {
            List<MenuDTO> dtos = new ArrayList<>();
            for (MenuBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 DTO 转换 BO
     */
    public static MenuBO toBO(MenuDTO dto) {
        if (dto != null) {
            return MenuBO.builder()
                    .id(dto.getId())
                    .menuName(dto.getMenuName())
                    .menuUrl(dto.getMenuUrl())
                    .menuIcon(dto.getMenuIcon())
                    .pageName(dto.getPageName())
                    .parentId(dto.getParentId())
                    .isTurn(dto.getIsTurn())
                    .isHome(dto.getIsHome())
                    .menuOrder(dto.getMenuOrder())
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
     * 菜单 DTO List 转换 BO List
     */
    public static List<MenuBO> toBO(List<MenuDTO> dtos) {
        if (dtos != null) {
            List<MenuBO> bos = new ArrayList<>();
            for (MenuDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 菜单 DTO 转换 BO
     */
    public MenuBO toBO() {
        return MenuBO.builder()
                .id(this.getId())
                .menuName(this.getMenuName())
                .menuUrl(this.getMenuUrl())
                .menuIcon(this.getMenuIcon())
                .pageName(this.getPageName())
                .parentId(this.getParentId())
                .isTurn(this.getIsTurn())
                .isHome(this.getIsHome())
                .menuOrder(this.getMenuOrder())
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
