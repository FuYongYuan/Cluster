package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleBO;
import cn.fyy.authorization.bean.bo.RoleManagerBO;
import cn.fyy.authorization.bean.dto.JwtDTO;
import cn.fyy.authorization.config.properties.AesProperties;
import cn.fyy.authorization.feign.client.capability.MenuFeignClient;
import cn.fyy.authorization.feign.client.member.ManagerFeignClient;
import cn.fyy.authorization.service.RoleManagerService;
import cn.fyy.authorization.service.RoleMenuService;
import cn.fyy.authorization.service.RoleService;
import cn.fyy.authorization.service.SystemService;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.common.service.ConstantService;
import cn.fyy.database.config.data.annotation.WriteDataSource;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.config.security.bean.bo.SecurityRedis;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import cn.fyy.member.bean.dto.ManagerInternalDTO;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import dispose.LocalDateTimeDispose;
import encrypt.AesUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    /**
     * 角色管理员关系 Service
     */
    @Resource
    private RoleManagerService roleManagerServiceImpl;

    /**
     * 角色 Service
     */
    @Resource
    private RoleService roleServiceImpl;

    /**
     * 角色菜单 Service
     */
    @Resource
    private RoleMenuService roleMenuServiceImpl;

    //------------------------------------------------------------------------------------------------------------------feign
    /**
     * 管理员
     */
    @Resource
    private ManagerFeignClient managerFeignClient;

    /**
     * 菜单
     */
    @Resource
    private MenuFeignClient menuFeignClient;


    //------------------------------------------------------------------------------------------------------------------JWT Redis

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    /**
     * JWT参数
     */
    @Resource
    private JwtProperties jwtProperties;

    /**
     * Redis 业务类
     */
    @Resource
    private RedisService redisServiceImpl;

    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes加密信息
     */
    @Resource
    private AesProperties aesProperties;

    //------------------------------------------------------------------------------------------------------------------自定义方法

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
    @Override
    @WriteDataSource
    // 因为feign调用，使用全局事务
    @GlobalTransactional
    // 添加本地事务
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<JwtDTO> login(String account, String loginPassword, String imageCaptcha, String imageCaptchaCacheName, HttpServletRequest request) throws BusinessException {
        try {
            LocalDateTime lastLoginDate = LocalDateTime.now();
            String visitorIpAddress = ConstantService.getVisitorIp(request);
            String captchaCheck = redisServiceImpl.get(RedisSelect.FOURTEEN, imageCaptchaCacheName, String.class);
            if (StringUtils.hasText(captchaCheck)) {
                if (imageCaptcha.equalsIgnoreCase(captchaCheck)) {
                    ManagerInternalDTO dto = managerFeignClient.getByAccountAndLoginPassword(account, loginPassword).getData();
                    if (dto == null) {
                        // 尝试登录处理
                        return this.attemptLoginDispose(account);
                    } else {
                        // 删除验证码
                        redisServiceImpl.delete(RedisSelect.FOURTEEN, imageCaptchaCacheName);
                        // 登录成功处理
                        return this.loginSuccessDispose(lastLoginDate, visitorIpAddress, dto);
                    }
                } else {
                    return new ResultMessage<>(2, "图形验证码错误！");
                }
            } else {
                return new ResultMessage<>(1, "图形验证码已过期！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据账号密码查询对应的管理员错误", e);
        }
    }

    /**
     * 根据电子邮箱、账号、密码创建新管理员
     *
     * @param mail                 电子邮箱
     * @param account              账号
     * @param loginPassword        密码
     * @param mailCaptcha          邮箱验证码
     * @param mailCaptchaCacheName 邮箱验证码缓存名称
     * @param visitorIpAddress     访问者IP
     * @return 管理员
     */
    @Override
    @WriteDataSource
    // 因为feign调用，使用全局事务
    @GlobalTransactional
    // 添加本地事务
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<Boolean> register(String mail, String account, String loginPassword, String mailCaptcha, String mailCaptchaCacheName, String visitorIpAddress) throws BusinessException {
        try {
            LocalDateTime lastLoginDate = LocalDateTime.now();
            String captchaCheck = redisServiceImpl.get(RedisSelect.FOURTEEN, mailCaptchaCacheName, String.class);
            if (StringUtils.hasText(captchaCheck)) {
                if (mailCaptcha.equalsIgnoreCase(captchaCheck)) {
                    Boolean mailExist = managerFeignClient.getByMailIsExist(mail).getData();
                    if (mailExist) {
                        return new ResultMessage<>(3, "电子邮箱已被注册！");
                    } else {
                        Boolean accountExist = managerFeignClient.getByAccountIsExist(account).getData();
                        if (accountExist) {
                            return new ResultMessage<>(4, "账号已被注册！");
                        } else {
                            // 创建管理员
                            ManagerInternalDTO dto = ManagerInternalDTO.builder()
                                    .mail(mail)
                                    .account(account)
                                    .loginPassword(loginPassword)
                                    .lastAttemptLoginRequestIp(visitorIpAddress)
                                    .lastAttemptLoginTime(lastLoginDate)
                                    .attemptLoginNumber(0)
                                    .requestIp(visitorIpAddress)
                                    .currentRequestIp(visitorIpAddress)
                                    .build();
                            // 保存管理员
                            String encryptString = dto.getAccount() + dto.getLoginPassword();
                            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());

                            Long id = managerFeignClient.saveReturnDTO(dto, encrypt).getData();
                            // 保存角色
                            RoleManagerBO roleManagerBO = RoleManagerBO.builder()
                                    .managerId(id)
                                    .roleId(ConstantParameter.SYSTEM_MANAGER_ROLE_ID)
                                    .build();
                            roleManagerServiceImpl.save(roleManagerBO, ConstantParameter.SYSTEM_ADMIN_MANAGER_ID, ConstantParameter.SYSTEM_ADMIN_MANAGER_NAME);
                            // 返回
                            return new ResultMessage<>(true);
                        }
                    }
                } else {
                    return new ResultMessage<>(2, "邮件验证码错误！");
                }
            } else {
                return new ResultMessage<>(1, "邮件验证码已过期！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据账号密码创建新管理员错误", e);
        }
    }

    /**
     * 根据电子邮箱、密码找回密码
     *
     * @param mail                 电子邮箱
     * @param loginPassword        密码
     * @param mailCaptcha          邮箱验证码
     * @param mailCaptchaCacheName 邮箱验证码缓存名称
     * @return 管理员
     */
    @Override
    @WriteDataSource
    // 因为feign调用，使用全局事务
    @GlobalTransactional
    // 添加本地事务
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<Integer> recover(String mail, String loginPassword, String mailCaptcha, String mailCaptchaCacheName) throws BusinessException {
        try {
            String captchaCheck = redisServiceImpl.get(RedisSelect.FOURTEEN, mailCaptchaCacheName, String.class);
            if (StringUtils.hasText(captchaCheck)) {
                if (mailCaptcha.equalsIgnoreCase(captchaCheck)) {
                    Boolean mailExist = managerFeignClient.getByMailIsExist(mail).getData();
                    if (!mailExist) {
                        return new ResultMessage<>(3, "电子邮箱未被注册！");
                    } else {
                        String encryptString = mail + loginPassword;
                        String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
                        return managerFeignClient.updateLoginPasswordByMail(mail, loginPassword, encrypt);
                    }
                } else {
                    return new ResultMessage<>(2, "邮件验证码错误！");
                }
            } else {
                return new ResultMessage<>(1, "邮件验证码已过期！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据账号密码创建新管理员错误", e);
        }
    }

    /**
     * 登出
     *
     * @param managerId 当前登陆人ID
     * @return 登出结果
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<Boolean> logout(Long managerId) throws BusinessException {
        try {
            redisServiceImpl.delete(RedisSelect.FIFTEEN, ConstantParameter.MANAGER_JWT_INFO_KEY + managerId);
            return new ResultMessage<>(true);
        } catch (Exception e) {
            throw new BusinessException("登出失败", e);
        }
    }

    /**
     * 根据管理员ID查询拥有的菜单权限
     *
     * @param managerId 当前登陆人ID
     * @return 管理员拥有的菜单
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<List<MenuDTO>> queryManagerHaveMenuByManagerId(Long managerId) throws BusinessException {
        try {
            List<Long> roleList = roleServiceImpl.queryManagerHaveRoleByManagerId(managerId).stream().map(RoleBO::getId).toList();
            List<Long> menuList = roleMenuServiceImpl.queryMenuIdsByRoleIds(roleList);
            return menuFeignClient.queryHierarchyMenuByMenuIdList(menuList);
        } catch (Exception e) {
            throw new BusinessException("根据管理员ID查询拥有的菜单权限", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------私有方法

    /**
     * 尝试登录处理
     *
     * @param account 账号
     * @return 记录登录错误
     * @throws BusinessException 尝试登录处理错误
     */
    private ResultMessage<JwtDTO> attemptLoginDispose(String account) throws BusinessException {
        try {
            Boolean accountExist = managerFeignClient.getByAccountIsExist(account).getData();
            if (accountExist) {
                Integer attemptLoginNumber = managerFeignClient.getAttemptLoginNumberByAccount(account).getData();
                if (attemptLoginNumber > ConstantParameter.ATTEMPT_LOGIN_FREQUENCY) {
                    return new ResultMessage<>(3, "尝试登录次数过多管理员已被冻结！请联系管理员！");
                }
            }
            return new ResultMessage<>(4, "登录管理员账号或密码错误！");
        } catch (Exception e) {
            throw new BusinessException("尝试登录处理错误", e);
        }
    }

    /**
     * 登录成功处理
     *
     * @param localDateTime    当前时间
     * @param visitorIpAddress IP地址
     * @param dto              管理员对象
     * @return 登录成功
     * @throws BusinessException 登录成功处理错误
     */
    private ResultMessage<JwtDTO> loginSuccessDispose(LocalDateTime localDateTime, String visitorIpAddress, ManagerInternalDTO dto) throws BusinessException {
        try {
            if (dto.getAttemptLoginNumber() != null && dto.getAttemptLoginNumber() > ConstantParameter.ATTEMPT_LOGIN_FREQUENCY) {
                return new ResultMessage<>(5, "尝试登录次数过多管理员已被冻结！请联系管理员！");
            } else {
                if (dto.getState() == DataState.NORMAL.getCode()) {
                    dto.setLastAttemptLoginTime(localDateTime);
                    dto.setLastAttemptLoginRequestIp(visitorIpAddress);
                    dto.setAttemptLoginNumber(0);
                    dto.setCurrentRequestIp(visitorIpAddress);

                    String encryptString = dto.getAccount() + dto.getLoginPassword();
                    String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());

                    ResultMessage<Long> saveResult = managerFeignClient.saveReturnDTO(dto, encrypt);
                    Integer saveState = saveResult.getCode();
                    if (saveState == HttpStatus.OK.value()) {
                        String[] roleBOList = roleServiceImpl.queryManagerHaveRoleByManagerId(dto.getId())
                                .stream()
                                .map(i -> String.valueOf(i.getId()))
                                .toArray(String[]::new);

                        // 返回token的管理员
                        SecurityRedis securityRedis = SecurityRedis.builder()
                                .managerId(dto.getId())
                                .managerName(dto.getManagerName())
                                .username(dto.getAccount())
                                .password(dto.getLoginPassword())
                                .authorities(roleBOList)
                                .seconds(jwtProperties.getAccessTokenExpireTime().toMillis() / 1000)
                                .build();
                        // 创建SecurityUser
                        SecurityUser securityUser = new SecurityUser(
                                securityRedis.getManagerId(),
                                securityRedis.getManagerName(),
                                securityRedis.getUsername(),
                                securityRedis.getPassword(),
                                AuthorityUtils.createAuthorityList(securityRedis.getAuthorities())
                        );

                        // 创建token
                        String token = jwtTokenWebService.generateToken(
                                jwtProperties.getIssuer(),
                                securityRedis.getUsername(),
                                securityUser,
                                jwtProperties.getAccessTokenExpireTime().toMillis()
                        );
                        securityRedis.setToken(token);

                        // 失效时间，必须在入redis之前计算好，进入redis后才不会因为代码执行顺序的问题导致秒级过期问题
                        LocalDateTime invalidDate = LocalDateTimeDispose.secondsCalculate(localDateTime, Math.toIntExact(securityRedis.getSeconds()));

                        redisServiceImpl.set(
                                RedisSelect.FIFTEEN,
                                ConstantParameter.MANAGER_JWT_INFO_KEY + securityRedis.getManagerId(),
                                securityRedis,
                                securityRedis.getSeconds()
                        );

                        JwtDTO jwtDTO = new JwtDTO().toBuilder()
                                .account(dto.getAccount())
                                .managerName(dto.getManagerName())
                                .headImgUrl(dto.getHeadImgUrl())
                                .jwtToken(securityRedis.getToken())
                                .invalidDate(invalidDate)
                                .build();

                        return new ResultMessage<>(jwtDTO);
                    } else {
                        return new ResultMessage<>(9, "登录信息更新失败！" + saveResult.getMessage());
                    }
                } else if (dto.getState() == DataState.FREEZE.getCode()) {
                    return new ResultMessage<>(8, "登录管理员已被冻结！");
                } else if (dto.getState() == DataState.DELETE.getCode()) {
                    return new ResultMessage<>(7, "登录管理员已被注销！");
                } else {
                    return new ResultMessage<>(6, "登录管理员状态丢失！");
                }
            }
        } catch (Exception e) {
            throw new BusinessException("登录成功处理错误", e);
        }
    }
}
