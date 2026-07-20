package cn.fyy.jwt.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

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
     * JSON 序列化工具
     */
    private static final JsonMapper JSON_MAPPER = JsonMapper.builder().build();

    /**
     * 转为 JSON
     *
     * @return json
     */
    public String toJson() {
        return JSON_MAPPER.writeValueAsString(this);
    }

    /**
     * 使用 toJson() 输出的 JSON 字符串初始化回对象
     */
    public SecurityAuthority(String json) {
        SecurityAuthority authority = JSON_MAPPER.readValue(json, SecurityAuthority.class);
        this.type = authority.getType();
        this.id = authority.getId();
        this.value = authority.getValue();
    }

}
