package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleManagerBO;
import cn.fyy.authorization.bean.dbo.RoleManagerDO;
import cn.fyy.authorization.repository.RoleManagerRepository;
import cn.fyy.authorization.service.RoleManagerService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import dispose.CopyClass;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
    public ResultMessage<String> save(RoleManagerBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleManagerBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS);
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL);
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
    public RoleManagerBO save(RoleManagerBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            Date date = new Date();
            RoleManagerDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(date);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(date);
                bo.setState(DataState.NORMAL);
                dbo = RoleManagerBO.toDO(bo);
            } else {
                RoleManagerDO old = roleManagerRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, RoleManagerDO.class, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(date);
                dbo = old;
            }

            return RoleManagerBO.toBO(roleManagerRepository.save(dbo));
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
    public ResultMessage<String> saveList(BigInteger managerId, String menuIds, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            Date date = new Date();
            if (StringUtils.hasText(menuIds)) {
                // 删除原先的用户角色关系
                roleManagerRepository.deleteByManagerId(managerId);
                // 新增用户角色关系
                List<BigInteger> menuId = Stream.of(menuIds.split(",")).map(BigInteger::new).toList();
                List<RoleManagerDO> list = new ArrayList<>();
                for (BigInteger id : menuId) {
                    RoleManagerDO bo = RoleManagerDO.builder()
                            .managerId(managerId)
                            .roleId(id)
                            .creatorId(currentManagerId)
                            .creatorName(currentManagerName)
                            .createTime(date)
                            .updaterId(currentManagerId)
                            .updaterName(currentManagerName)
                            .updateTime(date)
                            .state(DataState.NORMAL)
                            .build();
                    list.add(bo);
                }
                List<RoleManagerBO> tUserRoleBOList = RoleManagerBO.toBO(roleManagerRepository.saveAll(list));
                if (!tUserRoleBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS);
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL);
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改用户角色关系错误", e);
        }
        return null;
    }

}
