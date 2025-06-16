package cn.fyy.common.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Jackson 配置
 *
 * @author fyy
 */
@Configuration
public class JacksonConfig {

    /**
     * 创建 ObjectMapper 实例
     *
     * @return ObjectMapper 实例
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // 注册 Java 8 时间模块（支持 LocalDateTime）
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
