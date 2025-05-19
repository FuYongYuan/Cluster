package cn.fyy.mongodb.bean.ao;

import lombok.Getter;

/**
 * MongoDB 集合名称枚举
 *
 * @author fyy
 */
@Getter
public enum MongoDBCollection {
    /**
     * 共享
     */
    SHARE("share");

    /**
     * 集合名称
     */
    private final String name;

    /**
     * 构造方法
     *
     * @param name 集合名称
     */
    MongoDBCollection(String name) {
        this.name = name;
    }

}
