package cn.fyy.redis.service;

import cn.fyy.redis.bean.ao.RedisSelect;
import io.lettuce.core.RedisException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Redis 响应式操作接口
 *
 * @author fyy
 */
public interface ReactiveRedisService {
    /**
     * 判断是否有该属性
     *
     * @param key 键
     */
    Mono<Boolean> hasKey(String key);

    /**
     * 判断是否有该属性
     *
     * @param key      键
     * @param database 库
     */
    Mono<Boolean> hasKey(RedisSelect database, String key);

    /**
     * 添加
     *
     * @param key   键
     * @param value 值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Mono<Boolean> set(String key, Object value);

    /**
     * 添加
     *
     * @param database 库
     * @param key      键
     * @param value    值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Mono<Boolean> set(RedisSelect database, String key, Object value);

    /**
     * 添加
     *
     * @param key     键
     * @param value   值
     * @param seconds 过期时间 秒
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Mono<Boolean> set(String key, Object value, Duration seconds);

    /**
     * 添加
     *
     * @param database 库
     * @param key      键
     * @param value    值
     * @param seconds  过期时间 秒
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Mono<Boolean> set(RedisSelect database, String key, Object value, Duration seconds);

    /**
     * 查询
     *
     * @param key 键
     * @param <T> 类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> Mono<T> get(String key, Class<T> tClass);

    /**
     * 查询
     *
     * @param database 库
     * @param key      键
     * @param <T>      类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> Mono<T> get(RedisSelect database, String key, Class<T> tClass);

    /**
     * 查询
     *
     * @param pattern 匹配
     * @param <T>     类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> Flux<T> getAll(String pattern, Class<T> tClass);

    /**
     * 查询
     *
     * @param database 库
     * @param pattern  匹配
     * @param <T>      类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> Flux<T> getAll(RedisSelect database, String pattern, Class<T> tClass);

    /**
     * 删除
     *
     * @param key 键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    Mono<Long> delete(String key);

    /**
     * 删除
     *
     * @param database 库
     * @param key      键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    Mono<Long> delete(RedisSelect database, String key);

    /**
     * 更新到期时间
     *
     * @param key     键
     * @param seconds 过期时间 秒
     * @return 超时时间
     */
    Mono<Boolean> expire(String key, long seconds);

    /**
     * 更新到期时间
     *
     * @param database 库
     * @param key      键
     * @param seconds  过期时间 秒
     * @return 超时时间
     */
    Mono<Boolean> expire(RedisSelect database, String key, long seconds);
}
