package cn.fyy.job.service;

import cn.fyy.capability.bean.bo.ApiBO;
import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.bo.RestControllerMappingBO;
import cn.fyy.job.config.feign.client.capability.ApiFeignClient;
import cn.fyy.job.config.properties.AesProperties;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import encrypt.AesUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能 - Api 任务服务
 *
 * @author fyy
 **/
@Service
public class CapabilityApiJobService {
    /**
     * Api 信息 FeignClient
     */
    @Resource
    private ApiFeignClient apiFeignClient;

    @Resource
    private RedisService redisServiceImpl;
    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes 加密信息
     */
    @Resource
    private AesProperties aesProperties;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * Api 保存任务
     */
    @XxlJob("ApiSaveJobHandler")
    public void apiSaveJobHandler() throws Exception {
        XxlJobHelper.log("<<<<<<<<<<<<======== ApiSaveJobHandler 开始 ========>>>>>>>>>>>>");

        List<RestControllerMappingBO> mappingBOList = redisServiceImpl.getAll(
                RedisSelect.THIRTEEN,
                ConstantParameter.MAPPING_KEY + "*",
                RestControllerMappingBO.class
        );
        if (mappingBOList != null) {
            List<ApiBO> apiBOList = new ArrayList<>();
            for (RestControllerMappingBO restControllerMappingBO : mappingBOList) {
                XxlJobHelper.log("{}", restControllerMappingBO);

                ApiBO apiBO = ApiBO.builder()
                        .className(restControllerMappingBO.getClassName())
                        .methodName(restControllerMappingBO.getMethodName())
                        .mapping(restControllerMappingBO.getMapping())
                        .summary(restControllerMappingBO.getSummary())
                        .build();
                apiBOList.add(apiBO);
            }
            String encryptString = String.valueOf(apiBOList.size());
            String encrypt = AesUtil.encryptString(encryptString, aesProperties.getAesKey());
            apiFeignClient.feignSaveList(ApiDTO.toDTO(apiBOList), encrypt);
        }
        XxlJobHelper.log("<<<<<<<<<<<<======== ApiSaveJobHandler 结束 ========>>>>>>>>>>>>");
    }
}
