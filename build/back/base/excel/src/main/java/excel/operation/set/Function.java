package excel.operation.set;

import excel.exception.ExcelOperateException;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * 计算方法
 *
 * @author fyy
 */
@Getter
public class Function {
    /**
     * 小计
     */
    private final Calculation subTotal;

    /**
     * 总计
     */
    private final Calculation total;

    /**
     * 总计所有
     */
    private final Builder totalAll;

    //------------------------------------------------------------------------------------------------------------------构造

    /**
     * 构造
     *
     * @param workbook 导出工作簿对象
     */
    public Function(Workbook workbook) {
        this.subTotal = new Calculation(workbook);
        this.total = new Calculation(workbook);
        this.totalAll = new Builder(workbook);
    }

    //------------------------------------------------------------------------------------------------------------------GetSet

    //------------------------------------------------------------------------------------------------------------------内部类

    /**
     * 计算配置构建基础类
     */
    @Getter
    public static class Builder {
        /**
         * 需要计算字段名,排序
         */
        private final Map<String, Integer> calculationFieldNameAndOrder;

        /**
         * 样式
         */
        private CellStyle style;

        /**
         * 行内额外数据
         */
        private Map<Integer, RowExtraData> rowExtraData;

        //--------------------------------------------------------------------------------------------------------------内部参数
        /**
         * 字体
         */
        private final Font font;

        /**
         * 格式
         */
        private final DataFormat format;

        //--------------------------------------------------------------------------------------------------------------内部类

        /**
         * 当前行额外数据
         */
        @Getter
        public static class RowExtraData {
            /**
             * 结果位置
             */
            private int order;
            /**
             * 值
             */
            private String value;
            /**
             * 是否是公式
             */
            private boolean isFormula;

            /**
             * 内部构造
             */
            private RowExtraData() {
            }

            /**
             * 创建
             */
            public static RowExtraData create() {
                return new RowExtraData().setIsFormula(false);
            }

            /**
             * 初始化
             */
            public RowExtraData build() {
                if (order < 1) {
                    throw new ExcelOperateException("诊断：缺少当前行额外列数据列号！", new NullPointerException());
                }
                if (value == null) {
                    throw new ExcelOperateException("诊断：缺少当前行额外列数据值！", new NullPointerException());
                }
                return this;
            }

            /**
             * 结果位置
             */
            public RowExtraData setOrder(int order) {
                this.order = order;
                return this;
            }

            /**
             * 值
             */
            public RowExtraData setValue(String value) {
                this.value = value;
                return this;
            }

            /**
             * 是否是公式
             */
            public boolean getIsFormula() {
                return isFormula;
            }

            /**
             * 是否是公式
             */
            public RowExtraData setIsFormula(boolean formula) {
                isFormula = formula;
                return this;
            }
        }

        //--------------------------------------------------------------------------------------------------------------构造
        //构造
        private Builder(Workbook workbook) {
            this.style = workbook.createCellStyle();
            this.font = workbook.createFont();
            this.format = workbook.createDataFormat();
            this.calculationFieldNameAndOrder = new HashMap<>();
            this.rowExtraData = new HashMap<>();
        }

        //--------------------------------------------------------------------------------------------------------------GetSet

        /**
         * 计算字段名 - 可以多个
         */
        public Builder setCalculationFieldNames(String... calculationFieldNames) {
            for (String name : calculationFieldNames) {
                Integer i = this.calculationFieldNameAndOrder.get(name);
                this.calculationFieldNameAndOrder.put(name, Objects.requireNonNullElse(i, 0));
            }
            return this;
        }

        /**
         * 设置计算字段名,位置
         */
        public Builder setCalculationFieldNameAndOrder(String calculationFieldName, int order) {
            this.calculationFieldNameAndOrder.put(calculationFieldName, order);
            return this;
        }

