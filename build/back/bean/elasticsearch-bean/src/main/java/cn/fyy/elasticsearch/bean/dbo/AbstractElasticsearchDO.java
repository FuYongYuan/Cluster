package cn.fyy.elasticsearch.bean.dbo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽象DO
 *
 * @author fyy
 */
@Getter
@Setter
@ToString
// 索引名称
@Document(indexName = "share")
public abstract class AbstractElasticsearchDO implements Serializable {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @Field(type = FieldType.Date)
    private LocalDateTime updateTime;
}