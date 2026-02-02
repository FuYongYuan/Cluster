package cn.fyy.jwt.bean.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     * 转为 JSON
     *
     * @return json
     */
    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("未能将 SecurityAuthority 转换为 JSON", e);
        }
    }

    /**
     * 使用 toJson() 输出的 JSON 字符串初始化回对象
     */
    public SecurityAuthority(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SecurityAuthority authority = mapper.readValue(json, SecurityAuthority.class);
            this.type = authority.getType();
            this.id = authority.getId();
            this.value = authority.getValue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("未能将 JSON 字符串解析为 SecurityAuthority", e);
        }
    }

}