        /**
         * 多条设置计算字段名,位置
         */
        public Builder setCalculationFieldNameAndOrder(Map<String, Integer> calculationFieldNameAndOrder) {
            this.calculationFieldNameAndOrder.putAll(calculationFieldNameAndOrder);
            return this;
        }

        /**
         * 说明标注
         */
        public Builder setExplainAndOrder(String explain, int explainOrder) {
            RowExtraData rowExtraData = RowExtraData.create()
                    .setOrder(explainOrder)
                    .setValue(explain)
                    .build();
            this.rowExtraData.put(rowExtraData.order, rowExtraData);
            return this;
        }

        /**
         * 行内额外数据
         */
        public Builder setRowExtraData(Map<Integer, RowExtraData> rowExtraData) {
            this.rowExtraData = rowExtraData;
            return this;
        }

        /**
         * 行内额外数据
         */
        public Builder setRowExtraData(RowExtraData... rowExtraData) {
            for (RowExtraData red : rowExtraData) {
                if (red != null && red.getOrder() > 0) {
                    this.rowExtraData.put(red.order, red);
                }
            }
            return this;
        }

        /**
         * 行内额外数据
         */
        public Builder setRowExtraData(int order, String value, boolean isFormula) {
            RowExtraData rowExtraData = RowExtraData.create()
                    .setOrder(order)
                    .setValue(value)
                    .setIsFormula(isFormula)
                    .build();
            this.rowExtraData.put(rowExtraData.order, rowExtraData);
            return this;
        }

        /**
         * 样式
         */
        public Builder setStyle(CellStyle style) {
            this.style = style;
            return this;
        }


        /**
         * 标题颜色
         */
        public Builder setStyleColor(short styleColor) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(styleColor);
            return this;
        }

