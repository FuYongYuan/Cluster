package cn.fyy.message.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticePO extends BasePO implements Serializable {

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

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticePO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(noticeTitle, that.noticeTitle) &&
                Objects.equals(noticeContent, that.noticeContent) &&
                Objects.equals(noticeAuthor, that.noticeAuthor) &&
                Objects.equals(noticeOrder, that.noticeOrder);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(noticeTitle);
        result = 31 * result + Objects.hashCode(noticeContent);
        result = 31 * result + Objects.hashCode(noticeAuthor);
        result = 31 * result + Objects.hashCode(noticeOrder);
        return result;
    }
}
