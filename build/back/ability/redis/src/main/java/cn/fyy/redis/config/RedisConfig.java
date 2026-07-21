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
import java.util.EnumMap;

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


    /**
     * RedisTemplate 选择工具
     *
     * @return RedisTemplateSelectUtil
     */
    @Bean
    public RedisTemplateSelectUtil redisTemplateSelectUtil() {
        RedisSerializer<Object> serializer = redisSerializer();

        EnumMap<RedisSelect, RedisTemplate<String, Object>> redisTemplateMap = new EnumMap<>(RedisSelect.class);
        EnumMap<RedisSelect, ReactiveRedisTemplate<String, Object>> reactiveRedisTemplateMap = new EnumMap<>(RedisSelect.class);

        for (RedisSelect select : RedisSelect.values()) {
            LettuceConnectionFactory factory = createConnectionFactory(select.getValue());

            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(serializer);
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(serializer);
            redisTemplate.afterPropertiesSet();
            redisTemplateMap.put(select, redisTemplate);

            RedisSerializationContext<String, Object> context = RedisSerializationContext
                    .<String, Object>newSerializationContext()
                    .key(new StringRedisSerializer())
                    .value(serializer)
                    .hashKey(new StringRedisSerializer())
                    .hashValue(serializer)
                    .build();
            reactiveRedisTemplateMap.put(select, new ReactiveRedisTemplate<>(factory, context));
        }

        return new RedisTemplateSelectUtil(redisTemplateMap, reactiveRedisTemplateMap, redisProperties.getDatabase());
    }

    /**
     * 根据数据库创建连接工厂
     *
     * @param database 数据库
     * @return LettuceConnectionFactory
     */
    private LettuceConnectionFactory createConnectionFactory(int database) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(redisProperties.getPassword());
        configuration.setDatabase(database);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        factory.start();
        return factory;
    }

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

}