        /**
         * 标题颜色
         */
        public Builder setStyleColor(IndexedColors indexedColors) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(indexedColors.getIndex());
            return this;
        }

        /**
         * 标题是否加粗
         */
        public Builder setStyleBold(boolean bold) {
            font.setBold(true);
            style.setFont(font);
            return this;
        }

        /**
         * 水平位置设置
         */
        public Builder setHorizontalAlignment(HorizontalAlignment alignment) {
            style.setAlignment(alignment);
            return this;
        }

        /**
         * 垂直位置设置
         */
        public Builder setVerticalAlignment(VerticalAlignment alignment) {
            style.setVerticalAlignment(alignment);
            return this;
        }

        /**
         * 内容位置设置
         */
        public Builder setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
            style.setAlignment(horizontalAlignment);
            style.setVerticalAlignment(verticalAlignment);
            return this;
        }

        /**
         * 数字货币格式
         *
         * @param decimalAfterDigit 货币格式后保留位数
         */
        public Builder setStyleMoneyFormat(int decimalAfterDigit) {
            StringBuilder moneyFormat = new StringBuilder("#,##0");
            if (decimalAfterDigit > 0) {
                moneyFormat.append(".");
                moneyFormat.append("0".repeat(decimalAfterDigit));
            }
            style.setDataFormat(this.format.getFormat(moneyFormat.toString()));
            return this;
        }

        /**
         * 数字格式
         *
         * @param decimalAfterDigit 数字格式后保留位数
         */
        public Builder setStyleNumberFormat(int decimalAfterDigit) {
            StringBuilder sb = new StringBuilder("#0.");
            if (decimalAfterDigit > 0) {
                sb.append("0".repeat(decimalAfterDigit));
            } else {
                sb.append("000");
            }
            style.setDataFormat(this.format.getFormat(sb.toString()));
            return this;
        }

        /**
         * 边框
         */
        public Builder setBorder(BorderStyle borderStyle) {
            style.setBorderTop(borderStyle);
            style.setBorderBottom(borderStyle);
            style.setBorderLeft(borderStyle);
            style.setBorderRight(borderStyle);
            return this;
        }

        /**
         * 边框颜色
         */
        public Builder setBorderColor(IndexedColors indexedColors) {
            style.setTopBorderColor(indexedColors.getIndex());
            style.setBottomBorderColor(indexedColors.getIndex());
            style.setLeftBorderColor(indexedColors.getIndex());
            style.setRightBorderColor(indexedColors.getIndex());
            return this;
        }

        /**
         * 上边框
         */
        public Builder setBorderTop(BorderStyle borderStyle) {
            style.setBorderTop(borderStyle);
            return this;
        }

        /**
         * 上边框颜色
         */
        public Builder setBorderTopColor(IndexedColors indexedColors) {
            style.setTopBorderColor(indexedColors.getIndex());
            return this;
        }

        /**
         * 下边框
         */
        public Builder setBorderBottom(BorderStyle borderStyle) {
            style.setBorderBottom(borderStyle);
            return this;
        }

        /**
         * 下边框颜色
         */
        public Builder setBorderBottomColor(IndexedColors indexedColors) {
            style.setBottomBorderColor(indexedColors.getIndex());
            return this;
        }

        /**
         * 左边框
         */
        public Builder setBorderLeft(BorderStyle borderStyle) {
            style.setBorderLeft(borderStyle);
            return this;
        }

        /**
         * 左边框颜色
         */
        public Builder setBorderLeftColor(IndexedColors indexedColors) {
            style.setLeftBorderColor(indexedColors.getIndex());
            return this;
        }

        /**
         * 右边框
         */
        public Builder setBorderRight(BorderStyle borderStyle) {
            style.setBorderRight(borderStyle);
            return this;
        }

        /**
         * 右边框颜色
         */
        public Builder setBorderRightColor(IndexedColors indexedColors) {
            style.setRightBorderColor(indexedColors.getIndex());
            return this;
        }
    }

    /**
     * 计算配置类
     */
    @Getter
    public static class Calculation extends Builder {
        /**
         * 插入参考字段名 - 只能一个
         */
        private String referenceFieldName;

        /**
         * 插入后需要合并的字段
         */
        private List<String> spanFieldNames;


        //构造
        private Calculation(Workbook workbook) {
            super(workbook);
        }


        //创建方法
        public void setCalculationModel(String referenceFieldName, String[] calculationFieldNames, String explain, int explainOrder) {
            this.referenceFieldName = referenceFieldName;
            super.setCalculationFieldNames(calculationFieldNames).setExplainAndOrder(explain, explainOrder);
        }

        public void setCalculationModel(String referenceFieldName, String[] spanFieldNames, String[] calculationFieldNames, String explain, int explainOrder) {
            this.referenceFieldName = referenceFieldName;
            this.spanFieldNames = Arrays.asList(spanFieldNames);
            super.setCalculationFieldNames(calculationFieldNames).setExplainAndOrder(explain, explainOrder);
        }

        public void setCalculationModel(String referenceFieldName, Map<String, Integer> calculationFieldNameAndOrder, String explain, int explainOrder) {
            this.referenceFieldName = referenceFieldName;
            super.setCalculationFieldNameAndOrder(calculationFieldNameAndOrder).setExplainAndOrder(explain, explainOrder);
        }

        public void setCalculationModel(String referenceFieldName, String[] spanFieldNames, Map<String, Integer> calculationFieldNameAndOrder, String explain, int explainOrder) {
            this.referenceFieldName = referenceFieldName;
            this.spanFieldNames = Arrays.asList(spanFieldNames);
            super.setCalculationFieldNameAndOrder(calculationFieldNameAndOrder).setExplainAndOrder(explain, explainOrder);
        }

        //--------------------------------------------------------------------------------------------------------------GetSet

        /**
         * 插入参考字段名 - 只能一个
         */
        public Calculation setReferenceFieldName(String referenceFieldName) {
            this.referenceFieldName = referenceFieldName;
            return this;
        }

        /**
         * 插入后需要合并的字段
         */
        public Calculation setSpanFieldNames(String... spanFieldNames) {
            this.spanFieldNames = Arrays.asList(spanFieldNames);
            return this;
        }
    }

}
