package cn.fyy.message.service;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.message.bean.bo.NoticeBO;

/**
 * 公告 Service
 *
 * @author fuyy
 */
public interface NoticeService {
    /**
     * 新增或者修改
     *
     * @param bo                 公告 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(NoticeBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    NoticeBO save(NoticeBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 公告
     * @throws BusinessException 错误
     */
    NoticeBO getById(Long id) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException;
}
