package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.JwtDTO;
import cn.fyy.authorization.service.SystemService;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统 RestController
 *
 * @author fuyy
 */
@Tag(name = "SystemRestController", description = "系统 RestController")
@RestController
@RequestMapping(value = "/system", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemRestController {
    /**
     * 管理员 Service
     */
    @Resource
    private SystemService systemServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * JWT 配置
     */
    @Resource
    private JwtProperties jwtProperties;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据电子邮箱、账号、密码登录管理员
     *
     * @param account               账号account
     * @param loginPassword         密码passwordMD5
     * @param imageCaptcha          图形验证码
     * @param imageCaptchaCacheName 图形验证码缓存名称
     * @return ManagerDTO 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据电子邮箱、账号、密码登录管理员", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "account", description = "账号", required = true),
                    @Parameter(name = "loginPassword", description = "密码", required = true),
                    @Parameter(name = "imageCaptcha", description = "图形验证码", required = true),
                    @Parameter(name = "imageCaptchaCacheName", description = "图形验证码缓存名称", required = true)
            }
    )
    @PostMapping(value = "/login/{account}/{loginPassword}/{imageCaptcha}/{imageCaptchaCacheName}")
    public ResultMessage<JwtDTO> login(
            HttpServletRequest request,
            @PathVariable("account") String account,
            @PathVariable("loginPassword") String loginPassword,
            @PathVariable("imageCaptcha") String imageCaptcha,
            @PathVariable("imageCaptchaCacheName") String imageCaptchaCacheName
    ) throws BusinessException {
        return systemServiceImpl.login(account, loginPassword, imageCaptcha, imageCaptchaCacheName, request);
    }

    /**
     * 根据电子邮箱、账号、密码创建新管理员
     *
     * @param mail                 电子邮箱
     * @param account              账号account
     * @param loginPassword        密码passwordMD5
     * @param mailCaptcha          邮件验证码
     * @param mailCaptchaCacheName 邮件验证码缓存名称
     * @return ManagerDTO 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据账号密码创建新管理员", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "mail", description = "电子邮箱", required = true),
                    @Parameter(name = "account", description = "账号", required = true),
                    @Parameter(name = "loginPassword", description = "密码", required = true),
                    @Parameter(name = "mailCaptcha", description = "邮件验证码", required = true),
                    @Parameter(name = "mailCaptchaCacheName", description = "邮件验证码缓存名称", required = true)
            }
    )
    @PostMapping(value = "/register/{mail}/{account}/{loginPassword}/{mailCaptcha}/{mailCaptchaCacheName}")
    public ResultMessage<Boolean> register(
            HttpServletRequest request,
            @PathVariable("mail") String mail,
            @PathVariable("account") String account,
            @PathVariable("loginPassword") String loginPassword,
            @PathVariable("mailCaptcha") String mailCaptcha,
            @PathVariable("mailCaptchaCacheName") String mailCaptchaCacheName
    ) throws BusinessException {
        return systemServiceImpl.register(mail, account, loginPassword, mailCaptcha, mailCaptchaCacheName, ConstantService.getVisitorIp(request));
    }

    /**
     * 根据电子邮箱、密码找回密码
     *
     * @param mail                 电子邮箱
     * @param loginPassword        密码passwordMD5
     * @param mailCaptcha          邮件验证码
     * @param mailCaptchaCacheName 邮件验证码缓存名称
     * @return ManagerDTO 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据电子邮箱、密码找回密码", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "mail", description = "电子邮箱", required = true),
                    @Parameter(name = "loginPassword", description = "密码", required = true),
                    @Parameter(name = "mailCaptcha", description = "邮件验证码", required = true),
                    @Parameter(name = "mailCaptchaCacheName", description = "邮件验证码缓存名称", required = true)
            }
    )
    @PutMapping(value = "/recover/{mail}/{loginPassword}/{mailCaptcha}/{mailCaptchaCacheName}")
    public ResultMessage<Integer> recover(
            @PathVariable("mail") String mail,
            @PathVariable("loginPassword") String loginPassword,
            @PathVariable("mailCaptcha") String mailCaptcha,
            @PathVariable("mailCaptchaCacheName") String mailCaptchaCacheName
    ) throws BusinessException {
        return systemServiceImpl.recover(mail, loginPassword, mailCaptcha, mailCaptchaCacheName);
    }

    /**
     * 登出清除当前登录人
     *
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "登出", description = "是否成功"
    )
    @PostMapping(value = "/logout")
    public ResultMessage<Boolean> logout(
    ) throws BusinessException {
        try {
            return systemServiceImpl.logout(
                    jwtTokenUtil.getManagerIdFromToken(ConstantService.getRequestToken(jwtProperties))
            );
        } catch (Exception e) {
            throw new BusinessException("管理员未登录");
        }
    }

    /**
     * 根据JWTToken查询拥有的菜单权限
     *
     * @return 管理员拥有的菜单
     * @throws BusinessException 错误
     */
    @Operation(
            summary = "根据JWTToken查询拥有的菜单权限", description = "MenuDTO 管理员拥有的菜单"
    )
    @GetMapping(value = "/query/manager/have/menu")
    public ResultMessage<List<MenuDTO>> queryManagerHaveMenuByJwtToken(
    ) throws BusinessException {
        return systemServiceImpl.queryManagerHaveMenuByManagerId(
                jwtTokenUtil.getManagerIdFromToken(ConstantService.getRequestToken(jwtProperties))
        );
    }
}
