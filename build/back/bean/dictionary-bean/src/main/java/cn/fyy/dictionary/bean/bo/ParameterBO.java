package cn.fyy.dictionary.bean.bo;

import cn.fyy.dictionary.bean.po.ParameterPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParameterBO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 参数代码
     */
    private String parameterCode;

    /**
     * 参数名称
     */
    private String parameterName;

    /**
     * 参数值
     */
    private String parameterValue;

    /**
     * 参数说明
     */
    private String parameterExplain;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
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
     * 更新人ID
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
     * 参数 PO 转换 BO
     */
    public static ParameterBO toBO(ParameterPO po) {
        if (po != null) {
            return ParameterBO.builder()
                    .id(po.getId())
                    .parameterCode(po.getParameterCode())
                    .parameterName(po.getParameterName())
                    .parameterValue(po.getParameterValue())
                    .parameterExplain(po.getParameterExplain())
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
     * 参数 PO 数组 转换 BO List
     */
    public static List<ParameterBO> toBO(ParameterPO[] pos) {
        if (pos != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 PO List 转换 BO List
     */
    public static List<ParameterBO> toBO(List<ParameterPO> pos) {
        if (pos != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO 转换 PO
     */
    public static ParameterPO toPO(ParameterBO bo) {
        if (bo != null) {
            return ParameterPO.builder()
                    .id(bo.getId())
                    .parameterCode(bo.getParameterCode())
                    .parameterName(bo.getParameterName())
                    .parameterValue(bo.getParameterValue())
                    .parameterExplain(bo.getParameterExplain())
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
     * 参数 BO List 转换 PO List
     */
    public static List<ParameterPO> toPO(List<ParameterBO> bos) {
        if (bos != null) {
            List<ParameterPO> pos = new ArrayList<>();
            for (ParameterBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO 转换 PO
     */
    public ParameterPO toPO() {
        return ParameterPO.builder()
                .id(this.getId())
                .parameterCode(this.getParameterCode())
                .parameterName(this.getParameterName())
                .parameterValue(this.getParameterValue())
                .parameterExplain(this.getParameterExplain())
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
