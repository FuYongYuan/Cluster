package cn.fyy.member.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import cn.fyy.member.bean.bo.ManagerBO;
import cn.fyy.member.bean.dto.ManagerDTO;
import cn.fyy.member.bean.dto.ManagerInternalDTO;
import cn.fyy.member.service.ManagerService;
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
 * 管理员 RestController
 *
 * @author fuyy
 */
@Tag(name = "ManagerRestController", description = "管理员 RestController")
@RestController
@RequestMapping(value = "/manager", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerRestController {
    /**
     * 管理员 Service
     */
    @Resource
    private ManagerService managerServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据账户、手机、邮箱、管理员名称查询管理员列表
     *
     * @param account     账号
     * @param mobile      手机
     * @param mail        电子邮箱
     * @param managerName 管理员名称
     * @return ManagerDTO 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据账户、手机、邮箱、管理员名称查询管理员列表", description = "ManagerDTO 管理员对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "pageSort", description = "排序"),
                    @Parameter(name = "account", description = "账号"),
                    @Parameter(name = "mobile", description = "手机"),
                    @Parameter(name = "mail", description = "电子邮箱"),
                    @Parameter(name = "managerName", description = "管理员名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    public ResultMessage<PageImpl<ManagerDTO>> queryByAccountAndMobileAndEmailAndUserNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "pageSort", required = false) String pageSort,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "mail", required = false) String mail,
            @RequestParam(value = "managerName", required = false) String managerName,
            @RequestParam(value = "state", required = false) Byte state
    ) throws BusinessException {
        Page<ManagerBO> boPage = managerServiceImpl.queryByAccountAndMobileAndEmailAndUserNameAndState(
                currentPage,
                eachPageSize,
                pageSort,
                account,
                mobile,
                mail,
                managerName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(ManagerDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
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
        int i = managerServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
        if (i > 0) {
            return new ResultMessage<>(i);
        } else {
            return new ResultMessage<>(1, "删除失败!");
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<ManagerDTO> getById(
            @PathVariable("id") Long id
    ) throws BusinessException {
        return new ResultMessage<>(ManagerDTO.toDTO(managerServiceImpl.getById(id)));
    }

    /**
     * 根据JwtToken查询
     *
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据JwtToken查询", description = "ManagerDTO 管理员对象"
    )
    @GetMapping(value = "/get/jwt/token")
    public ResultMessage<ManagerDTO> getByJwtToken(
            HttpServletRequest request
    ) throws BusinessException {
        return new ResultMessage<>(ManagerDTO.toDTO(managerServiceImpl.getByJwtToken(
                jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request))
        )));
    }


    /**
     * 保存管理员
     *
     * @param dto 管理员对象
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存管理员", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "dto", description = "管理员对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            HttpServletRequest request,
            @RequestBody ManagerDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        return managerServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 保存管理员
     *
     * @param dto            管理员对象
     * @param authentication 鉴权串
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存管理员", description = "ManagerDTO 管理员对象",
            parameters = {
                    @Parameter(name = "dto", description = "管理员对象", required = true),
                    @Parameter(name = "authentication", description = "鉴权串", required = true)
            }
    )
    @PostMapping(value = "/save/return/dto/{authentication}")
    public ResultMessage<Long> saveReturnDTO(
            @RequestBody ManagerInternalDTO dto,
            @PathVariable(value = "authentication") String authentication
    ) throws BusinessException {
        return managerServiceImpl.saveReturnDTO(dto, authentication);
    }

    /**
     * 根据id保存状态
     *
     * @param ids   需要修改的管理员ID
     * @param state 状态
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据id保存状态", description = "受影响行数",
            parameters = {
                    @Parameter(name = "ids", description = "需要修改的管理员ID", required = true),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @PutMapping(value = "/update/state/{ids}/{state}")
    public ResultMessage<Integer> updateStateById(
            HttpServletRequest request,
            @PathVariable("ids") String ids,
            @PathVariable(value = "state") Byte state
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
        int i = managerServiceImpl.updateStateByIds(ids, state, managerMessage.getManagerId(), managerMessage.getManagerName());
        if (i > 0) {
            return new ResultMessage<>(i);
        } else {
            return new ResultMessage<>(1, "修改失败!");
        }
    }

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据账号查询", description = "是否存在",
            parameters = {
                    @Parameter(name = "account", description = "账号", required = true)
            }
    )
    @GetMapping(value = "/get/account/exist/{account}")
    public ResultMessage<Boolean> getByAccountIsExist(
            @PathVariable("account") String account
    ) throws BusinessException {
        return new ResultMessage<>(managerServiceImpl.getByAccountIsExist(account));
    }

    /**
     * 根据邮箱查询
     *
     * @param mail 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据邮箱查询", description = "是否存在",
            parameters = {
                    @Parameter(name = "mail", description = "邮箱", required = true)
            }
    )
    @GetMapping(value = "/get/mail/exist/{mail}")
    public ResultMessage<Boolean> getByMailIsExist(
            @PathVariable("mail") String mail
    ) throws BusinessException {
        return new ResultMessage<>(managerServiceImpl.getByMailIsExist(mail));
    }

    /**
     * 根据账号查询尝试登录次数
     *
     * @param account 账号
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据账号查询尝试登录次数", description = "尝试登陆次数",
            parameters = {
                    @Parameter(name = "account", description = "账号", required = true)
            }
    )
    @GetMapping(value = "/get/attempt/login/number/{account}")
    public ResultMessage<Integer> getAttemptLoginNumberByAccount(
            @PathVariable("account") String account
    ) throws BusinessException {
        return new ResultMessage<>(managerServiceImpl.getAttemptLoginNumberByAccount(account));
    }

    /**
     * 根据邮箱保存密码
     *
     * @param mail           邮箱
     * @param loginPassword  密码
     * @param authentication 鉴权串
     * @return 受影响行数
     * @throws BusinessException 错误
     */
    @Operation(
            summary = "根据账号查询尝试登录次数", description = "尝试登陆次数",
            parameters = {
                    @Parameter(name = "mail", description = "邮箱", required = true),
                    @Parameter(name = "loginPassword", description = "密码", required = true),
                    @Parameter(name = "authentication", description = "验证码", required = true)
            }
    )
    @PutMapping(value = "/update/login/password/{mail}/{loginPassword}/{authentication}")
    public ResultMessage<Integer> updateLoginPasswordByMail(
            @PathVariable("mail") String mail,
            @PathVariable("loginPassword") String loginPassword,
            @PathVariable("authentication") String authentication
    ) throws BusinessException {
        return managerServiceImpl.updateLoginPasswordByMail(mail, loginPassword, authentication);
    }

    /**
     * 根据账号密码查询
     *
     * @param account       账号
     * @param loginPassword 密码
     * @return 管理员对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据账号密码查询", description = "管理员对象",
            parameters = {
                    @Parameter(name = "account", description = "账号", required = true),
                    @Parameter(name = "loginPassword", description = "密码", required = true)
            }
    )
    @GetMapping(value = "/get/account/password/{account}/{loginPassword}")
    public ResultMessage<ManagerInternalDTO> getByAccountAndLoginPassword(
            @PathVariable("account") String account,
            @PathVariable("loginPassword") String loginPassword
    ) throws BusinessException {
        return new ResultMessage<>(ManagerInternalDTO.toDTO(managerServiceImpl.getByAccountAndLoginPassword(account, loginPassword)));
    }
}
