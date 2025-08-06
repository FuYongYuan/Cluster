package cn.fyy.member.service;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.member.bean.bo.ManagerBO;
import cn.fyy.member.bean.dto.ManagerInternalDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 管理员 Service
 *
 * @author fuyy
 */
public interface ManagerService {

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(ManagerBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

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
    ManagerBO save(ManagerBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 查询尝试次数大于0的管理员
     *
     * @return 管理员集合
     * @throws BusinessException 错误
     */
    List<ManagerBO> queryByAttemptLoginNumber() throws BusinessException;

    /**
     * 根据账户、手机、邮箱、管理员名称查询管理员列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param pageSort     排序
     * @param account      账号
     * @param mobile       手机
     * @param mail         电子邮箱
     * @param managerName  管理员名称
     * @param state        状态
     * @return ManagerBO 管理员对象
     * @throws BusinessException 错误
     */
    Page<ManagerBO> queryByAccountAndMobileAndEmailAndUserNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String account,
            String mobile,
            String mail,
            String managerName,
            Byte state
    ) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 管理员
     * @throws BusinessException 错误
     */
    ManagerBO getById(Long id) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 管理员
     * @throws BusinessException 错误
     */
    ManagerBO getByJwtToken(Long id) throws BusinessException;

    /**
     * 根据id保存状态
     *
     * @param ids                需要修改的管理员id
     * @param state              状态
     * @param currentManagerId   当前登陆管理员id
     * @param currentManagerName 当前登陆人名称
     * @return 受影响行数
     * @throws BusinessException 错误
     */
    int updateStateByIds(String ids, Byte state, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return 管理员
     * @throws BusinessException 错误
     */
    Boolean getByAccountIsExist(String account) throws BusinessException;

    /**
     * 根据邮箱查询
     *
     * @param mail 邮箱
     * @return 管理员
     * @throws BusinessException 错误
     */
    Boolean getByMailIsExist(String mail) throws BusinessException;

    /**
     * 根据账号查询尝试登录次数
     *
     * @param account 账号
     * @return 管理员
     * @throws BusinessException 错误
     */
    Integer getAttemptLoginNumberByAccount(String account) throws BusinessException;

    /**
     * 根据邮箱保存密码
     *
     * @param mail           邮箱
     * @param loginPassword  密码
     * @param authentication 鉴权串
     * @return 受影响行数
     * @throws BusinessException 错误
     */
    ResultMessage<Integer> updateLoginPasswordByMail(String mail, String loginPassword, String authentication) throws BusinessException;

    /**
     * 根据账号查询
     *
     * @param account       账号
     * @param loginPassword 密码
     * @return 管理员
     * @throws BusinessException 错误
     */
    ManagerBO getByAccountAndLoginPassword(String account, String loginPassword) throws BusinessException;

    /**
     * 保存并返回DTO
     *
     * @param dto            需要保存的DTO
     * @param authentication 鉴权串
     * @return 管理员DTO
     * @throws BusinessException 错误
     */
    ResultMessage<Long> saveReturnDTO(ManagerInternalDTO dto, String authentication) throws BusinessException;
}
