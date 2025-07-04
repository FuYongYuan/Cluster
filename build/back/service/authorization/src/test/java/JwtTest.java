import cn.fyy.authorization.AuthorizationApplication;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import cn.fyy.jwt.service.JwtTokenService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthorizationApplication.class)
public class JwtTest {
    @Resource
    private JwtTokenService jwtTokenService;

    @Test
    public void testGenerateToken() {
        var authorities = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        SecurityUser securityUser = new SecurityUser(
                1L,
                "FYY",
                "fyy",
                "fyyain77",
                AuthorityUtils.createAuthorityList(authorities)
        );
        System.out.println(securityUser);
        System.out.println(
                jwtTokenService.getAccessToken(
                        "1",
                        securityUser
                )
        );
    }
}
