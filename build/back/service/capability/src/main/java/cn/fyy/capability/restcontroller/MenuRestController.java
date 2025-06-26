package cn.fyy.capability.restcontroller;

import cn.fyy.capability.bean.bo.MenuBO;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.capability.service.MenuService;
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
 * 菜单 RestController
 *
 * @author fuyy
 */
@Tag(name = "MenuRestController", description = "菜单 RestController")
@RestController
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    /**
     * 菜单 Service
     */
    @Resource
    private MenuService menuServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据名称查询菜单列表
     *
     * @param menuName 菜单名称
     * @return TMenuDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据名称查询菜单列表", description = "TMenuDTO 菜单对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "pageSort", description = "排序"),
                    @Parameter(name = "menuName", description = "菜单名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    public ResultMessage<PageImpl<MenuDTO>> queryByMenuNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "pageSort", required = false) String pageSort,
            @RequestParam(value = "menuName", required = false) String menuName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        Page<MenuBO> boPage = menuServiceImpl.queryByMenuNameAndState(
                currentPage,
                eachPageSize,
                pageSort,
                menuName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(MenuDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
    }

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
        int i = menuServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
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
     * @return 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TMenuDTO 菜单对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<MenuDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(MenuDTO.toDTO(menuServiceImpl.getById(id)));
    }

    /**
     * 保存菜单
     *
     * @param dto 菜单对象
     * @return 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存菜单", description = "TMenuDTO 菜单对象",
            parameters = {
                    @Parameter(name = "dto", description = "菜单对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody MenuDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return menuServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 查询菜单列表
     *
     * @return TMenuDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "查询菜单列表", description = "TMenuDTO 菜单对象集合"
    )
    @GetMapping(value = "/query/all")
    public ResultMessage<List<MenuDTO>> queryAll(
    ) throws BusinessException {
        return new ResultMessage<>(MenuDTO.toDTO(menuServiceImpl.queryAll()));
    }

    /**
     * 查询父级菜单列表
     *
     * @return TMenuDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "查询父级菜单列表", description = "TMenuDTO 菜单对象集合"
    )
    @GetMapping(value = "/query/all/parent")
    public ResultMessage<List<MenuDTO>> queryAllParent(
    ) throws BusinessException {
        return new ResultMessage<>(MenuDTO.toDTO(menuServiceImpl.queryAllParent()));
    }

    /**
     * 根据菜单ID集合查询菜单列表
     *
     * @param menuIdList 菜单ID集合
     * @return TMenuDTO 菜单对象集合
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据菜单ID集合查询菜单列表", description = "TMenuDTO 菜单对象集合",
            parameters = {
                    @Parameter(name = "menuIdList", description = "菜单ID集合", required = true)
            }
    )
    @GetMapping(value = "/query/hierarchy/menu/list/{menuIdList}")
    public ResultMessage<List<MenuDTO>> queryHierarchyMenuByMenuIdList(
            @PathVariable(value = "menuIdList") List<Long> menuIdList
    ) throws BusinessException {
        return new ResultMessage<>(MenuDTO.toDTO(menuServiceImpl.queryHierarchyMenuByMenuIdList(menuIdList)));
    }

    /**
     * 根据菜单ID集合查询菜单列表
     *
     * @param menuIdList 菜单ID集合
     * @return TMenuDTO 菜单对象集合
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据菜单ID集合查询菜单列表", description = "TMenuDTO 菜单对象集合",
            parameters = {
                    @Parameter(name = "menuIdList", description = "菜单ID集合", required = true)
            }
    )
    @GetMapping(value = "/query/menu/list/{menuIdList}")
    public ResultMessage<List<MenuDTO>> queryMenuByMenuIdList(
            @PathVariable(value = "menuIdList") List<Long> menuIdList
    ) throws BusinessException {
        return new ResultMessage<>(MenuDTO.toDTO(menuServiceImpl.queryMenuByMenuIdList(menuIdList)));
    }
}
