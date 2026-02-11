package cn.fyy.redis.service;

import cn.fyy.redis.bean.ao.RedisSelect;
import io.lettuce.core.RedisException;

import java.util.Map;

/**
 * Redis 操作实现类
 *
 * @author fyy
 */
public interface RedisService {
    /**
     * 判断是否有该属性
     *
     * @param key 键
     */
    Boolean hasKey(String key);

    /**
     * 判断是否有该属性
     *
     * @param key      键
     * @param database 库
     */
    Boolean hasKey(RedisSelect database, String key);

    /**
     * 添加
     *
     * @param key   键
     * @param value 值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Boolean set(String key, Object value);

    /**
     * 添加
     *
     * @param database 库
     * @param key      键
     * @param value    值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Boolean set(RedisSelect database, String key, Object value);

    /**
     * 添加
     *
     * @param key     键
     * @param value   值
     * @param seconds 过期时间 秒
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    Boolean set(String key, Object value, long seconds);

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
    Boolean set(RedisSelect database, String key, Object value, long seconds);

    /**
     * 查询
     *
     * @param key 键
     * @param <T> 类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> T get(String key, Class<T> tClass);

    /**
     * 查询
     *
     * @param database 库
     * @param key      键
     * @param <T>      类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    <T> T get(RedisSelect database, String key, Class<T> tClass);

    /**
     * 删除
     *
     * @param key 键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    Boolean delete(String key);

    /**
     * 删除
     *
     * @param database 库
     * @param key      键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    Boolean delete(RedisSelect database, String key);

    /**
     * 更新到期时间
     *
     * @param key     键
     * @param seconds 过期时间 秒
     * @return 超时时间
     */
    Boolean expire(String key, long seconds);

    /**
     * 更新到期时间
     *
     * @param database 库
     * @param key      键
     * @param seconds  过期时间 秒
     * @return 超时时间
     */
    Boolean expire(RedisSelect database, String key, long seconds);

    /**
     * 使用 Lua 脚本查询所有 Key 中包含指定前缀的对象及其对应的 Value
     *
     * @param pattern 匹配模式，例如 "ABC-*"
     * @return 匹配的 Key 和对应 Value 的映射
     */
    Map<String, Object> scanKeyWithValueByLua(String pattern);

    /**
     * 使用 Lua 脚本查询指定数据库中所有 Key 中包含指定前缀的对象及其对应的 Value
     *
     * @param database 指定数据库
     * @param pattern  匹配模式，例如 "ABC-*"
     * @return 匹配的 Key 和对应 Value 的映射
     */
    Map<String, Object> scanKeyWithValueByLua(RedisSelect database, String pattern);

    //------------------------------------------------------------------------------------------------------------------Lua脚本
    // 获取所有 Key 中包含指定前缀的对象及其对应的 Value
    String SCAN_KEY_WITH_VALUE =
            """
                    local pattern = ARGV[1]
                    local keys = redis.call('keys', pattern)
                    local result = {}
                    for i, key in ipairs(keys) do
                        result[key] = redis.call('get', key)
                    end
                    return result
                    """;
}
