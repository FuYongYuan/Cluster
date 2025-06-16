package cn.fyy.dictionary.bean.bo;

import excel.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
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
public class ParameterExcel implements Serializable {

    /**
     * 主键ID
     */
    @ExcelField(columnName = "主键ID", order = 1)
    private BigInteger id;

    /**
     * 参数代码
     */
    @ExcelField(columnName = "参数代码", order = 2)
    private String parameterCode;

    /**
     * 参数名称
     */
    @ExcelField(columnName = "参数名称", order = 3)
    private String parameterName;

    /**
     * 参数值
     */
    @ExcelField(columnName = "参数值", order = 4)
    private String parameterValue;

    /**
     * 参数说明
     */
    @ExcelField(columnName = "参数说明", order = 5)
    private String parameterExplain;

    /**
     * 创建时间
     */
    @ExcelField(columnName = "创建时间", order = 6)
    private LocalDateTime createTime;

    /**
     * 创建人名称
     */
    @ExcelField(columnName = "创建人名称", order = 7)
    private String creatorName;

    /**
     * 修改时间
     */
    @ExcelField(columnName = "修改时间", order = 8)
    private LocalDateTime updateTime;

    /**
     * 修改人名称
     */
    @ExcelField(columnName = "修改人名称", order = 9)
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @ExcelField(columnName = "状态(0.正常 99.删除)", order = 10, valueLimit = "0=正常;99=删除;")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 参数 BO 转换 Excel
     */
    public static ParameterExcel toExcel(ParameterBO bo) {
        if (bo != null) {
            return ParameterExcel.builder()
                    .id(bo.getId())
                    .parameterCode(bo.getParameterCode())
                    .parameterName(bo.getParameterName())
                    .parameterValue(bo.getParameterValue())
                    .parameterExplain(bo.getParameterExplain())
                    .createTime(bo.getCreateTime())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 参数 BO 数组 转换 Excel List
     */
    public static List<ParameterExcel> toExcel(ParameterBO[] bos) {
        if (bos != null) {
            List<ParameterExcel> excels = new ArrayList<>();
            for (ParameterBO bo : bos) {
                excels.add(toExcel(bo));
            }
            return excels;
        } else {
            return null;
        }
    }

    /**
     * 参数 BO List 转换 Excel List
     */
    public static List<ParameterExcel> toExcel(List<ParameterBO> bos) {
        if (bos != null) {
            List<ParameterExcel> excels = new ArrayList<>();
            for (ParameterBO bo : bos) {
                excels.add(toExcel(bo));
            }
            return excels;
        } else {
            return null;
        }
    }

    /**
     * 参数 Excel 转换 BO
     */
    public static ParameterBO toBO(ParameterExcel excel) {
        if (excel != null) {
            return ParameterBO.builder()
                    .id(excel.getId())
                    .parameterCode(excel.getParameterCode())
                    .parameterName(excel.getParameterName())
                    .parameterValue(excel.getParameterValue())
                    .parameterExplain(excel.getParameterExplain())
                    .createTime(excel.getCreateTime())
                    .creatorName(excel.getCreatorName())
                    .updateTime(excel.getUpdateTime())
                    .updaterName(excel.getUpdaterName())
                    .state(excel.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 参数 Excel List 转换 BO List
     */
    public static List<ParameterBO> toBO(List<ParameterExcel> excels) {
        if (excels != null) {
            List<ParameterBO> bos = new ArrayList<>();
            for (ParameterExcel excel : excels) {
                bos.add(toBO(excel));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 参数 Excel 转换 BO
     */
    public ParameterBO toBO() {
        return ParameterBO.builder()
                .id(this.getId())
                .parameterCode(this.getParameterCode())
                .parameterName(this.getParameterName())
                .parameterValue(this.getParameterValue())
                .parameterExplain(this.getParameterExplain())
                .createTime(this.getCreateTime())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .build();
    }
}
