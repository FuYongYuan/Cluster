package cn.fyy.mongodb.bean.dbo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * MongoDB 实体基类（抽象类）
 * 包含通用字段：_id、创建时间、更新时间、版本号
 *
 * @author fyy
 */
@Document
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractMongoDO implements Serializable {
    /**
     * 主键ID，自动映射到 MongoDB 的 _id 字段
     */
    @Id
    private String id;

    /**
     * 创建时间，自动填充
     */
    @CreatedDate
    private Date createTime;

    /**
     * 最后修改时间，自动填充
     */
    @LastModifiedDate
    private Date updateTime;

    /**
     * 版本号，自动填充
     */
    @Version
    private Long version;

}
