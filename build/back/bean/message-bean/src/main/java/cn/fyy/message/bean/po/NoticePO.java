package cn.fyy.message.bean.po;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
public class NoticePO implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Long creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 更新人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    @Column(name = "state")
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticePO that)) {
            return false;
        }

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(noticeTitle, that.noticeTitle)) {
            return false;
        }
        if (!Objects.equals(noticeContent, that.noticeContent)) {
            return false;
        }
        if (!Objects.equals(noticeAuthor, that.noticeAuthor)) {
            return false;
        }
        if (!Objects.equals(noticeOrder, that.noticeOrder)) {
            return false;
        }
        if (!Objects.equals(createTime, that.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, that.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, that.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, that.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, that.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, that.updaterName)) {
            return false;
        }
        return Objects.equals(state, that.state);
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
