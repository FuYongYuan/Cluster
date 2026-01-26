package cn.fyy.common.controller;

import cn.fyy.common.config.security.service.JwtTokenWebService;
import jakarta.annotation.Resource;

/**
 * RESTful 控制器基类
 * @author fyy
 */
public abstract class BaseRestController {

    /**
     * JWT 工具类
     */
    @Resource
    protected JwtTokenWebService jwtTokenWebService;
}
