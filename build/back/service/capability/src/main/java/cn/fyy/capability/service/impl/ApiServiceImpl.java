package cn.fyy.capability.service.impl;

import cn.fyy.capability.bean.bo.ApiBO;
import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.capability.bean.po.ApiPO;
import cn.fyy.capability.config.properties.AesProperties;
import cn.fyy.capability.repository.ApiRepository;
import cn.fyy.capability.service.ApiService;
import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.database.util.BeanUtil;
import cn.fyy.database.util.snowflake.SnowflakeIdUtil;
import cn.fyy.jpa.bean.ao.DataState;
import encrypt.AesUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Api 信息 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {
    /**
     * 雪花算法
     */
    @Resource
    private SnowflakeIdUtil snowflakeIdUtil;

    /**
     * Api 信息 Repository
     */
    @Resource
    private ApiRepository apiRepository;
    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes 加密信息
     */
    @Resource
    private AesProperties aesProperties;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 Api 信息 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(ApiBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            ApiBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改 Api 信息错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 Api 信息 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public ApiBO save(ApiBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            ApiPO po;
            if (bo.getId() == null) {
                po = BeanUtil.insert(
                        ApiBO.toPO(bo),
                        snowflakeIdUtil.getGenerator().nextId(),
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            } else {
                ApiPO old = apiRepository.getReferenceById(bo.getId());
                // 根据 getNull 复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                po = BeanUtil.update(
                        old,
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            }

            return ApiBO.toBO(apiRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改 Api 信息错误", e);
        }
    }


    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 新增或者修改集合
     *
     * @param bos            Api 信息 BO
     * @param authentication 鉴权串
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<String> saveList(List<ApiBO> bos, String authentication) throws BusinessException {
        try {
            String encryptString = String.valueOf(bos.size());
            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
            String decrypt = AesUtil.decryptString(authentication, aesProperties.getAesKey());
            if (encrypt.equals(authentication) && decrypt.equals(encryptString)) {
                return this.saveList(bos, ConstantParameter.SYSTEM_ADMIN_MANAGER_ID, ConstantParameter.SYSTEM_ADMIN_MANAGER_NAME, false);
            } else {
                return new ResultMessage<>(1, "试图篡改信息拒绝请求！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据管理员主键 ID 查询能够使用的API信息列表错误", e);
        }
    }

    /**
     * 新增或者修改集合
     *
     * @param list               Api 信息 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<String> saveList(List<ApiBO> list, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            List<String> mappings = list.stream()
                    .map(ApiBO::getMethodMapping)
                    .collect(Collectors.toList());

            List<ApiPO> olds = apiRepository.findByMethodMappingIn(mappings);

            Map<String, ApiPO> map = olds.stream()
                    .collect(Collectors.toMap(ApiPO::getMethodMapping, e -> e));

            LocalDateTime localDateTime = LocalDateTime.now();
            List<ApiPO> pos = new ArrayList<>();
            for (ApiBO bo : list) {
                ApiPO po;
                ApiPO old = map.get(bo.getMethodMapping());
                if (old == null) {
                    po = BeanUtil.insert(
                            ApiBO.toPO(bo),
                            snowflakeIdUtil.getGenerator().nextId(),
                            currentManagerId,
                            currentManagerName,
                            localDateTime
                    );
                } else {
                    // 根据 getNull 复制其中的非空或包含空字段
                    BeanUtil.copyProperties(bo, old, getNull);
                    po = BeanUtil.update(
                            old,
                            currentManagerId,
                            currentManagerName,
                            localDateTime
                    );
                }
                pos.add(po);
            }
            List<ApiBO> bos = ApiBO.toBO(apiRepository.saveAll(pos));
            if (!bos.isEmpty()) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色用户关系错误", e);
        }
        return new ResultMessage<>(OperateResult.FAIL.getMessage());
    }

    /**
     * 查询全部 Api 信息
     *
     * @return Api 信息集合
     */
    @Override
    public List<ApiBO> queryAll() throws BusinessException {
        try {
            return ApiBO.toBO(
                    apiRepository.queryAll()
            );
        } catch (Exception e) {
            throw new BusinessException("查询全部 Api 信息错误", e);
        }
    }

    /**
     * 根据管理员主键 ID 查询能够使用的API信息列表
     *
     * @param apiIds         API ID 集合
     * @param authentication 鉴权串
     * @return 能够使用的API信息列表
     */
    @Override
    public ResultMessage<List<ApiDTO>> feignQueryApiByApiIdList(
            List<Long> apiIds,
            String authentication
    ) throws BusinessException {
        try {
            String encryptString = String.valueOf(apiIds.size());
            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
            String decrypt = AesUtil.decryptString(authentication, aesProperties.getAesKey());
            if (encrypt.equals(authentication) && decrypt.equals(encryptString)) {
                return new ResultMessage<>(ApiDTO.toDTO(ApiBO.toBO(
                        apiRepository.queryApiByApiIdListAndState(apiIds, DataState.NORMAL.getCode())
                )));
            } else {
                return new ResultMessage<>(1, "试图篡改信息拒绝请求！");
            }
        } catch (Exception e) {
            throw new BusinessException("根据管理员主键 ID 查询能够使用的API信息列表错误", e);
        }
    }
}
