package cn.fyy.redis.service.impl;

import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.ReactiveRedisService;
import cn.fyy.redis.util.RedisTemplateSelectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Redis 响应式操作实现类
 *
 * @author fyy
 */
@Slf4j
@Service
public class ReactiveRedisServiceImpl implements ReactiveRedisService {
    /**
     * Redis 模板工具类
     */
    @Resource
    private RedisTemplateSelectUtil redisTemplateSelectUtil;

    //------------------------------------------------------------------------------------------------------------------String操作

    /**
     * 验证 Redis 中是否存在指定 key
     *
     * @param key key
     * @return 是否存在
     */
    @Override
    public Mono<Boolean> hasKey(String key) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .hasKey(key)
                .onErrorResume(e -> {
                    log.error("Redis 响应式验证存在失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 验证 Redis 中是否存在指定 key
     *
     * @param database 数据库
     * @param key      key
     * @return 是否存在
     */
    @Override
    public Mono<Boolean> hasKey(RedisSelect database, String key) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .hasKey(key)
                .onErrorResume(e -> {
                    log.error("Redis 响应式验证存在失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 添加 Redis 中的 key-value
     *
     * @param key   key
     * @param value value
     * @return 是否添加成功
     */
    @Override
    public Mono<Boolean> set(String key, Object value) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .opsForValue()
                .set(key, value)
                .onErrorResume(e -> {
                    log.error("Redis 响应式添加失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 添加 Redis 中的 key-value
     *
     * @param database 数据库
     * @param key      key
     * @param value    value
     * @return 是否添加成功
     */
    @Override
    public Mono<Boolean> set(RedisSelect database, String key, Object value) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .opsForValue()
                .set(key, value)
                .onErrorResume(e -> {
                    log.error("Redis 响应式添加失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 添加 Redis 中的 key-value，并设置过期时间
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @return 是否添加成功
     */
    @Override
    public Mono<Boolean> set(String key, Object value, Duration timeout) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .opsForValue()
                .set(key, value, timeout)
                .onErrorResume(e -> {
                    log.error("Redis 响应式添加失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 添加 Redis 中的 key-value，并设置过期时间
     *
     * @param database 数据库
     * @param key      key
     * @param value    value
     * @param timeout  过期时间
     * @return 是否添加成功
     */
    @Override
    public Mono<Boolean> set(RedisSelect database, String key, Object value, Duration timeout) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .opsForValue()
                .set(key, value, timeout)
                .onErrorResume(e -> {
                    log.error("Redis 响应式添加失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 查询 Redis 中的 key-value
     *
     * @param key    key
     * @param tClass 类型
     * @return key-value
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Mono<T> get(String key, Class<T> tClass) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .opsForValue()
                .get(key)
                .map(obj -> (T) obj)
                .onErrorResume(e -> {
                    log.error("Redis 响应式查询失败", e);
                    return Mono.empty();
                });
    }

    /**
     * 查询 Redis 中的 key-value
     *
     * @param database 数据库
     * @param key      key
     * @param tClass   类型
     * @return key-value
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Mono<T> get(RedisSelect database, String key, Class<T> tClass) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .opsForValue()
                .get(key)
                .map(obj -> (T) obj)
                .onErrorResume(e -> {
                    log.error("Redis 响应式查询失败", e);
                    return Mono.empty();
                });
    }

    /**
     * 查询 Redis 中的 key-value
     *
     * @param pattern 匹配
     * @param tClass  类型
     * @return key-value
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Flux<T> getAll(String pattern, Class<T> tClass) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .keys(pattern + "*")
                .flatMap(key -> redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                        .opsForValue()
                        .get(key)
                        .map(obj -> (T) obj))
                .onErrorResume(e -> {
                    log.error("Redis 响应式批量查询失败", e);
                    return Flux.empty();
                });
    }

    /**
     * 查询 Redis 中的 key-value
     *
     * @param database 数据库
     * @param pattern  匹配
     * @param tClass   类型
     * @return key-value
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Flux<T> getAll(RedisSelect database, String pattern, Class<T> tClass) {
        ReactiveRedisTemplate<String, Object> template = redisTemplateSelectUtil.getReactiveRedisTemplate(database);
        return template.keys(pattern + "*")
                .flatMap(key -> template.opsForValue()
                        .get(key)
                        .map(obj -> (T) obj))
                .onErrorResume(e -> {
                    log.error("Redis 响应式批量查询失败", e);
                    return Flux.empty();
                });
    }

    /**
     * 删除 Redis 中的 key
     *
     * @param key key
     * @return 删除数量
     */
    @Override
    public Mono<Long> delete(String key) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .delete(key)
                .onErrorResume(e -> {
                    log.error("Redis 响应式删除失败", e);
                    return Mono.just(0L);
                });
    }

    /**
     * 删除 Redis 中的 key
     *
     * @param database 数据库
     * @param key      key
     * @return 删除数量
     */
    @Override
    public Mono<Long> delete(RedisSelect database, String key) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .delete(key)
                .onErrorResume(e -> {
                    log.error("Redis 响应式删除失败", e);
                    return Mono.just(0L);
                });
    }

    /**
     * 更新 Redis 中的 key 的到期时间
     *
     * @param key     key
     * @param seconds 过期时间
     * @return 是否更新成功
     */
    @Override
    public Mono<Boolean> expire(String key, long seconds) {
        return redisTemplateSelectUtil.getDefaultReactiveRedisTemplate()
                .expire(key, Duration.ofSeconds(seconds))
                .onErrorResume(e -> {
                    log.error("Redis 响应式更新到期时间失败", e);
                    return Mono.just(false);
                });
    }

    /**
     * 更新 Redis 中的 key 的到期时间
     *
     * @param database 数据库
     * @param key      key
     * @param seconds  过期时间
     * @return 是否更新成功
     */
    @Override
    public Mono<Boolean> expire(RedisSelect database, String key, long seconds) {
        return redisTemplateSelectUtil.getReactiveRedisTemplate(database)
                .expire(key, Duration.ofSeconds(seconds))
                .onErrorResume(e -> {
                    log.error("Redis 响应式更新到期时间失败", e);
                    return Mono.just(false);
                });
    }
}
