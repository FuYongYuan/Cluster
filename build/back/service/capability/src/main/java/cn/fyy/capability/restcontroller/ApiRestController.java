package cn.fyy.capability.restcontroller;

import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.capability.service.ApiService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.controller.BaseRestController;
import cn.fyy.jwt.bean.bo.ManagerMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Api 信息 RestController
 *
 * @author fyy
 */
@Tag(name = "ApiRestController", description = "Api 信息 RestController")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRestController extends BaseRestController {
    /**
     * Api 信息 Service
     */
    @Resource
    private ApiService apiServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存 Api 信息
     *
     * @param dto Api 信息对象
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存 Api 信息", description = "ApiDTO Api 信息对象",
            parameters = {
                    @Parameter(name = "dto", description = "Api 信息对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody ApiDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return apiServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存 Api 信息集合
     *
     * @param list           Api 信息对象集合
     * @param authentication 鉴权串
     * @return Api 信息对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存 Api 信息集合", description = "ApiDTO Api 信息对象",
            parameters = {
                    @Parameter(name = "dto", description = "Api 信息对象集合", required = true),
                    @Parameter(name = "authentication", description = "验证码", required = true)
            }
    )
    @PostMapping(value = "/feign/save/list/{authentication}")
    public ResultMessage<String> feignSaveList(
            @RequestBody List<ApiDTO> list,
            @PathVariable String authentication
    ) throws BusinessException {
        return apiServiceImpl.saveList(ApiDTO.toBO(list), authentication);
    }
}
