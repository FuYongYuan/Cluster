package cn.fyy.message.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import cn.fyy.message.bean.dto.NoticeDTO;
import cn.fyy.message.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 公告 RestController
 *
 * @author fuyy
 */
@Tag(name = "NoticeRestController", description = "公告 RestController")
@RestController
@RequestMapping(value = "/notice", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticeRestController {
    /**
     * 公告 Service
     */
    @Resource
    private NoticeService noticeServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键删除 主键可以是多个用,分割", description = "受影响行数",
            parameters = {
                    @Parameter(name = "ids", description = "删除ID", required = true)
            }
    )
    @DeleteMapping(value = "/delete/{ids}")
    public ResultMessage<Integer> delete(
            HttpServletRequest request,
            @PathVariable("ids") String ids
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        int i = noticeServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
        if (i > 0) {
            return new ResultMessage<>(i);
        } else {
            return new ResultMessage<>(1, "删除失败!");
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 公告对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "NoticeDTO 公告对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<NoticeDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(NoticeDTO.toDTO(noticeServiceImpl.getById(id)));
    }

    /**
     * 保存公告
     *
     * @param dto 公告对象
     * @return 公告对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存公告", description = "NoticeDTO 公告对象",
            parameters = {
                    @Parameter(name = "dto", description = "公告对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody NoticeDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return noticeServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

}
