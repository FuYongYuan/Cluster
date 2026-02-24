package cn.fyy.capability.service.impl;

import cn.fyy.capability.bean.bo.ApiBO;
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
            throw new BusinessException("根据管理员主键 ID 查询能够使用的菜单列表错误", e);
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
                    .map(ApiBO::getMapping)
                    .collect(Collectors.toList());

            List<ApiPO> olds = apiRepository.findByMappingIn(mappings);

            Map<String, ApiPO> map = olds.stream()
                    .collect(Collectors.toMap(ApiPO::getMapping, e -> e));

            LocalDateTime localDateTime = LocalDateTime.now();
            List<ApiPO> pos = new ArrayList<>();
            for (ApiBO bo : list) {
                ApiPO po;
                ApiPO old = map.get(bo.getMapping());
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
            pos.forEach(po -> log.info("{}", po));
            List<ApiBO> bos = ApiBO.toBO(apiRepository.saveAll(pos));
            if (!bos.isEmpty()) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改用户角色关系错误", e);
        }
        return new ResultMessage<>(OperateResult.FAIL.getMessage());
    }

}
