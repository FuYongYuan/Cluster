package cn.fyy.jwt.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限
 *
 * @author fyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SecurityAuthority {
    /**
     * 权限类型
     */
    private String type;
    /**
     * 权限id
     */
    private Long id;
    /**
     * 权限值
     */
    private String value;

    /**
     * 转为json
     *
     * @return json
     */
    public String toJson() {
        return "{\"type\":\"" + type + "\",\"id\":\"" + id + "\",\"value\":\"" + value + "\"}";
    }
}
