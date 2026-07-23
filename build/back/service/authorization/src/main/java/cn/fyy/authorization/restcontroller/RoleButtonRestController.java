package cn.fyy.authorization.restcontroller;

import cn.fyy.authorization.bean.dto.RoleButtonDTO;
import cn.fyy.authorization.service.RoleButtonService;
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

/**
 * 按钮角色关系 RestController
 *
 * @author fyy
 */
@Tag(name = "RoleButtonRestController", description = "按钮角色关系 RestController")
@RestController
@RequestMapping(value = "/role/button", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleButtonRestController extends BaseRestController {
    /**
     * 按钮角色关系 Service
     */
    @Resource
    private RoleButtonService roleButtonServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存按钮角色关系
     *
     * @param dto 按钮角色关系对象
     * @return 按钮角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存按钮角色关系", description = "RoleButtonDTO 按钮角色关系对象",
            parameters = {
                    @Parameter(name = "dto", description = "按钮角色关系对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody RoleButtonDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleButtonServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存按钮角色关系集合
     *
     * @param roleId    角色 ID
     * @param buttonIds 按钮 ID 集合
     * @return 按钮角色关系对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存按钮角色关系集合", description = "RoleButtonDTO 按钮角色关系对象",
            parameters = {
                    @Parameter(name = "roleId", description = "角色 ID", required = true),
                    @Parameter(name = "buttonIds", description = "按钮 ID 集合", required = true)
            }
    )
    @PostMapping(value = "/save/list/{roleId}/{buttonIds}")
    public ResultMessage<String> saveList(
            HttpServletRequest request,
            @PathVariable Long roleId,
            @PathVariable String buttonIds
    ) throws BusinessException {
        ManagerMessage managerMessage = super.getLoginManagerMessage(request);
        return roleButtonServiceImpl.saveList(roleId, buttonIds, managerMessage.getManagerId(), managerMessage.getManagerName());
    }

}
