package cn.fyy.message.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 公告
 *
 * @author fuyy
 */
@Entity
@Table(name = "notice", schema = "message")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDO implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    /**
     * 公告标题
     */
    @Column(name = "notice_title", length = 200)
    private String noticeTitle;

    /**
     * 公告内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "notice_content", columnDefinition = "text")
    @ToString.Exclude
    private String noticeContent;

    /**
     * 公告作者
     */
    @Column(name = "notice_author", length = 200)
    private String noticeAuthor;

    /**
     * 公告排序
     */
    @Column(name = "notice_order")
    private Integer noticeOrder;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "updater_id")
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Column(name = "state")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticeDO noticeDO)) {
            return false;
        }

        if (!id.equals(noticeDO.id)) {
            return false;
        }
        if (!Objects.equals(noticeTitle, noticeDO.noticeTitle)) {
            return false;
        }
        if (!Objects.equals(noticeContent, noticeDO.noticeContent)) {
            return false;
        }
        if (!Objects.equals(noticeAuthor, noticeDO.noticeAuthor)) {
            return false;
        }
        if (!Objects.equals(noticeOrder, noticeDO.noticeOrder)) {
            return false;
        }
        if (!Objects.equals(createTime, noticeDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, noticeDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, noticeDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, noticeDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, noticeDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, noticeDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, noticeDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (noticeTitle != null ? noticeTitle.hashCode() : 0);
        result = 31 * result + (noticeContent != null ? noticeContent.hashCode() : 0);
        result = 31 * result + (noticeAuthor != null ? noticeAuthor.hashCode() : 0);
        result = 31 * result + (noticeOrder != null ? noticeOrder.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updaterName != null ? updaterName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
