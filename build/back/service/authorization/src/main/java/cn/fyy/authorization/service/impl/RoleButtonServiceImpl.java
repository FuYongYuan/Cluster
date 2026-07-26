package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleButtonBO;
import cn.fyy.authorization.bean.po.RoleButtonPO;
import cn.fyy.authorization.repository.RoleButtonRepository;
import cn.fyy.authorization.service.RoleButtonService;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.database.util.BeanUtil;
import cn.fyy.database.util.snowflake.SnowflakeIdUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 角色按钮关系 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class RoleButtonServiceImpl implements RoleButtonService {
    /**
     * 雪花算法
     */
    @Resource
    private SnowflakeIdUtil snowflakeIdUtil;

    /**
     * 角色按钮关系 Repository
     */
    @Resource
    private RoleButtonRepository roleButtonRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 角色按钮关系 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(RoleButtonBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleButtonBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色按钮关系错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 角色按钮关系 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleButtonBO save(RoleButtonBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RoleButtonPO po;
            if (bo.getId() == null) {
                po = BeanUtil.insert(
                        RoleButtonBO.toPO(bo),
                        snowflakeIdUtil.getGenerator().nextId(),
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            } else {
                RoleButtonPO old = roleButtonRepository.getReferenceById(bo.getId());
                // 根据 getNull 复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                po = BeanUtil.update(
                        old,
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            }

            return RoleButtonBO.toBO(roleButtonRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色按钮关系错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存集合
     *
     * @param roleId             角色主键 ID
     * @param buttonIds          按钮主键 ID 集合
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> saveList(Long roleId, String buttonIds, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (StringUtils.hasText(buttonIds)) {
                // 删除原先的角色按钮关系
                roleButtonRepository.deleteByRoleId(roleId);
                // 新增角色按钮关系
                List<Long> buttonId = Stream.of(buttonIds.split(",")).map(Long::valueOf).toList();
                List<RoleButtonPO> list = new ArrayList<>();
                for (Long id : buttonId) {
                    RoleButtonPO bo = BeanUtil.insert(
                            RoleButtonPO.builder()
                                    .roleId(roleId)
                                    .buttonId(id)
                                    .build(),
                            snowflakeIdUtil.getGenerator().nextId(),
                            currentManagerId,
                            currentManagerName,
                            localDateTime
                    );
                    list.add(bo);
                }
                List<RoleButtonBO> roleButtonBOList = RoleButtonBO.toBO(roleButtonRepository.saveAll(list));
                if (!roleButtonBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色按钮关系错误", e);
        }
        return null;
    }

}
