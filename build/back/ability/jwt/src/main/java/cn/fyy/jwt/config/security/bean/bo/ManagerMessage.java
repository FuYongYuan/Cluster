package cn.fyy.jwt.config.security.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 管理员信息 - 项目中使用
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerMessage {
    /**
     * 管理员ID
     */
    private BigInteger managerId;

    /**
     * 管理员名称
     */
    private String managerName;
}
