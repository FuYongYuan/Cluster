package cn.fyy.common.config.swagger2;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Restful API 访问路径:
 * IP:port/{context-path}/swagger-ui/index.html
 * 如:localhost:8080/XXX/swagger-ui/index.html
 *
 * @author fuyy
 */
@Configuration
public class SwaggerConfig {
    /**
     * 获取项目名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    // 创建扫描内容和分组情况

    @Bean
    public OpenAPI restfulOpenApi() {
        return new OpenAPI()
                .info(
                        new Info().title(applicationName)
                                .description("API接口说明文档（基于RESTful协议）")
                                .version("1.0.0")
                                .license(new License().name("哎呋歪歪").url("https://www.aifuwaiwai.com"))
                                .contact(new Contact().name("FYY"))
                )
                .security(securityRequirements())
                .schemaRequirement("JWT", securityScheme())
                ;
    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用Token认证 即在Header中加入 Authorization: Bearer Header-头部.Payload-负载.Signature-签名
     */
    private List<SecurityRequirement> securityRequirements() {
        List<SecurityRequirement> list = new ArrayList<>();
        SecurityRequirement sr = new SecurityRequirement();
        sr.addList("JWT");
        list.add(sr);
        return list;
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("JWT")
                .scheme("Bearer")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER);
    }

}