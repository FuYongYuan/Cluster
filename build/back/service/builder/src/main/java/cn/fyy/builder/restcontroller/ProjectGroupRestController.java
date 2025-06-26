package cn.fyy.builder.restcontroller;

import cn.fyy.builder.bean.bo.ProjectGroupBO;
import cn.fyy.builder.bean.dto.ProjectGroupDTO;
import cn.fyy.builder.service.ProjectGroupService;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目群 RestController
 *
 * @author fuyy
 */
@Tag(name = "ProjectGroupRestController", description = "项目群 RestController")
@RestController
@RequestMapping(value = "/project_group", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectGroupRestController {
    /**
     * 项目群 Service
     */
    @Resource
    private ProjectGroupService projectGroupServiceImpl;

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
        int i = projectGroupServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
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
     * @return 项目群对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TProjectGroupDTO 项目群对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<ProjectGroupDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(ProjectGroupDTO.toDTO(projectGroupServiceImpl.getById(id)));
    }

    /**
     * 保存项目群
     *
     * @param dto 项目群对象
     * @return 项目群对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存项目群", description = "TProjectGroupDTO 项目群对象",
            parameters = {
                    @Parameter(name = "dto", description = "项目群对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody ProjectGroupDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        dto.setManagerId(managerMessage.getManagerId());
        return projectGroupServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 根据名称查询项目群列表
     *
     * @param projectGroupName 项目群名称
     * @return TProjectGroupDTO 项目群对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据名称查询项目群列表", description = "TProjectGroupDTO 项目群对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "projectGroupName", description = "项目群名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    public ResultMessage<PageImpl<ProjectGroupDTO>> queryByManagerIdAndProjectGroupNameAndState(
            HttpServletRequest request,
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "projectGroupName", required = false) String projectGroupName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        Page<ProjectGroupBO> boPage = projectGroupServiceImpl.queryByManagerIdAndProjectGroupNameAndState(
                currentPage,
                eachPageSize,
                jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)),
                projectGroupName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(ProjectGroupDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
    }

    /**
     * 根据当前登陆人主键ID查询
     *
     * @return 项目群对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据当前登陆人主键ID查询", description = "TProjectGroupDTO 项目群对象"
    )
    @GetMapping(value = "/get/login")
    public ResultMessage<List<ProjectGroupDTO>> getByManagerId(
            HttpServletRequest request
    ) throws BusinessException {
        return new ResultMessage<>(ProjectGroupDTO.toDTO(projectGroupServiceImpl.queryByManagerId(jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request)))));
    }

//    /**
//     * 根据主键ID生成项目群压缩包
//     * @throws BusinessException 业务错误
//     */
//    @ApiOperation(value = "生成项目群压缩包", notes = "文件流下载")
//    @PostMapping(value = "/generate/{id}")
//    public void generateById(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @ApiParam(value = "主键ID", required = true) @PathVariable("id") Long id
//    ) throws Exception {
//        SecurityUser securityUser = SecurityMessage.getPrincipal();
//        // 生成下载压缩文件
//        projectGroupServiceImpl.generateById(request, response, id, securityUser.getManagerId());
//    }
}
