package cn.fyy.redis.util;

import cn.fyy.redis.bean.ao.RedisSelect;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis链接选择工具类
 *
 * @author fyy
 */
public class RedisTemplateSelectUtil {
    /**
     * redis操作模板 库0
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate0;
    /**
     * redis操作模板 库1
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate1;
    /**
     * redis操作模板 库2
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate2;
    /**
     * redis操作模板 库3
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate3;
    /**
     * redis操作模板 库4
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate4;
    /**
     * redis操作模板 库5
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate5;
    /**
     * redis操作模板 库6
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate6;
    /**
     * redis操作模板 库7
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate7;
    /**
     * redis操作模板 库8
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate8;
    /**
     * redis操作模板 库9
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate9;
    /**
     * redis操作模板 库10
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate10;
    /**
     * redis操作模板 库11
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate11;
    /**
     * redis操作模板 库12
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate12;
    /**
     * redis操作模板 库13
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate13;
    /**
     * redis操作模板 库14
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate14;
    /**
     * redis操作模板 库15
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate15;

    /**
     * 选择不同的redis操作模板
     */
    public RedisTemplate<String, Object> getRedisTemplate(RedisSelect redisSelect) {
        return switch (redisSelect) {
            case INFO -> redisTemplate0;
            case ONE -> redisTemplate1;
            case TWO -> redisTemplate2;
            case THREE -> redisTemplate3;
            case FOUR -> redisTemplate4;
            case FIVE -> redisTemplate5;
            case SIX -> redisTemplate6;
            case SEVEN -> redisTemplate7;
            case EIGHT -> redisTemplate8;
            case NINE -> redisTemplate9;
            case TEN -> redisTemplate10;
            case ELEVEN -> redisTemplate11;
            case TWELVE -> redisTemplate12;
            case THIRTEEN -> redisTemplate13;
            case FOURTEEN -> redisTemplate14;
            case FIFTEEN -> redisTemplate15;
        };
    }

    /**
     * 默认Redis操作实现类
     * -- GETTER --
     * 获取默认库
     */
    @Getter
    private final RedisTemplate<String, Object> defaultRedisTemplate;

    /**
     * 初始化Redis操作实现类
     */
    public RedisTemplateSelectUtil(int defaultRedisSelect) {
        RedisSelect redisSelect = RedisSelect.getByRedisSelectValue(defaultRedisSelect);
        // 指定的默认库选择
        if (redisSelect != null) {
            // 指定默认库
            defaultRedisTemplate = getRedisTemplate(redisSelect);
        } else {
            // 未找到对应的默认库则根据Redis默认的0号库为默认
            defaultRedisTemplate = redisTemplate0;
        }
    }

}
