package cn.fyy.member.service.impl;

import cn.fyy.authorization.bean.dto.RoleDTO;
import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import cn.fyy.member.bean.bo.ManagerBO;
import cn.fyy.member.bean.dbo.ManagerDO;
import cn.fyy.member.bean.dto.ManagerInternalDTO;
import cn.fyy.member.config.properties.AesProperties;
import cn.fyy.member.feign.client.authorization.RoleFeignClient;
import cn.fyy.member.feign.client.authorization.RoleManagerFeignClient;
import cn.fyy.member.repository.ManagerRepository;
import cn.fyy.member.service.ManagerService;
import dispose.CopyClass;
import encrypt.AesUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 管理员 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    /**
     * 管理员 Repository
     */
    @Resource
    private ManagerRepository managerRepository;

    //------------------------------------------------------------------------------------------------------------------feign
    /**
     * 角色关系 FeignClient
     */
    @Resource
    private RoleFeignClient roleFeignClient;

    /**
     * 角色管理员关系 FeignClient
     */
    @Resource
    private RoleManagerFeignClient roleManagerFeignClient;

    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes加密信息
     */
    @Resource
    private AesProperties aesProperties;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    // 因为feign调用，使用全局事务
    @GlobalTransactional
    // 添加本地事务
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> save(ManagerBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            ManagerBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                if (StringUtils.hasText(bo.getRoleIds())) {
                    ResultMessage<String> roleManagerSaveListResult = roleManagerFeignClient.saveList(bo.getId(), bo.getRoleIds());
                    if (roleManagerSaveListResult.getCode() != HttpStatus.OK.value()) {
                        throw new RuntimeException("新增或者修改角色管理员关系错误");
                    }
                }
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改管理员错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 表_管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ManagerBO save(ManagerBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            ManagerDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                dbo = ManagerBO.toDO(bo);
            } else {
                ManagerDO old = managerRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, ManagerDO.class, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                dbo = old;
            }

            return ManagerBO.toBO(managerRepository.save(dbo));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改管理员错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法


    /**
     * 查询尝试次数大于0的管理员
     */
    @Override
    public List<ManagerBO> queryByAttemptLoginNumber() throws BusinessException {
        try {
            return ManagerBO.toBO(managerRepository.queryByAttemptLoginNumberGreaterThan(0));
        } catch (Exception e) {
            throw new BusinessException("查询尝试次数大于0的管理员错误", e);
        }
    }

    /**
     * 根据账户、手机、邮箱、管理员名称查询管理员列表
     *
     * @param account     账号
     * @param mobile      手机
     * @param mail        电子邮箱
     * @param managerName 管理员名称
     * @param state       状态
     * @return ManagerBO 表_管理员对象
     */
    @Override
    public Page<ManagerBO> queryByAccountAndMobileAndEmailAndUserNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String account,
            String mobile,
            String mail,
            String managerName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<ManagerDO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                if (state == null) {
                    CriteriaBuilder.In<Byte> in = criteriaBuilder.in(root.get("state"));
                    in.value(DataState.NORMAL.getCode());
                    in.value(DataState.FREEZE.getCode());
                    predicate = criteriaBuilder.and(in);
                } else {
                    predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), state));
                }
                if (StringUtils.hasText(account)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("account"), "%" + account + "%"));
                }
                if (StringUtils.hasText(mobile)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("mobile"), "%" + mobile + "%"));
                }
                if (StringUtils.hasText(mail)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("mail"), "%" + mail + "%"));
                }
                if (StringUtils.hasText(managerName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("managerName"), "%" + managerName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(ConstantService.getSort(root, criteriaBuilder, pageSort));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<ManagerDO> results = managerRepository.findAll(specification, pageable);
            // 返回结果
            return new PageImpl<>(ManagerBO.toBO(results.getContent()), pageable, results.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据账户、手机、邮箱、管理员名称查询管理员列表", e);
        }
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            if (StringUtils.hasText(ids)) {
                return managerRepository.updateStateByIds(
                        DataState.DELETE.getCode(),
                        currentManagerId,
                        currentManagerName,
                        LocalDateTime.now(),
                        Stream.of(ids.split(",")
                        ).map(Long::valueOf).toList());
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 管理员
     */
    @Override
    public ManagerBO getById(Long id) throws BusinessException {
        try {
            ManagerBO bo = ManagerBO.toBO(managerRepository.getReferenceById(id));
            if (bo != null) {
                ResultMessage<List<RoleDTO>> resultMessage = roleFeignClient.queryManagerHaveRoleByManagerId(bo.getId());
                List<String> roleIdList = resultMessage.getData().stream().map(r -> r.getId().toString()).toList();
                if (resultMessage.getCode() == HttpStatus.OK.value()) {
                    bo.setRoleIds(String.join(",", roleIdList));
                }
            }
            return bo;
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 管理员
     */
    @Override
    public ManagerBO getByJwtToken(Long id) throws BusinessException {
        try {
            return ManagerBO.toBO(managerRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 根据id保存状态
     *
     * @param ids                需要修改的管理员id
     * @param state              状态
     * @param currentManagerId   当前登陆管理员id
     * @param currentManagerName 当前登陆管理员名称
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateStateByIds(String ids, Byte state, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            return managerRepository.updateStateByIds(state, currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据id保存状态错误", e);
        }
    }

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return 管理员
     * @throws BusinessException 错误
     */
    @Override
    public Boolean getByAccountIsExist(String account) throws BusinessException {
        try {
            ManagerBO bo = ManagerBO.toBO(managerRepository.getByAccountIgnoreCase(account));
            return bo != null;
        } catch (Exception e) {
            throw new BusinessException("根据账号查询错误", e);
        }
    }

    /**
     * 根据邮箱查询
     *
     * @param mail 邮箱
     * @return 管理员
     * @throws BusinessException 错误
     */
    @Override
    public Boolean getByMailIsExist(String mail) throws BusinessException {
        try {
            ManagerBO bo = ManagerBO.toBO(managerRepository.getByMailIgnoreCase(mail));
            return bo != null;
        } catch (Exception e) {
            throw new BusinessException("根据邮箱查询错误", e);
        }
    }

    /**
     * 根据账号查询尝试登录次数
     *
     * @param account 账号
     * @return 管理员
     * @throws BusinessException 错误
     */
    @Override
    public Integer getAttemptLoginNumberByAccount(String account) throws BusinessException {
        try {
            ManagerBO bo = ManagerBO.toBO(managerRepository.getByAccountIgnoreCase(account));
            return bo.getAttemptLoginNumber();
        } catch (Exception e) {
            throw new BusinessException("根据账号查询错误", e);
        }
    }

    /**
     * 根据邮箱保存密码
     *
     * @param mail           邮箱
     * @param loginPassword  密码
     * @param authentication 鉴权串
     * @return 受影响行数
     * @throws BusinessException 错误
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<Integer> updateLoginPasswordByMail(String mail, String loginPassword, String authentication) throws BusinessException {
        try {
            String encryptString = mail + loginPassword;
            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
            String decrypt = AesUtil.decryptString(authentication, aesProperties.getAesKey());
            if (encrypt.equals(authentication) && decrypt.equals(encryptString)) {
                return new ResultMessage<>(managerRepository.updateLoginPasswordByMail(mail, loginPassword));
            } else {
                return new ResultMessage<>(1, "试图篡改信息拒绝请求！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据邮箱修改密码错误", e);
        }
    }

    /**
     * 根据账号查询
     *
     * @param account       账号
     * @param loginPassword 密码
     * @return 管理员
     * @throws BusinessException 错误
     */
    @Override
    @Transactional
    public ManagerBO getByAccountAndLoginPassword(String account, String loginPassword) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String visitorIpAddress = ConstantService.getVisitorIp(request);

            ManagerBO bo = ManagerBO.toBO(managerRepository.getByAccountAndLoginPassword(account, loginPassword));
            if (bo == null) {
                bo = ManagerBO.toBO(managerRepository.getByAccountIgnoreCase(account));
                if (bo != null) {
                    bo.setLastAttemptLoginTime(localDateTime);
                    bo.setLastAttemptLoginRequestIp(visitorIpAddress);
                    bo.setAttemptLoginNumber(bo.getAttemptLoginNumber() == null ? 1 : bo.getAttemptLoginNumber() + 1);

                    ManagerBO result = this.save(bo, ConstantParameter.SYSTEM_ADMIN_MANAGER_ID, ConstantParameter.SYSTEM_ADMIN_MANAGER_NAME, false);
                    if (result != null) {
                        if (result.getAttemptLoginNumber() > ConstantParameter.ATTEMPT_LOGIN_FREQUENCY) {
                            return null;
                        }
                    }
                }
                return null;
            } else {
                if (bo.getAttemptLoginNumber() > ConstantParameter.ATTEMPT_LOGIN_FREQUENCY) {
                    return null;
                } else {
                    return bo;
                }
            }
        } catch (Exception e) {
            throw new BusinessException("根据账号查询错误", e);
        }
    }


    /**
     * 保存并返回DTO
     *
     * @param dto            需要保存的DTO
     * @param authentication 鉴权串
     * @return 管理员DTO
     * @throws BusinessException 错误
     */
    @Override
    @Transactional
    public ResultMessage<Long> saveReturnDTO(ManagerInternalDTO dto, String authentication) throws BusinessException {
        try {
            String encryptString = dto.getAccount() + dto.getLoginPassword();
            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());

            if (encrypt.equals(authentication)) {
                ManagerBO bo = this.save(
                        ManagerInternalDTO.toBO(dto),
                        dto.getId() != null ? dto.getId() : ConstantParameter.SYSTEM_ADMIN_MANAGER_ID,
                        Objects.requireNonNull(dto.getManagerName(), dto.getAccount()),
                        false
                );
                return new ResultMessage<>(bo.getId());
            } else {
                return new ResultMessage<>(1, "试图篡改信息拒绝请求！");
            }
        } catch (Exception e) {
            throw new BusinessException("保存错误", e);
        }
    }
}
