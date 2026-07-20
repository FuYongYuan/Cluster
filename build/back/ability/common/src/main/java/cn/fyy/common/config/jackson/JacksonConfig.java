package cn.fyy.common.config.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Jackson 配置
 *
 * @author fyy
 */
@Configuration
public class JacksonConfig {

    @Bean
    public JsonMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.changeDefaultPropertyInclusion(value -> value.withValueInclusion(JsonInclude.Include.NON_NULL));
    }
}
