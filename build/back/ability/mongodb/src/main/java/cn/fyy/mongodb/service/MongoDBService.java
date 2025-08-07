package cn.fyy.mongodb.service;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.mongodb.bean.ao.MongoDBCollection;
import cn.fyy.mongodb.bean.po.AbstractMongoPO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MongoDB 操作
 *
 * @author fyy
 */
public interface MongoDBService<T extends AbstractMongoPO> {
    /**
     * 插入数据-有数据则报错不更新
     *
     * @param bo 实体对象
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    T insert(T bo) throws BusinessException;

    /**
     * 插入数据-有数据则报错不更新
     *
     * @param bo         实体对象
     * @param collection 集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    T insert(T bo, MongoDBCollection collection) throws BusinessException;

    /**
     * 保存数据-比插入慢，但如果有数据则更新
     *
     * @param bo 实体对象
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    T save(T bo) throws BusinessException;

    /**
     * 保存数据-比插入慢，但如果有数据则更新
     *
     * @param bo         实体对象
     * @param collection 集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    T save(T bo, MongoDBCollection collection) throws BusinessException;

    /**
     * 更新数据
     *
     * @param queryMap    查询条件
     * @param updateMap   更新条件
     * @param entityClass 实体对象类型
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    String update(Map<String, String> queryMap, Map<String, String> updateMap, Class<T> entityClass) throws BusinessException;

    /**
     * 更新数据
     *
     * @param queryMap    查询条件
     * @param updateMap   更新条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    String update(Map<String, String> queryMap, Map<String, String> updateMap, Class<T> entityClass, MongoDBCollection collection) throws BusinessException;

    /**
     * 删除数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    String remove(Map<String, String> queryMap, Class<T> entityClass) throws BusinessException;

    /**
     * 删除数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    String remove(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection) throws BusinessException;

    /**
     * 获取集合名称
     *
     * @return 集合名称
     */
    Set<String> getCollectionNames();

    /**
     * 获取一条数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 数据
     */
    T findOne(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection);

    /**
     * 获取多条数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 数据
     */
    List<T> find(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection);
}
