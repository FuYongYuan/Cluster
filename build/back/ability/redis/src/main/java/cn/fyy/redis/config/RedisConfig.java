package cn.fyy.redis.config;

import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.util.RedisTemplateSelectUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.*;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Redis 相关 Bean 配置
 *
 * @author fyy
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    /**
     * Redis 配置
     */
    @Resource
    private RedisProperties redisProperties;

    //------------------------------------------------------------------------------------------------------------------RedisTemplate

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate0() {
        return getRedisTemplate(RedisSelect.INFO);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate1() {
        return getRedisTemplate(RedisSelect.ONE);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate2() {
        return getRedisTemplate(RedisSelect.TWO);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate3() {
        return getRedisTemplate(RedisSelect.THREE);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate4() {
        return getRedisTemplate(RedisSelect.FOUR);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate5() {
        return getRedisTemplate(RedisSelect.FIVE);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate6() {
        return getRedisTemplate(RedisSelect.SIX);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate7() {
        return getRedisTemplate(RedisSelect.SEVEN);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate8() {
        return getRedisTemplate(RedisSelect.EIGHT);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate9() {
        return getRedisTemplate(RedisSelect.NINE);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate10() {
        return getRedisTemplate(RedisSelect.TEN);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate11() {
        return getRedisTemplate(RedisSelect.ELEVEN);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate12() {
        return getRedisTemplate(RedisSelect.TWELVE);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate13() {
        return getRedisTemplate(RedisSelect.THIRTEEN);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate14() {
        return getRedisTemplate(RedisSelect.FOURTEEN);
    }

    /**
     * RedisTemplate 配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate15() {
        return getRedisTemplate(RedisSelect.FIFTEEN);
    }

    /**
     * 获取 RedisTemplate
     *
     * @param redisSelect RedisSelect 选择
     * @return RedisTemplate
     */
    private RedisTemplate<String, Object> getRedisTemplate(RedisSelect redisSelect) {
        // 配置 Redis
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(redisProperties.getPassword());
        configuration.setDatabase(redisSelect.getValue());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        factory.start();

        // 序列化配置
        RedisSerializer<Object> serializer = redisSerializer();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    //------------------------------------------------------------------------------------------------------------------ReactiveRedisTemplate

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate0() {
        return getReactiveRedisTemplate(RedisSelect.INFO);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate1() {
        return getReactiveRedisTemplate(RedisSelect.ONE);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate2() {
        return getReactiveRedisTemplate(RedisSelect.TWO);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate3() {
        return getReactiveRedisTemplate(RedisSelect.THREE);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate4() {
        return getReactiveRedisTemplate(RedisSelect.FOUR);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate5() {
        return getReactiveRedisTemplate(RedisSelect.FIVE);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate6() {
        return getReactiveRedisTemplate(RedisSelect.SIX);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate7() {
        return getReactiveRedisTemplate(RedisSelect.SEVEN);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate8() {
        return getReactiveRedisTemplate(RedisSelect.EIGHT);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate9() {
        return getReactiveRedisTemplate(RedisSelect.NINE);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate10() {
        return getReactiveRedisTemplate(RedisSelect.TEN);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate11() {
        return getReactiveRedisTemplate(RedisSelect.ELEVEN);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate12() {
        return getReactiveRedisTemplate(RedisSelect.TWELVE);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate13() {
        return getReactiveRedisTemplate(RedisSelect.THIRTEEN);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate14() {
        return getReactiveRedisTemplate(RedisSelect.FOURTEEN);
    }

    /**
     * ReactiveRedisTemplate 配置
     *
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate15() {
        return getReactiveRedisTemplate(RedisSelect.FIFTEEN);
    }

    /**
     * 获取 ReactiveRedisTemplate
     *
     * @param redisSelect RedisSelect 选择
     * @return ReactiveRedisTemplate
     */
    private ReactiveRedisTemplate<String, Object> getReactiveRedisTemplate(RedisSelect redisSelect) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(redisProperties.getPassword());
        configuration.setDatabase(redisSelect.getValue());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        factory.start();

        RedisSerializer<Object> serializer = redisSerializer();
        RedisSerializationContext<String, Object> context = RedisSerializationContext
                .<String, Object>newSerializationContext()
                .key(new StringRedisSerializer())
                .value(serializer)
                .hashKey(new StringRedisSerializer())
                .hashValue(serializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
    //------------------------------------------------------------------------------------------------------------------公用方法

    /**
     * Redis 序列化方式
     *
     * @return RedisSerializer
     */
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        SimpleModule dateTimeModule = new SimpleModule("RedisDateTimeModule");

        dateTimeModule.addSerializer(LocalDateTime.class, new ValueSerializer<>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
                gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        });
        dateTimeModule.addDeserializer(LocalDateTime.class, new ValueDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
                return LocalDateTime.parse(p.getString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        });

        dateTimeModule.addSerializer(LocalDate.class, new ValueSerializer<>() {
            @Override
            public void serialize(LocalDate value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
                gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
        dateTimeModule.addDeserializer(LocalDate.class, new ValueDeserializer<>() {
            @Override
            public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
                return LocalDate.parse(p.getString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        });

        dateTimeModule.addSerializer(LocalTime.class, new ValueSerializer<>() {
            @Override
            public void serialize(LocalTime value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
                gen.writeString(value.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        });
        dateTimeModule.addDeserializer(LocalTime.class, new ValueDeserializer<>() {
            @Override
            public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
                return LocalTime.parse(p.getString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        });

        var typeValidator = BasicPolymorphicTypeValidator.builder()
                .allowIfSubTypeIsArray()
                .allowIfSubType("java.")
                .allowIfSubType("javax.")
                .allowIfSubType("org.")
                .allowIfSubType("com.")
                .allowIfSubType("cn.")
                .build();

        var mapper = JsonMapper.builder()
                .changeDefaultVisibility(checker -> checker
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.ANY)
                        .withSetterVisibility(JsonAutoDetect.Visibility.ANY)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.ANY))
                .activateDefaultTypingAsProperty(typeValidator,
                        DefaultTyping.NON_FINAL, "@class")
                .addModule(dateTimeModule)
                .build();


        return new JacksonJsonRedisSerializer<>(mapper, Object.class);
    }


    /**
     * 初始化 Redis 链接选择工具类
     */
    @Bean
    public RedisTemplateSelectUtil redisTemplateSelectUtil() {
        return new RedisTemplateSelectUtil(redisProperties.getDatabase());
    }
}