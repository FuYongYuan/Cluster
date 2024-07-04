package dispose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 各种判断处理类
 *
 * @author fyy
 */
public class JudgeDispose {
    /**
     * 整型正则
     */
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 浮点正则
     */
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^[-\\+]?\\d*[.]\\d+$");

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        if (null == value || value.isEmpty()) {
            return false;
        }
        Matcher matcher = INTEGER_PATTERN.matcher(value);
        return matcher.matches();
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        if (null == value || value.isEmpty()) {
            return false;
        }
        Matcher matcher = DOUBLE_PATTERN.matcher(value);
        return matcher.matches();
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 判断一个字符串是否含有数字
     */
    public static boolean isHasDigit(String content) {
        boolean flag = false;
        Pattern p = compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断一个字符是否是中文
     */
    public static boolean isChinese(char c) {
        // 根据字节码判断
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    /**
     * 判断一个字符串是否含有中文
     */
    public static boolean isChinese(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                // 有一个中文字符就返回
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否都为大写
     */
    public static boolean isAllUpper(String str) {
        return str.matches("[A-Z]*");
    }

    /**
     * 判断是否都为小写
     */
    public static boolean isAllLower(String str) {
        return str.matches("[a-z]*");
    }

    /**
     * 判断一个字符串是否都为数字
     */
    public static boolean isDigit(String str) {
        return str.matches("[0-9]*");
    }

    /**
     * 判断去除第一位后续是否是驼峰命名规则
     */
    public static boolean isCamelCase(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        str = str.substring(1);
        int upper = 0;
        int lower = 0;
        int other = 0;
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i + 1);
            if (isAllUpper(s)) {
                upper = upper + 1;
            } else if (isAllLower(s)) {
                lower = lower + 1;
            } else {
                other = other + 1;
            }
        }
        if (other > 0) {
            return false;
        } else if ((upper + lower) == str.length()) {
            return upper != 0 && lower != 0;
        } else {
            return false;
        }
    }

    /**
     * 判断是否包含表情
     */
    public static boolean isContainsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50) {
                    return true;
                }
                if (source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
