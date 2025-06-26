package cn.fyy.message.bean.dto;

import cn.fyy.message.bean.bo.NoticeBO;
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
 * 公告
 *
 * @author fuyy
 */
@Schema(name = "NoticeDTO", description = "公告 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "Long")
    private Long id;

    /**
     * 公告标题
     */
    @Schema(name = "noticeTitle", description = "公告标题", type = "String")
    private String noticeTitle;

    /**
     * 公告内容
     */
    @Schema(name = "noticeContent", description = "公告内容", type = "String")
    private String noticeContent;

    /**
     * 公告作者
     */
    @Schema(name = "noticeAuthor", description = "公告作者", type = "String")
    private String noticeAuthor;

    /**
     * 公告排序
     */
    @Schema(name = "noticeOrder", description = "公告排序", type = "Integer")
    private Integer noticeOrder;

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
     * 公告 BO 转换 DTO
     */
    public static NoticeDTO toDTO(NoticeBO bo) {
        if (bo != null) {
            return NoticeDTO.builder()
                    .id(bo.getId())
                    .noticeTitle(bo.getNoticeTitle())
                    .noticeContent(bo.getNoticeContent())
                    .noticeAuthor(bo.getNoticeAuthor())
                    .noticeOrder(bo.getNoticeOrder())
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
     * 公告 BO 数组 转换 DTO List
     */
    public static List<NoticeDTO> toDTO(NoticeBO[] bos) {
        if (bos != null) {
            List<NoticeDTO> dtos = new ArrayList<>();
            for (NoticeBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO List 转换 DTO List
     */
    public static List<NoticeDTO> toDTO(List<NoticeBO> bos) {
        if (bos != null) {
            List<NoticeDTO> dtos = new ArrayList<>();
            for (NoticeBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO 转换 BO
     */
    public static NoticeBO toBO(NoticeDTO dto) {
        if (dto != null) {
            return NoticeBO.builder()
                    .id(dto.getId())
                    .noticeTitle(dto.getNoticeTitle())
                    .noticeContent(dto.getNoticeContent())
                    .noticeAuthor(dto.getNoticeAuthor())
                    .noticeOrder(dto.getNoticeOrder())
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
     * 公告 DTO List 转换 BO List
     */
    public static List<NoticeBO> toBO(List<NoticeDTO> dtos) {
        if (dtos != null) {
            List<NoticeBO> bos = new ArrayList<>();
            for (NoticeDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO 转换 BO
     */
    public NoticeBO toBO() {
        return NoticeBO.builder()
                .id(this.getId())
                .noticeTitle(this.getNoticeTitle())
                .noticeContent(this.getNoticeContent())
                .noticeAuthor(this.getNoticeAuthor())
                .noticeOrder(this.getNoticeOrder())
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
