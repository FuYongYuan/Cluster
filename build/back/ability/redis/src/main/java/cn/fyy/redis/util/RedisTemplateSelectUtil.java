package cn.fyy.redis.util;

import cn.fyy.redis.bean.ao.RedisSelect;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.EnumMap;

/**
 * Redis 链接选择工具类
 *
 * @author fyy
 */
public class RedisTemplateSelectUtil {

    /**
     * redisTemplateMap
     */
    private final EnumMap<RedisSelect, RedisTemplate<String, Object>> redisTemplateMap;
    /**
     * reactiveRedisTemplateMap
     */
    private final EnumMap<RedisSelect, ReactiveRedisTemplate<String, Object>> reactiveRedisTemplateMap;

    /**
     * defaultRedisTemplate
     */
    @Getter
    private RedisTemplate<String, Object> defaultRedisTemplate;

    /**
     * defaultReactiveRedisTemplate
     */
    @Getter
    private ReactiveRedisTemplate<String, Object> defaultReactiveRedisTemplate;

    /**
     * defaultRedisSelect
     */
    private final int defaultRedisSelect;

    /**
     * 构造方法
     *
     * @param redisTemplateMap         redisTemplateMap
     * @param reactiveRedisTemplateMap reactiveRedisTemplateMap
     * @param defaultRedisSelect       defaultRedisSelect
     */
    public RedisTemplateSelectUtil(
            EnumMap<RedisSelect, RedisTemplate<String, Object>> redisTemplateMap,
            EnumMap<RedisSelect, ReactiveRedisTemplate<String, Object>> reactiveRedisTemplateMap,
            int defaultRedisSelect
    ) {
        this.redisTemplateMap = redisTemplateMap;
        this.reactiveRedisTemplateMap = reactiveRedisTemplateMap;
        this.defaultRedisSelect = defaultRedisSelect;
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        RedisSelect redisSelect = RedisSelect.getByRedisSelectValue(defaultRedisSelect);
        if (redisSelect != null) {
            defaultRedisTemplate = redisTemplateMap.get(redisSelect);
            defaultReactiveRedisTemplate = reactiveRedisTemplateMap.get(redisSelect);
        } else {
            defaultRedisTemplate = redisTemplateMap.get(RedisSelect.INFO);
            defaultReactiveRedisTemplate = reactiveRedisTemplateMap.get(RedisSelect.INFO);
        }
    }

    /**
     * 根据redisSelect获取redisTemplate
     *
     * @param redisSelect redisSelect
     * @return redisTemplate
     */
    public RedisTemplate<String, Object> getRedisTemplate(RedisSelect redisSelect) {
        return redisTemplateMap.get(redisSelect);
    }

    /**
     * 根据redisSelect获取reactiveRedisTemplate
     *
     * @param redisSelect redisSelect
     * @return reactiveRedisTemplate
     */
    public ReactiveRedisTemplate<String, Object> getReactiveRedisTemplate(RedisSelect redisSelect) {
        return reactiveRedisTemplateMap.get(redisSelect);
    }

}
