package cn.fyy.authorization.service;

import cn.fyy.authorization.bean.dto.JwtDTO;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 系统 Service
 *
 * @author fuyy
 */
public interface SystemService {
    /**
     * 根据账号密码查询对应的管理员
     *
     * @param account               账号
     * @param loginPassword         密码
     * @param imageCaptcha          图形验证码
     * @param imageCaptchaCacheName 图形验证码缓存名称
     * @param request               请求内容
     * @return 管理员
     * @throws BusinessException 错误
     */
    ResultMessage<JwtDTO> login(String account, String loginPassword, String imageCaptcha, String imageCaptchaCacheName, HttpServletRequest request) throws BusinessException;

    /**
     * 根据电子邮箱、账号、密码创建新管理员
     *
     * @param mail                 电子邮箱
     * @param account              账号
     * @param loginPassword        密码
     * @param mailCaptcha          验证码
     * @param mailCaptchaCacheName 邮箱验证码缓存名称
     * @param visitorIpAddress     访问者IP
     * @return 管理员
     * @throws BusinessException 错误
     */
    ResultMessage<Boolean> register(String mail, String account, String loginPassword, String mailCaptcha, String mailCaptchaCacheName, String visitorIpAddress) throws BusinessException;

    /**
     * 根据电子邮箱、密码找回密码
     *
     * @param mail                 电子邮箱
     * @param loginPassword        密码
     * @param mailCaptcha          验证码
     * @param mailCaptchaCacheName 邮箱验证码缓存名称
     * @return 管理员
     * @throws BusinessException 错误
     */
    ResultMessage<Integer> recover(String mail, String loginPassword, String mailCaptcha, String mailCaptchaCacheName) throws BusinessException;

    /**
     * 登出
     *
     * @param managerId 当前登陆人ID
     * @return 登出结果
     * @throws BusinessException 错误
     */
    ResultMessage<Boolean> logout(Long managerId) throws BusinessException;

    /**
     * 根据管理员ID查询拥有的菜单权限
     *
     * @param managerId 当前登陆人ID
     * @return 管理员拥有的菜单
     * @throws BusinessException 错误
     */
    ResultMessage<List<MenuDTO>> queryManagerHaveMenuByManagerId(Long managerId) throws BusinessException;
}
