package cn.fyy.authorization.restcontroller;

import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.dto.ResultMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Tag(name = "TestRestController", description = "测试 RestController")
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestRestController {
    /**
     * 测试
     *
     * @return 测试
     */
    @Operation(
            summary = "测试", description = "测试内容"
    )
    @GetMapping(value = "/1")
    public ResultMessage<String> testGet() {
        try {
            log.info(InetAddress.getLocalHost().getHostAddress() + " IP被访问");
        } catch (UnknownHostException e) {
            log.error("错误未获取IP");
        }
        return new ResultMessage<>(OperateResult.SUCCESS);
    }
}
