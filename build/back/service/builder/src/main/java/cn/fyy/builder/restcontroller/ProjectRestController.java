package cn.fyy.builder.restcontroller;

import cn.fyy.builder.bean.bo.ProjectBO;
import cn.fyy.builder.bean.dto.ProjectDTO;
import cn.fyy.builder.service.ProjectService;
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

/**
 * 项目 RestController
 *
 * @author fuyy
 */
@Tag(name = "ProjectRestController", description = "项目 RestController")
@RestController
@RequestMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectRestController {
    /**
     * 项目群 Service
     */
    @Resource
    private ProjectService projectServiceImpl;

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
        int i = projectServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
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
            summary = "根据主键查询", description = "TProjectDTO 项目群对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<ProjectDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(ProjectDTO.toDTO(projectServiceImpl.getById(id)));
    }

    /**
     * 保存项目群
     *
     * @param dto 项目群对象
     * @return 项目群对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存项目群", description = "TProjectDTO 项目群对象",
            parameters = {
                    @Parameter(name = "dto", description = "项目群对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody ProjectDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return projectServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }


    /**
     * 根据名称查询项目群列表
     *
     * @param projectName 项目名称
     * @return TProjectDTO 项目对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据名称查询项目列表", description = "TProjectDTO 项目对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "projectGroupId", description = "项目群ID", required = true),
                    @Parameter(name = "projectName", description = "项目名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}/{projectGroupID}")
    public ResultMessage<PageImpl<ProjectDTO>> queryByManagerIdAndProjectGroupNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @PathVariable(value = "projectGroupID") Long projectGroupId,
            @RequestParam(value = "projectName", required = false) String projectName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        Page<ProjectBO> boPage = projectServiceImpl.queryByProjectGroupIdAndProjectNameAndState(
                currentPage,
                eachPageSize,
                projectGroupId,
                projectName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(ProjectDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
    }
}
