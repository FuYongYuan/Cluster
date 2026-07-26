package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleApiBO;
import cn.fyy.authorization.bean.po.RoleApiPO;
import cn.fyy.authorization.repository.RoleApiRepository;
import cn.fyy.authorization.service.RoleApiService;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.database.util.BeanUtil;
import cn.fyy.database.util.snowflake.SnowflakeIdUtil;
import cn.fyy.jpa.bean.ao.DataState;
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
 * 角色API接口访问关系 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class RoleApiServiceImpl implements RoleApiService {
    /**
     * 雪花算法
     */
    @Resource
    private SnowflakeIdUtil snowflakeIdUtil;

    /**
     * 角色API关系 Repository
     */
    @Resource
    private RoleApiRepository roleApiRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 角色API关系 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(RoleApiBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleApiBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色API关系错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 角色API关系 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleApiBO save(RoleApiBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RoleApiPO po;
            if (bo.getId() == null) {
                po = BeanUtil.insert(
                        RoleApiBO.toPO(bo),
                        snowflakeIdUtil.getGenerator().nextId(),
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            } else {
                RoleApiPO old = roleApiRepository.getReferenceById(bo.getId());
                // 根据 getNull 复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                po = BeanUtil.update(
                        old,
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            }

            return RoleApiBO.toBO(roleApiRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色API关系错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存集合
     *
     * @param roleId             角色主键 ID
     * @param apiIds             API主键 ID 集合
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> saveList(Long roleId, String apiIds, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (StringUtils.hasText(apiIds)) {
                // 删除原先的角色API关系
                roleApiRepository.deleteByRoleId(roleId);
                // 新增角色API关系
                List<Long> apiId = Stream.of(apiIds.split(",")).map(Long::valueOf).toList();
                List<RoleApiPO> list = new ArrayList<>();
                for (Long id : apiId) {
                    RoleApiPO po = BeanUtil.insert(
                            RoleApiPO.builder()
                                    .roleId(roleId)
                                    .apiId(id)
                                    .build(),
                            snowflakeIdUtil.getGenerator().nextId(),
                            currentManagerId,
                            currentManagerName,
                            localDateTime
                    );
                    list.add(po);
                }
                List<RoleApiBO> roleApiBOList = RoleApiBO.toBO(roleApiRepository.saveAll(list));
                if (!roleApiBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色API关系错误", e);
        }
        return null;
    }

    /**
     * 根据角色主键 ID 查询拥有的 API ID 集合
     *
     * @param roleId 角色主键 ID
     * @return API ID 集合
     */
    @Override
    public List<Long> queryApiIdsByRoleIds(List<Long> roleId) throws BusinessException {
        try {
            List<RoleApiBO> roleApiBOList = RoleApiBO.toBO(roleApiRepository.queryByRoleIdsAndState(roleId, DataState.NORMAL.getCode()));
            if (roleApiBOList.isEmpty()) {
                return null;
            } else {
                List<Long> apiList = new ArrayList<>();

                roleApiBOList.forEach(bo -> apiList.add(bo.getApiId()));

                return apiList;
            }
        } catch (Exception e) {
            throw new BusinessException("根据角色主键 ID 查询拥有的 API ID 集合错误", e);
        }
    }
}
