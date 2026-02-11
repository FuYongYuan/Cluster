package cn.fyy.capability.bean.bo;

import cn.fyy.capability.bean.po.ApiPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * API信息 BO
 *
 * @author fyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiBO implements Serializable {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * Mapping值
     */
    private String mapping;

    /**
     * Swagger说明
     */
    private String summary;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人 ID
     */
    private Long creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人 ID
     */
    private Long updaterId;

    /**
     * 更新人名称
     */
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * API PO 转换 BO
     */
    public static ApiBO toBO(ApiPO po) {
        if (po != null) {
            return ApiBO.builder()
                    .id(po.getId())
                    .className(po.getClassName())
                    .methodName(po.getMethodName())
                    .mapping(po.getMapping())
                    .summary(po.getSummary())
                    .createTime(po.getCreateTime())
                    .creatorId(po.getCreatorId())
                    .creatorName(po.getCreatorName())
                    .updateTime(po.getUpdateTime())
                    .updaterId(po.getUpdaterId())
                    .updaterName(po.getUpdaterName())
                    .state(po.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * API PO 数组 转换 BO List
     */
    public static List<ApiBO> toBO(ApiPO[] pos) {
        if (pos != null) {
            List<ApiBO> bos = new ArrayList<>();
            for (ApiPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * API PO List 转换 BO List
     */
    public static List<ApiBO> toBO(List<ApiPO> pos) {
        if (pos != null) {
            List<ApiBO> bos = new ArrayList<>();
            for (ApiPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * API BO 转换 PO
     */
    public static ApiPO toPO(ApiBO bo) {
        if (bo != null) {
            return ApiPO.builder()
                    .id(bo.getId())
                    .className(bo.getClassName())
                    .methodName(bo.getMethodName())
                    .mapping(bo.getMapping())
                    .summary(bo.getSummary())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * API BO List 转换 PO List
     */
    public static List<ApiPO> toPO(List<ApiBO> bos) {
        if (bos != null) {
            List<ApiPO> pos = new ArrayList<>();
            for (ApiBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * API BO 转换 PO
     */
    public ApiPO toPO() {
        return ApiPO.builder()
                .id(this.getId())
                .className(this.getClassName())
                .methodName(this.getMethodName())
                .mapping(this.getMapping())
                .summary(this.getSummary())
                .createTime(this.getCreateTime())
                .creatorId(this.getCreatorId())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterId(this.getUpdaterId())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .build();
    }
}
