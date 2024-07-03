package cn.fyy.redis.config;

import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.util.RedisTemplateSelectUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Redis相关Bean配置
 *
 * @author fyy
 */
@Configuration
public class RedisConfig {
    /**
     * Redis配置
     */
    @Resource
    private RedisProperties redisProperties;

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate0() {
        return getRedisTemplate(RedisSelect.INFO);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate1() {
        return getRedisTemplate(RedisSelect.ONE);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate2() {
        return getRedisTemplate(RedisSelect.TWO);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate3() {
        return getRedisTemplate(RedisSelect.THREE);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate4() {
        return getRedisTemplate(RedisSelect.FOUR);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate5() {
        return getRedisTemplate(RedisSelect.FIVE);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate6() {
        return getRedisTemplate(RedisSelect.SIX);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate7() {
        return getRedisTemplate(RedisSelect.SEVEN);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate8() {
        return getRedisTemplate(RedisSelect.EIGHT);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate9() {
        return getRedisTemplate(RedisSelect.NINE);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate10() {
        return getRedisTemplate(RedisSelect.TEN);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate11() {
        return getRedisTemplate(RedisSelect.ELEVEN);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate12() {
        return getRedisTemplate(RedisSelect.TWELVE);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate13() {
        return getRedisTemplate(RedisSelect.THIRTEEN);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate14() {
        return getRedisTemplate(RedisSelect.FOURTEEN);
    }

    /**
     * RedisTemplate配置
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate15() {
        return getRedisTemplate(RedisSelect.FIFTEEN);
    }

    /**
     * 获取RedisTemplate
     *
     * @param redisSelect RedisSelect选择
     * @return RedisTemplate
     */
    private RedisTemplate<String, Object> getRedisTemplate(RedisSelect redisSelect) {
        // 配置Redis
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

    /**
     * Redis序列化方式
     *
     * @return RedisSerializer
     */
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //必须设置，否则无法将JSON转化为对象，会转化成Map类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        // 自定义ObjectMapper的时间处理模块
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        objectMapper.registerModule(javaTimeModule);

        // 禁用将日期序列化为时间戳的行为
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //创建JSON序列化器
        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    }

    /**
     * 初始化Redis链接选择工具类
     */
    @Bean
    public RedisTemplateSelectUtil redisTemplateSelectUtil() {
        return new RedisTemplateSelectUtil(redisProperties.getDatabase());
    }
}