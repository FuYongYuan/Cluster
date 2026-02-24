package cn.fyy.job.config.feign.client.capability;

import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Api 信息 RestController
 *
 * @author fyy
 */
@FeignClient(name = "capability", contextId = "Api", path = "/api")
public interface ApiFeignClient {
    /**
     * 保存 Api 信息
     *
     * @param dto Api 信息对象
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody ApiDTO dto
    ) throws BusinessException;

    /**
     * 保存 Api 信息集合
     *
     * @param list Api 信息对象集合
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save/list")
    ResultMessage<String> save(
            @RequestBody List<ApiDTO> list
    ) throws BusinessException;

    /**
     * 保存 Api 信息集合
     *
     * @param list           Api 信息对象集合
     * @param authentication 鉴权串
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/feign/save/list/{authentication}")
    ResultMessage<String> feignSaveList(
            @RequestBody List<ApiDTO> list,
            @PathVariable String authentication
    ) throws BusinessException;
}
