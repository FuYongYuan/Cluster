package cn.fyy.builder.service.impl;

import cn.fyy.builder.bean.bo.CommonlyVersionBO;
import cn.fyy.builder.bean.po.CommonlyVersionPO;
import cn.fyy.builder.repository.CommonlyVersionRepository;
import cn.fyy.builder.service.CommonlyVersionService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.util.BeanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * 常用版本 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class CommonlyVersionServiceImpl implements CommonlyVersionService {

    /**
     * 常用版本 Repository
     */
    @Resource
    private CommonlyVersionRepository commonlyVersionRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 常用版本 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(CommonlyVersionBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            CommonlyVersionBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改常用版本错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 常用版本 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public CommonlyVersionBO save(CommonlyVersionBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            CommonlyVersionPO po;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                po = CommonlyVersionBO.toPO(bo);
            } else {
                CommonlyVersionPO old = commonlyVersionRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                po = old;
            }

            return CommonlyVersionBO.toBO(commonlyVersionRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改常用版本错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 常用版本
     */
    @Override
    public CommonlyVersionBO getById(Long id) throws BusinessException {
        try {
            return CommonlyVersionBO.toBO(commonlyVersionRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
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
            return commonlyVersionRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据用户主键查询
     *
     * @param managerId 管理员主键ID
     * @return 常用版本
     */
    @Override
    public CommonlyVersionBO getByManagerId(Long managerId) throws BusinessException {
        try {
            return CommonlyVersionBO.toBO(commonlyVersionRepository.getByManagerId(managerId));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }
}
