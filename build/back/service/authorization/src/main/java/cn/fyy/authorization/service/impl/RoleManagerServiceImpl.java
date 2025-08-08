package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleManagerBO;
import cn.fyy.authorization.bean.po.RoleManagerPO;
import cn.fyy.authorization.repository.RoleManagerRepository;
import cn.fyy.authorization.service.RoleManagerService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.util.BeanUtil;
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
 * 用户角色关系 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class RoleManagerServiceImpl implements RoleManagerService {

    /**
     * 用户角色关系 Repository
     */
    @Resource
    private RoleManagerRepository roleManagerRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 用户角色关系 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(RoleManagerBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleManagerBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改用户角色关系错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 用户角色关系 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleManagerBO save(RoleManagerBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RoleManagerPO po;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                po = RoleManagerBO.toPO(bo);
            } else {
                RoleManagerPO old = roleManagerRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                po = old;
            }

            return RoleManagerBO.toBO(roleManagerRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改用户角色关系错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存集合
     *
     * @param menuIds            菜单主键ID集合
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> saveList(Long managerId, String menuIds, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (StringUtils.hasText(menuIds)) {
                // 删除原先的用户角色关系
                roleManagerRepository.deleteByManagerId(managerId);
                // 新增用户角色关系
                List<Long> menuId = Stream.of(menuIds.split(",")).map(Long::valueOf).toList();
                List<RoleManagerPO> list = new ArrayList<>();
                for (Long id : menuId) {
                    RoleManagerPO bo = RoleManagerPO.builder()
                            .managerId(managerId)
                            .roleId(id)
                            .creatorId(currentManagerId)
                            .creatorName(currentManagerName)
                            .createTime(localDateTime)
                            .updaterId(currentManagerId)
                            .updaterName(currentManagerName)
                            .updateTime(localDateTime)
                            .state(DataState.NORMAL.getCode())
                            .build();
                    list.add(bo);
                }
                List<RoleManagerBO> tUserRoleBOList = RoleManagerBO.toBO(roleManagerRepository.saveAll(list));
                if (!tUserRoleBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改用户角色关系错误", e);
        }
        return null;
    }

}
