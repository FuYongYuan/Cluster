package enumerate;

import lombok.Getter;

/**
 * 常用变量类型
 *
 * @author fyy
 */
@Getter
public enum UsedType {

    /**
     * java.lang.String
     */
    Type_String("java.lang.String"),

    /**
     * int
     */
    Type_int("int"),

    /**
     * java.lang.Integer
     */
    Type_Integer("java.lang.Integer"),

    /**
     * java.util.Date
     */
    Type_Util_Date("java.util.Date"),

    /**
     * java.time.LocalDateTime
     */
    Type_LocalDateTime("java.time.LocalDateTime"),

    /**
     * double
     */
    Type_double("double"),

    /**
     * java.lang.Double
     */
    Type_Double("java.lang.Double"),

    /**
     * java.lang.Boolean
     */
    Type_Boolean("java.lang.Boolean"),

    /**
     * boolean
     */
    Type_boolean("boolean"),

    /**
     * java.math.BigDecimal
     */
    Type_BigDecimal("java.math.BigDecimal"),

    /**
     * java.sql.Timestamp
     */
    Type_Timestamp("java.sql.Timestamp"),

    /**
     * java.sql.Date
     */
    Type_Sql_Date("java.sql.Date"),

    /**
     * java.math.BigInteger
     */
    Type_BigInteger("java.math.BigInteger"),

    /**
     * long
     */
    Type_long("long"),

    /**
     * java.lang.Long
     */
    Type_Long("java.lang.Long");

    /**
     * 实际值
     */
    private final String value;

    UsedType(String value) {
        this.value = value;
    }

}
