package cn.fyy.common.controller;

import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.bean.bo.ManagerMessage;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RESTful 控制器基类
 *
 * @author fyy
 */
public abstract class BaseRestController {

    /**
     * JWT 工具类
     */
    @Resource
    protected JwtTokenWebService jwtTokenWebService;

    /**
     * 从请求中获取管理员信息
     *
     * @param request HTTP请求
     * @return 管理员信息
     */
    protected ManagerMessage getLoginManagerMessage(
            HttpServletRequest request
    ) {
        return jwtTokenWebService.getManagerMessageFromToken(jwtTokenWebService.getTokenFromRequest(request));
    }

    /**
     * 从请求中获取管理员ID
     *
     * @param request HTTP请求
     * @return 管理员ID
     */
    protected Long getLoginManagerId(HttpServletRequest request) {
        return jwtTokenWebService.getManagerIdFromToken(jwtTokenWebService.getTokenFromRequest(request));
    }

    /**
     * 从请求中获取管理员名称
     *
     * @param request HTTP请求
     * @return 管理员名称
     */
    protected String getLoginManagerName(HttpServletRequest request) {
        return jwtTokenWebService.getManagerNameFromToken(jwtTokenWebService.getTokenFromRequest(request));
    }
}
