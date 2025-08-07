package cn.fyy.message.bean.bo;

import cn.fyy.message.bean.po.NoticePO;
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
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告作者
     */
    private String noticeAuthor;

    /**
     * 公告排序
     */
    private Integer noticeOrder;

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
     * 公告 PO 转换 BO
     */
    public static NoticeBO toBO(NoticePO po) {
        if (po != null) {
            return NoticeBO.builder()
                    .id(po.getId())
                    .noticeTitle(po.getNoticeTitle())
                    .noticeContent(po.getNoticeContent())
                    .noticeAuthor(po.getNoticeAuthor())
                    .noticeOrder(po.getNoticeOrder())
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
     * 公告 PO 数组 转换 BO List
     */
    public static List<NoticeBO> toBO(NoticePO[] pos) {
        if (pos != null) {
            List<NoticeBO> bos = new ArrayList<>();
            for (NoticePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 PO List 转换 BO List
     */
    public static List<NoticeBO> toBO(List<NoticePO> pos) {
        if (pos != null) {
            List<NoticeBO> bos = new ArrayList<>();
            for (NoticePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 转换 PO
     */
    public static NoticePO toPO(NoticeBO bo) {
        if (bo != null) {
            return NoticePO.builder()
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
     * 公告 BO List 转换 PO List
     */
    public static List<NoticePO> toPO(List<NoticeBO> bos) {
        if (bos != null) {
            List<NoticePO> pos = new ArrayList<>();
            for (NoticeBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 转换 PO
     */
    public NoticePO toPO() {
        return NoticePO.builder()
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
