package cn.fyy.authorization.restcontroller;

import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.dto.ResultMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/")
    public ResultMessage<String> testGet(
    ) {
        return new ResultMessage<>(OperateResult.SUCCESS);
    }
}
