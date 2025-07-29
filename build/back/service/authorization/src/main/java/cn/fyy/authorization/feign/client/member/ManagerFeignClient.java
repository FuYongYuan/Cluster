package cn.fyy.authorization.feign.client.member;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.member.bean.dto.ManagerDTO;
import cn.fyy.member.bean.dto.ManagerInternalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员 RestController
 *
 * @author fuyy
 */
@FeignClient(name = "member", contextId = "Manager", path = "/manager")
public interface ManagerFeignClient {

    /**
     * 根据账户、手机、邮箱、管理员名称查询管理员列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param account      账号
     * @param mobile       手机
     * @param mail         电子邮箱
     * @param managerName  管理员名称
     * @param state        状态
     * @return ManagerDTO 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    ResultMessage<PageImpl<ManagerDTO>> queryByAccountAndMobileAndEmailAndUserNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "mail", required = false) String mail,
            @RequestParam(value = "managerName", required = false) String managerName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @DeleteMapping(value = "/delete/{ids}")
    ResultMessage<Integer> delete(
            @PathVariable("ids") String ids
    ) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/{id}")
    ResultMessage<ManagerDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException;


    /**
     * 保存管理员
     *
     * @param dto 管理员对象
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody ManagerDTO dto
    ) throws BusinessException;

    /**
     * 保存管理员
     *
     * @param dto            管理员对象
     * @param authentication 鉴权串
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save/return/dto/{authentication}")
    ResultMessage<Long> saveReturnDTO(
            @RequestBody ManagerInternalDTO dto,
            @PathVariable(value = "authentication") String authentication
    ) throws BusinessException;

    /**
     * 根据id保存状态
     *
     * @param ids   需要修改的管理员ID
     * @param state 状态
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @PutMapping(value = "/update/state/{ids}/{state}")
    ResultMessage<Integer> updateStateById(
            @PathVariable("ids") String ids,
            @PathVariable(value = "state") Byte state
    ) throws BusinessException;

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/account/exist/{account}")
    ResultMessage<Boolean> getByAccountIsExist(
            @PathVariable("account") String account
    ) throws BusinessException;

    /**
     * 根据邮箱查询
     *
     * @param mail 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/mail/exist/{mail}")
    ResultMessage<Boolean> getByMailIsExist(
            @PathVariable("mail") String mail
    ) throws BusinessException;

    /**
     * 根据账号查询尝试登录次数
     *
     * @param account 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/attempt/login/number/{account}")
    ResultMessage<Integer> getAttemptLoginNumberByAccount(
            @PathVariable("account") String account
    ) throws BusinessException;

    /**
     * 根据邮箱保存密码
     *
     * @param mail           邮箱
     * @param loginPassword  密码
     * @param authentication 鉴权串
     * @return 受影响行数
     * @throws BusinessException 错误
     */
    @PutMapping(value = "/update/login/password/{mail}/{loginPassword}/{authentication}")
    ResultMessage<Integer> updateLoginPasswordByMail(
            @PathVariable("mail") String mail,
            @PathVariable("loginPassword") String loginPassword,
            @PathVariable("authentication") String authentication
    ) throws BusinessException;

    /**
     * 根据账号密码查询
     *
     * @param account       账号
     * @param loginPassword 密码
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/account/password/{account}/{loginPassword}")
    ResultMessage<ManagerInternalDTO> getByAccountAndLoginPassword(
            @PathVariable("account") String account,
            @PathVariable("loginPassword") String loginPassword
    ) throws BusinessException;
}
