package cn.fyy.common.controller;

import cn.fyy.common.bean.bo.RestControllerMappingBO;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * 获取所有 RESTful 控制器的 URL
 *
 * @author fyy
 */
@Slf4j
@Component
public class RestControllerUrlCollector implements ApplicationRunner {
    /**
     * 获取所有 RESTful 控制器的 URL
     */
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * Redis 操作
     */
    @Resource
    private RedisService redisServiceImpl;

    /**
     * 应用名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        log.info(">>>>>>>>>>>>======== 开始获取 RESTful 控制器 URL ========<<<<<<<<<<<<");
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();

            // 获取方法上的 @Operation 注解
            Operation operation = method.getMethodAnnotation(Operation.class);
            String summary = (operation != null) ? operation.summary() : "未知";

            info.getPatternValues().forEach(pattern -> {
                // org.springframework.boot 去除 Spring 自带
                if (!method.getBeanType().getName().startsWith("org.springframework.boot")) {
                    RestControllerMappingBO bo = RestControllerMappingBO.builder()
                            .className(method.getBeanType().getName())
                            .methodName(method.getMethod().getName())
                            .mapping(pattern)
                            .summary(summary)
                            .build();

                    redisServiceImpl.set(RedisSelect.THIRTEEN, "/" + applicationName + bo.getMapping(), bo);

                    log.info("功能清单已收集 {}", bo);
                }
            });

        }
        log.info(">>>>>>>>>>>>======== 结束获取 RESTful 控制器 URL ========<<<<<<<<<<<<");
    }


}
