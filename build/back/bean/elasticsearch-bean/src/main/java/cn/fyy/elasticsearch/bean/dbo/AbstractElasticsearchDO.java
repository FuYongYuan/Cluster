package cn.fyy.elasticsearch.bean.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * 抽象DO
 *
 * @author fyy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//  索引名称
@Document(indexName = "base-index")
public abstract class AbstractElasticsearchDO {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private LocalDateTime createdDate;

    public AbstractElasticsearchDO(String id) {
        this.id = id;
        this.createdDate = LocalDateTime.now();
    }
}