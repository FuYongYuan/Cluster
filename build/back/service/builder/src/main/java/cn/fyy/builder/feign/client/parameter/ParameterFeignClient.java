package cn.fyy.builder.feign.client.parameter;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.dictionary.bean.dto.ParameterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * 参数 FeignClient调用
 *
 * @author fuyy
 */
@FeignClient(name = "dictionary", contextId = "Parameter", path = "/parameter")
public interface ParameterFeignClient {
    /**
     * 根据参数编码、参数名称查询参数列表
     *
     * @param currentPage   当前页
     * @param eachPageSize  每页多少条
     * @param parameterCode 参数编码
     * @param parameterName 参数名称
     * @param state         状态
     * @return TParameterDTO 参数对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    ResultMessage<PageImpl<ParameterDTO>> queryByRoleNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "parameterCode", required = false) String parameterCode,
            @RequestParam(value = "parameterName", required = false) String parameterName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @DeleteMapping(value = "/delete/{ids}")
    ResultMessage<Integer> delete(
            @PathVariable("ids") String ids
    ) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/{id}")
    ResultMessage<ParameterDTO> getById(
            @PathVariable("id") BigInteger id
    ) throws BusinessException;

    /**
     * 根据参数编号查询
     *
     * @param parameterCode 参数编号
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/parameter/code/{parameterCode}")
    ResultMessage<ParameterDTO> getByParameterCode(
            @PathVariable("parameterCode") String parameterCode
    ) throws BusinessException;

    /**
     * 保存参数
     *
     * @param dto 参数对象
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody ParameterDTO dto
    ) throws BusinessException;
}
