package cn.fyy.redis.service.impl;

import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import cn.fyy.redis.util.RedisTemplateSelectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作实现类
 *
 * @author fyy
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    /**
     * RedisTemplate 选择工具类
     */
    @Resource
    private RedisTemplateSelectUtil redisTemplateSelectUtil;

    /**
     * json 操作类
     */
    private final ObjectMapper mapper = new ObjectMapper();

    //------------------------------------------------------------------------------------------------------------------String操作

    /**
     * 判断是否有该属性
     *
     * @param key 键
     */
    @Override
    public Boolean hasKey(String key) {
        try {
            return redisTemplateSelectUtil.getDefaultRedisTemplate().hasKey(key);
        } catch (Exception e) {
            log.error("Redis 验证存在失败", e);
            return false;
        }
    }

    /**
     * 判断是否有该属性
     *
     * @param key      键
     * @param database 库
     */
    @Override
    public Boolean hasKey(RedisSelect database, String key) {
        try {
            return redisTemplateSelectUtil.getRedisTemplate(database).hasKey(key);
        } catch (Exception e) {
            log.error("Redis 验证存在失败", e);
            return false;
        }
    }

    /**
     * 添加
     *
     * @param key   键
     * @param value 值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    @Override
    public Boolean set(String key, Object value) {
        try {
            redisTemplateSelectUtil.getDefaultRedisTemplate().opsForValue().set(key, mapper.writeValueAsString(value));
        } catch (Exception e) {
            log.error("Redis 添加失败", e);
            return false;
        }
        return this.hasKey(key);
    }

    /**
     * 添加
     *
     * @param database 库
     * @param key      键
     * @param value    值
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    @Override
    public Boolean set(RedisSelect database, String key, Object value) {
        try {
            redisTemplateSelectUtil.getRedisTemplate(database).opsForValue().set(key, mapper.writeValueAsString(value));
        } catch (Exception e) {
            log.error("Redis 添加失败", e);
            return false;
        }
        return this.hasKey(database, key);
    }

    /**
     * 添加
     *
     * @param key     键
     * @param value   值
     * @param seconds 过期时间 秒
     * @return 是否存在-存在添加成功，不存在添加失败
     * @throws RedisException 业务错误
     */
    @Override
    public Boolean set(String key, Object value, long seconds) {
        try {
            redisTemplateSelectUtil.getDefaultRedisTemplate().opsForValue().set(key, mapper.writeValueAsString(value), seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Redis 添加失败", e);
            return false;
        }
        return this.hasKey(key);
    }

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
    @Override
    public Boolean set(RedisSelect database, String key, Object value, long seconds) {
        try {
            redisTemplateSelectUtil.getRedisTemplate(database).opsForValue().set(key, mapper.writeValueAsString(value), seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Redis 添加失败", e);
            return false;
        }
        return this.hasKey(database, key);
    }

    /**
     * 查询
     *
     * @param key 键
     * @param <T> 类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    @Override
    public <T> T get(String key, Class<T> tClass) {
        try {
            Object obj = redisTemplateSelectUtil.getDefaultRedisTemplate().opsForValue().get(key);
            if (obj != null) {
                return mapper.readValue(obj.toString(), tClass);
            }
        } catch (Exception e) {
            log.error("Redis 查询失败", e);
            return null;
        }
        return null;
    }

    /**
     * 查询
     *
     * @param database 库
     * @param key      键
     * @param <T>      类型
     * @return 对象
     * @throws RedisException 业务错误
     */
    @Override
    public <T> T get(RedisSelect database, String key, Class<T> tClass) {
        try {
            Object obj = redisTemplateSelectUtil.getRedisTemplate(database).opsForValue().get(key);
            if (obj != null) {
                return mapper.readValue(obj.toString(), tClass);
            }
        } catch (Exception e) {
            log.error("Redis 查询失败", e);
            return null;
        }
        return null;
    }

    /**
     * 删除
     *
     * @param key 键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    @Override
    public Boolean delete(String key) {
        try {
            return redisTemplateSelectUtil.getDefaultRedisTemplate().delete(key);
        } catch (Exception e) {
            log.error("Redis 删除失败", e);
            return false;
        }
    }

    /**
     * 删除
     *
     * @param database 库
     * @param key      键
     * @return 是 否
     * @throws RedisException 业务错误
     */
    @Override
    public Boolean delete(RedisSelect database, String key) {
        try {
            return redisTemplateSelectUtil.getRedisTemplate(database).delete(key);
        } catch (Exception e) {
            log.error("Redis 删除失败", e);
            return false;
        }
    }

    /**
     * 更新到期时间
     *
     * @param key     键
     * @param seconds 过期时间 秒
     * @return 超时时间
     */
    @Override
    public Boolean expire(String key, long seconds) {
        try {
            return redisTemplateSelectUtil.getDefaultRedisTemplate().expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Redis 更新到期时间失败", e);
            return false;
        }
    }

    /**
     * 更新到期时间
     *
     * @param database 库
     * @param key      键
     * @param seconds  过期时间 秒
     * @return 超时时间
     */
    @Override
    public Boolean expire(RedisSelect database, String key, long seconds) {
        try {
            return redisTemplateSelectUtil.getRedisTemplate(database).expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Redis 更新到期时间失败", e);
            return false;
        }
    }

    /**
     * 使用 Lua 脚本查询所有 Key 中包含指定前缀的对象及其对应的 Value
     *
     * @param pattern 匹配模式，例如 "ABC-*"
     * @return 匹配的 Key 和对应 Value 的映射
     */
    @Override
    public Map<String, Object> scanKeyWithValueByLua(String pattern) {
        // 创建 RedisScript 对象
        RedisScript<Map<String, Object>> script = new DefaultRedisScript<>(SCAN_KEY_WITH_VALUE);

        try {
            // 执行 Lua 脚本
            return redisTemplateSelectUtil.getDefaultRedisTemplate().execute(
                    script,
                    // KEYS 参数为空
                    Collections.emptyList(),
                    // ARGV 参数为匹配模式
                    pattern
            );
        } catch (Exception e) {
            log.error("Redis Lua 脚本执行失败", e);
            return Collections.emptyMap();
        }
    }

    /**
     * 使用 Lua 脚本查询指定数据库中所有 Key 中包含指定前缀的对象及其对应的 Value
     *
     * @param database 指定数据库
     * @param pattern  匹配模式，例如 "ABC-*"
     * @return 匹配的 Key 和对应 Value 的映射
     */
    @Override
    public Map<String, Object> scanKeyWithValueByLua(RedisSelect database, String pattern) {
        // 创建 RedisScript 对象
        RedisScript<Map<String, Object>> script = new DefaultRedisScript<>(SCAN_KEY_WITH_VALUE);

        try {
            // 执行 Lua 脚本（指定数据库）
            return redisTemplateSelectUtil.getRedisTemplate(database).execute(
                    script,
                    // KEYS 参数为空
                    Collections.emptyList(),
                    // ARGV 参数为匹配模式
                    pattern
            );
        } catch (Exception e) {
            log.error("Redis Lua 脚本执行失败", e);
            return Collections.emptyMap();
        }
    }
}