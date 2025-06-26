package cn.fyy.dictionary.bean.bo;

import cn.fyy.dictionary.bean.dbo.ParameterDO;
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
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    private Long updaterId;

    /**
     * 修改人名称
     */
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 参数 DO 转换 BO
     */
    public static ParameterBO toBO(ParameterDO dbo) {
        if (dbo != null) {
            return ParameterBO.builder()
                    .id(dbo.getId())
                    .parameterCode(dbo.getParameterCode())
                    .parameterName(dbo.getParameterName())
                    .parameterValue(dbo.getParameterValue())
                    .parameterExplain(dbo.getParameterExplain())
                    .createTime(dbo.getCreateTime())
                    .creatorId(dbo.getCreatorId())
                    .creatorName(dbo.getCreatorName())
                    .updateTime(dbo.getUpdateTime())
                    .updaterId(dbo.getUpdaterId())
                    .updaterName(dbo.getUpdaterName())
                    .state(dbo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 参数 DO 数组 转换 BO List
     */
    public static List<ParameterBO> toBO(ParameterDO[] dbos) {
        if (dbos != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 DO List 转换 BO List
     */
    public static List<ParameterBO> toBO(List<ParameterDO> dbos) {
        if (dbos != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO 转换 DO
     */
    public static ParameterDO toDO(ParameterBO bo) {
        if (bo != null) {
            return ParameterDO.builder()
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
     * 参数 BO List 转换 DO List
     */
    public static List<ParameterDO> toDO(List<ParameterBO> bos) {
        if (bos != null) {
            List<ParameterDO> dbos = new ArrayList<>();
            for (ParameterBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO 转换 DO
     */
    public ParameterDO toDO() {
        return ParameterDO.builder()
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
