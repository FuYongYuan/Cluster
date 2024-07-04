package cn.fyy.authorization.feign.client.capability;

import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * 菜单 RestController
 *
 * @author fuyy
 */
@FeignClient(name = "capability", contextId = "Menu", path = "/menu")
public interface MenuFeignClient {

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据名称查询菜单列表
     *
     * @param menuName     菜单名称
     * @param currentPage  当前页
     * @param eachPageSize 每页显示条数
     * @param state        状态
     * @return TMenuDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    ResultMessage<PageImpl<MenuDTO>> queryByMenuNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "menuName", required = false) String menuName,
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
     * @return 菜单对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/get/{id}")
    ResultMessage<MenuDTO> getById(
            @PathVariable("id") BigInteger id
    ) throws BusinessException;

    /**
     * 保存菜单
     *
     * @param dto 菜单对象
     * @return 菜单对象
     * @throws BusinessException 业务错误
     */
    @PostMapping(value = "/save")
    ResultMessage<String> save(
            @RequestBody MenuDTO dto
    ) throws BusinessException;

    /**
     * 查询菜单列表
     *
     * @return TMenuDTO 菜单对象
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/all")
    ResultMessage<List<MenuDTO>> queryAll(
    ) throws BusinessException;

    /**
     * 根据菜单ID集合查询菜单列表
     *
     * @param menuIdList 菜单ID集合
     * @return TMenuDTO 菜单对象集合
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/hierarchy/menu/list/{menuIdList}")
    ResultMessage<List<MenuDTO>> queryHierarchyMenuByMenuIdList(
            @PathVariable(value = "menuIdList") List<BigInteger> menuIdList
    ) throws BusinessException;

    /**
     * 根据菜单ID集合查询菜单列表
     *
     * @param menuIdList 菜单ID集合
     * @return TMenuDTO 菜单对象集合
     * @throws BusinessException 业务错误
     */
    @GetMapping(value = "/query/menu/list/{menuIdList}")
    ResultMessage<List<MenuDTO>> queryMenuByMenuIdList(
            @PathVariable(value = "menuIdList") List<BigInteger> menuIdList
    ) throws BusinessException;
}
