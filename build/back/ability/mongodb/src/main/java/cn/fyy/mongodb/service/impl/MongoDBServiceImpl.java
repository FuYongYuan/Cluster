package cn.fyy.mongodb.service.impl;

import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.mongodb.bean.ao.MongoDBCollection;
import cn.fyy.mongodb.bean.dbo.AbstractMongoDO;
import cn.fyy.mongodb.service.MongoDBService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * mongoDB 操作服务实现类
 *
 * @param <T> 实体对象类型
 * @author fyy
 */
@Slf4j
@Service
public class MongoDBServiceImpl<T extends AbstractMongoDO> implements MongoDBService<T> {
    /**
     * mongoTemplate 操作模板
     */
    @Resource
    private MongoTemplate mongoTemplate;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 插入数据-有数据则报错不更新
     *
     * @param bo 实体对象
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public T insert(T bo) throws BusinessException {
        return this.insert(bo, MongoDBCollection.SHARE);
    }

    /**
     * 插入数据-有数据则报错不更新
     *
     * @param bo         实体对象
     * @param collection 集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public T insert(T bo, MongoDBCollection collection) throws BusinessException {
        try {
            return mongoTemplate.insert(bo, collection.getName());
        } catch (Exception e) {
            throw new BusinessException("新增数据错误", e);
        }
    }

    /**
     * 保存数据-比插入慢，但如果有数据则更新
     *
     * @param bo 实体对象
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public T save(T bo) throws BusinessException {
        return this.save(bo, MongoDBCollection.SHARE);
    }

    /**
     * 保存数据-比插入慢，但如果有数据则更新
     *
     * @param bo         实体对象
     * @param collection 集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public T save(T bo, MongoDBCollection collection) throws BusinessException {
        try {
            return mongoTemplate.save(bo, collection.getName());
        } catch (Exception e) {
            throw new BusinessException("新增或修改数据错误", e);
        }
    }

    /**
     * 更新数据
     *
     * @param queryMap    查询条件
     * @param updateMap   更新条件
     * @param entityClass 实体对象类型
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public String update(Map<String, String> queryMap, Map<String, String> updateMap, Class<T> entityClass) throws BusinessException {
        return this.update(queryMap, updateMap, entityClass, MongoDBCollection.SHARE);
    }

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
    @Override
    public String update(Map<String, String> queryMap, Map<String, String> updateMap, Class<T> entityClass, MongoDBCollection collection) throws BusinessException {
        try {
            Query query = new Query();
            for (String key : queryMap.keySet()) {
                query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
            }
            Update update = new Update();
            for (String key : updateMap.keySet()) {
                update.set(key, updateMap.get(key));
            }
            UpdateResult result = mongoTemplate.upsert(query, update, entityClass, collection.getName());
            if (result.getModifiedCount() > 0) {
                return OperateResult.SUCCESS.getMessage();
            } else {
                return OperateResult.FAIL.getMessage();
            }
        } catch (Exception e) {
            throw new BusinessException("修改数据错误", e);
        }
    }

    /**
     * 删除数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public String remove(Map<String, String> queryMap, Class<T> entityClass) throws BusinessException {
        return this.remove(queryMap, entityClass, MongoDBCollection.SHARE);
    }

    /**
     * 删除数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 操作结果
     * @throws BusinessException 业务异常
     */
    @Override
    public String remove(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection) throws BusinessException {
        try {
            Query query = new Query();
            for (String key : queryMap.keySet()) {
                query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
            }
            DeleteResult result = mongoTemplate.remove(query, entityClass, collection.getName());
            if (result.getDeletedCount() > 0) {
                return OperateResult.SUCCESS.getMessage();
            } else {
                return OperateResult.FAIL.getMessage();
            }
        } catch (Exception e) {
            throw new BusinessException("删除数据错误", e);
        }
    }

    /**
     * 获取集合名称
     *
     * @return 集合名称
     */
    @Override
    public Set<String> getCollectionNames() {
        return mongoTemplate.getCollectionNames();
    }

    /**
     * 获取一条数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 数据
     */
    @Override
    public T findOne(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection) {
        Query query = new Query();
        for (String key : queryMap.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
        }
        return mongoTemplate.findOne(query, entityClass, collection.getName());
    }

    /**
     * 获取多条数据
     *
     * @param queryMap    查询条件
     * @param entityClass 实体对象类型
     * @param collection  集合
     * @return 数据
     */
    @Override
    public List<T> find(Map<String, String> queryMap, Class<T> entityClass, MongoDBCollection collection) {
        Query query = new Query();
        for (String key : queryMap.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
        }
        return mongoTemplate.find(query, entityClass, collection.getName());
    }
}
