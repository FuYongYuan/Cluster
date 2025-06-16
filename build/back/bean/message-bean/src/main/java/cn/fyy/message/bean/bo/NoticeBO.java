package cn.fyy.message.bean.bo;

import cn.fyy.message.bean.dbo.NoticeDO;
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
    private BigInteger id;

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
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 公告 DO 转换 BO
     */
    public static NoticeBO toBO(NoticeDO dbo) {
        if (dbo != null) {
            return NoticeBO.builder()
                    .id(dbo.getId())
                    .noticeTitle(dbo.getNoticeTitle())
                    .noticeContent(dbo.getNoticeContent())
                    .noticeAuthor(dbo.getNoticeAuthor())
                    .noticeOrder(dbo.getNoticeOrder())
                    .createTime(dbo.getCreateTime())
                    .creatorId(dbo.getCreatorId())
                    .creatorName(dbo.getCreatorName())
                    .updateTime(dbo.getUpdateTime())
                    .updaterId(dbo.getUpdaterId())
                    .updaterName(dbo.getUpdaterName())
                    .state(dbo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 公告 DO 数组 转换 BO List
     */
    public static List<NoticeBO> toBO(NoticeDO[] dbos) {
        if (dbos != null) {
            List<NoticeBO> bos = new ArrayList<>();
            for (NoticeDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 DO List 转换 BO List
     */
    public static List<NoticeBO> toBO(List<NoticeDO> dbos) {
        if (dbos != null) {
            List<NoticeBO> bos = new ArrayList<>();
            for (NoticeDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 转换 DO
     */
    public static NoticeDO toDO(NoticeBO bo) {
        if (bo != null) {
            return NoticeDO.builder()
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
     * 公告 BO List 转换 DO List
     */
    public static List<NoticeDO> toDO(List<NoticeBO> bos) {
        if (bos != null) {
            List<NoticeDO> dbos = new ArrayList<>();
            for (NoticeBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 转换 DO
     */
    public NoticeDO toDO() {
        return NoticeDO.builder()
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
