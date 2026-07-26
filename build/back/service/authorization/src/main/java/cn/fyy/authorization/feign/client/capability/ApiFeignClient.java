package cn.fyy.authorization.feign.client.capability;

import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 菜单 RestController
 *
 * @author fyy
 */
@FeignClient(name = "capability", contextId = "Api", path = "/api")
public interface ApiFeignClient {

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存 Api 信息
     *
     * @param dto Api 信息对象
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody ApiDTO dto
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

    /**
     * 查询全部 Api 信息
     *
     * @return Api 信息对象集合
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/all")
    ResultMessage<List<ApiDTO>> queryAll(
    ) throws BusinessException;

    /**
     * 根据API信息 ID 集合查询菜单列表
     *
     * @param apiIdList      API信息 ID 集合
     * @param authentication 鉴权串
     * @return ApiDTO API信息对象集合
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/feign/query/api/list/{apiIdList}/{authentication}")
    ResultMessage<List<ApiDTO>> feignQueryApiByApiIdList(
            @PathVariable List<Long> apiIdList,
            @PathVariable String authentication
    ) throws BusinessException;
}
