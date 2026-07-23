package cn.fyy.authorization.service;

import cn.fyy.authorization.bean.bo.RoleApiBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;

/**
 * 角色API接口访问关系 Service
 *
 * @author fyy
 */
public interface RoleApiService {
    /**
     * 新增或者修改
     *
     * @param bo                 角色API关系 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(RoleApiBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 新增或者修改
     *
     * @param bo                 角色API关系 BO
     * @param currentManagerId   当前登陆人 ID
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    RoleApiBO save(RoleApiBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 保存集合
     *
     * @param roleId             角色主键 ID
     * @param apiIds             API主键 ID 集合
     * @param currentManagerId   当前登录人
     * @param currentManagerName 当前登录人名称
     * @return 是否成功
     * @throws BusinessException 错误
     */
    ResultMessage<String> saveList(Long roleId, String apiIds, Long currentManagerId, String currentManagerName) throws BusinessException;

}
