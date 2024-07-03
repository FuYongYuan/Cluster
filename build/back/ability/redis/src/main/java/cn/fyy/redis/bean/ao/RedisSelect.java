package cn.fyy.redis.bean.ao;

import lombok.Getter;

/**
 * 通过枚举类选择redis使用的数据库
 * <p>
 * 一般公司的redis会分不同的数据到不同的库，比如用户信息到 0 库，商品数据放在第二个库，运动数据放在 3 库等等跟随项目分配而分配
 *
 * @author fuyy
 */
@Getter
public enum RedisSelect {
    // 以下分别代表redis 0-15库
    /**
     * 0号库
     */
    INFO(0),
    /**
     * 1号库
     */
    ONE(1),
    /**
     * 2号库
     */
    TWO(2),
    /**
     * 3号库
     */
    THREE(3),
    /**
     * 4号库
     */
    FOUR(4),
    /**
     * 5号库
     */
    FIVE(5),
    /**
     * 6号库
     */
    SIX(6),
    /**
     * 7号库
     */
    SEVEN(7),
    /**
     * 8号库
     */
    EIGHT(8),
    /**
     * 9号库
     */
    NINE(9),
    /**
     * 10号库
     */
    TEN(10),
    /**
     * 11号库
     */
    ELEVEN(11),
    /**
     * 12号库
     */
    TWELVE(12),
    /**
     * 13号库
     */
    THIRTEEN(13),
    /**
     * 14号库 -- 已做验证码存储
     */
    FOURTEEN(14),
    /**
     * 15号库 -- 已做JWT存储
     */
    FIFTEEN(15);

    /**
     * 值
     */
    private final int value;

    /**
     * Redis 库
     *
     * @param value 几号
     */
    RedisSelect(int value) {
        this.value = value;
    }


    /**
     * 根据给定的数据库编号获取对应的RedisSelect枚举值。
     * 如果没有找到匹配的枚举，则返回null。
     *
     * @param redisSelectValue 数据库编号
     * @return 匹配的RedisSelect枚举值，如果没有找到则返回null
     */
    public static RedisSelect getByRedisSelectValue(int redisSelectValue) {
        for (RedisSelect redisSelect : values()) {
            if (redisSelect.getValue() == redisSelectValue) {
                return redisSelect;
            }
        }
        return null;
    }

}