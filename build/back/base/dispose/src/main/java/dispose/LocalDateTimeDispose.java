package dispose;

import enumerate.DateType;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * 时间处理类
 *
 * @author fyy
 */
public class LocalDateTimeDispose {
    //------------------------------------------------------------------------------------------------------------------默认日期时间格式
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DateType.Year_Month_Day_Hour_Minute_Second.getValue());

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 获取当前 LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 当前时间-----------------------------------------------------------------------------------------------------------
     */
    public static String getYear() {
        return format(now(), DateType.Year);
    }

    public static String getMonth() {
        return format(now(), DateType.Month);
    }

    public static String getDay() {
        return format(now(), DateType.Day);
    }

    public static String getHour() {
        return format(now(), DateType.Hour);
    }

    public static String getMinute() {
        return format(now(), DateType.Minute);
    }

    public static String getSecond() {
        return format(now(), DateType.Second);
    }

    public static String getYearMonthDay() {
        return format(now(), DateType.Year_Month_Day);
    }

    public static String getYearMonth() {
        return format(now(), DateType.Year_Month);
    }

    public static String getMonthDay() {
        return format(now(), DateType.Month_Day);
    }

    public static String getYearMonthDayHourMinuteSecond() {
        return format(now(), DateType.Year_Month_Day_Hour_Minute_Second);
    }

    public static String getYearMonthDayHourMinuteSecondMillisecond() {
        return format(now(), DateType.Year_Month_Day_Hour_Minute_Second_MS);
    }

    public static String getHourMinuteSecond() {
        return format(now(), DateType.Hour_Minute_Second);
    }

    public static String getMonthDayHourMinuteSecond() {
        return format(now(), DateType.Month_Day_Hour_Minute_Second);
    }

    public static String getMonthDayHourMinute() {
        return format(now(), DateType.Month_Day_Hour_Minute);
    }

    public static String getDayHourMinute() {
        return format(now(), DateType.Day_Hour_Minute);
    }

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 字符串转 LocalDateTime，默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime parse(String datetimeStr) {
        return LocalDateTime.parse(datetimeStr, DEFAULT_FORMATTER);
    }

    /**
     * 指定格式字符串转 LocalDateTime
     */
    public static LocalDateTime parse(String datetimeStr, DateType dateType) {
        return LocalDateTime.parse(datetimeStr, DateTimeFormatter.ofPattern(dateType.getValue()));
    }

    /**
     * 格式化 LocalDateTime，默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String format(LocalDateTime time) {
        return time.format(DEFAULT_FORMATTER);
    }

    /**
     * 按指定格式格式化 LocalDateTime
     */
    public static String format(LocalDateTime time, DateType dateType) {
        return time.format(DateTimeFormatter.ofPattern(dateType.getValue()));
    }

    //------------------------------------------------------------------------------------------------------------------获取时间戳

    /**
     * 时间戳转时间(10位时间戳)
     */
    public static String timestamp10() {
        long timeStampSec = System.currentTimeMillis() / 1000;
        return String.format("%010d", timeStampSec);
    }

    /**
     * 时间戳转时间(13位时间戳)
     */
    public static String timestamp13() {
        return String.format("%010d", System.currentTimeMillis());
    }

    //------------------------------------------------------------------------------------------------------------------计算

    /**
     * 时间加法：支持年、月、日、小时、分钟、秒
     *
     * @param time   时间
     * @param amount 需要加减 加用正数 减用负数
     * @param unit   时间单位
     */
    public static LocalDateTime add(LocalDateTime time, int amount, ChronoUnit unit) {
        return time.plus(amount, unit);
    }

    /**
     * 时间减法：支持年、月、日、小时、分钟、秒
     *
     * @param time   时间
     * @param amount 需要加减 加用正数 减用负数
     * @param unit   时间单位
     */
    public static LocalDateTime subtract(LocalDateTime time, int amount, ChronoUnit unit) {
        return time.minus(amount, unit);
    }

    //------------------------------------------------------------------------------------------------------------------年计算

    /**
     * 年计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String yearCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(yearCalculate(time, i), dateType);
    }

    /**
     * 年计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String yearCalculate(String time, int i, DateType dateType) {
        return format(yearCalculate(time, i), dateType);
    }

    /**
     * 年计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime yearCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.YEARS);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.YEARS);
        }
        return result;
    }

    /**
     * 年计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime yearCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.YEARS);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.YEARS);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------月计算

    /**
     * 月计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String monthCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(monthCalculate(time, i), dateType);
    }

    /**
     * 月计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String monthCalculate(String time, int i, DateType dateType) {
        return format(monthCalculate(time, i), dateType);
    }

    /**
     * 月计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime monthCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.MONTHS);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.MONTHS);
        }
        return result;
    }

    /**
     * 月计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime monthCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.MONTHS);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.MONTHS);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------日计算

    /**
     * 日计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String dayCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(dayCalculate(time, i), dateType);
    }

    /**
     * 日计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String dayCalculate(String time, int i, DateType dateType) {
        return format(dayCalculate(time, i), dateType);
    }

    /**
     * 日计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime dayCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.DAYS);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.DAYS);
        }
        return result;
    }

    /**
     * 日计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime dayCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.DAYS);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.DAYS);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------时计算

    /**
     * 时计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String hoursCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(hoursCalculate(time, i), dateType);
    }

    /**
     * 时计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String hoursCalculate(String time, int i, DateType dateType) {
        return format(hoursCalculate(time, i), dateType);
    }

    /**
     * 时计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime hoursCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.HOURS);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.HOURS);
        }
        return result;
    }

    /**
     * 时计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime hoursCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.HOURS);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.HOURS);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------分计算

    /**
     * 分计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String minutesCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(minutesCalculate(time, i), dateType);
    }

    /**
     * 分计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String minutesCalculate(String time, int i, DateType dateType) {
        return format(minutesCalculate(time, i), dateType);
    }

    /**
     * 分计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime minutesCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.MINUTES);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.MINUTES);
        }
        return result;
    }

    /**
     * 分计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime minutesCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.MINUTES);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.MINUTES);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------秒计算

    /**
     * 秒计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String secondsCalculate(LocalDateTime time, int i, DateType dateType) {
        return format(secondsCalculate(time, i), dateType);
    }

    /**
     * 秒计算
     *
     * @param time     时间
     * @param i        需要加减 加用正数 减用负数
     * @param dateType 返回的格式
     */
    public static String secondsCalculate(String time, int i, DateType dateType) {
        return format(secondsCalculate(time, i), dateType);
    }

    /**
     * 秒计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime secondsCalculate(LocalDateTime time, int i) {
        LocalDateTime result = time;
        if (i > 0) {
            result = add(time, i, ChronoUnit.SECONDS);
        } else if (i < 0) {
            result = subtract(time, -i, ChronoUnit.SECONDS);
        }
        return result;
    }

    /**
     * 秒计算
     *
     * @param time 时间
     * @param i    需要加减 加用正数 减用负数
     */
    public static LocalDateTime secondsCalculate(String time, int i) {
        LocalDateTime result = parse(time);
        if (i > 0) {
            result = add(result, i, ChronoUnit.SECONDS);
        } else if (i < 0) {
            result = subtract(result, -i, ChronoUnit.SECONDS);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------比较

    /**
     * 比较时间是否相等
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return true 时间1和时间2相等    false 时间1和时间2不相等
     */
    public static boolean isEqual(LocalDateTime time1, LocalDateTime time2) {
        return time1.isEqual(time2);
    }

    /**
     * 比较时间大小
     * LocalDate a = LocalDateTime.of(2012, 6, 30, 12, 00);
     * LocalDate b = LocalDateTime.of(2012, 7, 1, 12, 00);
     * a.isBefore(b) == true
     * a.isBefore(a) == false
     * b.isBefore(a) == false
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return true 时间1比时间2（时间1早-时间2大）|| false 时间1比时间2（时间1晚-时间2小）
     */
    public static boolean compareDate(LocalDateTime time1, LocalDateTime time2) {
        return time1.isBefore(time2);
    }

    /**
     * 判断给定时间是否在 [startDate, endDate] 范围内（闭区间）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param date      时间
     * @return true 是    false 否
     */
    public static boolean compareDateWithin(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date) {
        return !date.isBefore(startTime) && !date.isAfter(endTime);
    }
    //------------------------------------------------------------------------------------------------------------------差值计算

    /**
     * 计算两个时间差
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param returnType 返回类型   hh小时  mm分钟  ss秒  day天
     */
    public static Long timeBetween(LocalDateTime startTime, LocalDateTime endTime, DateType returnType) {
        Duration duration = Duration.between(startTime, endTime);
        // 输出结果
        if (DateType.Hour.equals(returnType)) {
            return duration.toHours();
        } else if (DateType.Minute.equals(returnType)) {
            return duration.toMinutes();
        } else if (DateType.Second.equals(returnType)) {
            return duration.toSeconds();
        } else if (DateType.Day.equals(returnType)) {
            return duration.toDays();
        } else {
            return 0L;
        }
    }

    /**
     * 计算两个时间差
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param formatType 格式
     * @param returnType 返回类型   hh小时  mm分钟  ss秒  day天
     */
    public static Long dateDiff(String startTime, String endTime, DateType formatType, DateType returnType) {
        return timeBetween(parse(startTime, formatType), parse(endTime, formatType), returnType);
    }

    //------------------------------------------------------------------------------------------------------------------判断

    /**
     * 获取星期几（周一返回 1，周日返回 7）
     */
    public static int getDayOfWeek(LocalDateTime time) {
        // Monday = 1
        // Tuesday = 2
        // Wednesday = 3
        // Thursday = 4
        // Friday = 5
        // Saturday = 6
        // Sunday = 7
        return time.getDayOfWeek().getValue();
    }

    /**
     * 获取星期几（周一返回 1，周日返回 7）
     * Monday = 1
     * Tuesday = 2
     * Wednesday = 3
     * Thursday = 4
     * Friday = 5
     * Saturday = 6
     * Sunday = 7
     */
    public static int getDayOfWeek(String time) {
        return parse(time).getDayOfWeek().getValue();
    }

    /**
     * 判断当月多少天
     *
     * @param month 月份
     * @param year  年
     * @return 多少天
     */
    public static int getDaysOfMonth(int year, int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> (year % 400) == 0 || (year % 100) != 0 && (year % 4) == 0 ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 0;
        };
    }

    /**
     * 获取本月的第一天
     */
    public static LocalDateTime getFirstDayOfMonth() {
        return now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月的最后一天
     */
    public static LocalDateTime getLastDayOfMonth() {
        return now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取本周的第一天（周一）
     */
    public static LocalDateTime getFirstDayOfWeek() {
        return now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * 获取本周的最后一天（周日）
     */
    public static LocalDateTime getLastDayOfWeek() {
        return now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }


    //------------------------------------------------------------------------------------------------------------------随机时间

    /**
     * 生成指定范围内的随机 LocalDateTime（精确到毫秒，线程安全）
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 随机 LocalDateTime
     */
    public static LocalDateTime randomDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("结束时间不能早于开始时间");
        }

        // 将 LocalDateTime 转换为纪元秒和纳秒偏移量
        long startEpochSecond = startTime.toInstant(ZoneOffset.UTC).getEpochSecond();
        long endEpochSecond = endTime.toInstant(ZoneOffset.UTC).getEpochSecond();

        // 在总秒数范围内随机选择一个秒数
        long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond + 1);

        // 如果是同一秒，则在纳秒内再随机
        long nanoAdjust = 0;
        if (startEpochSecond == endEpochSecond) {
            long startNano = startTime.getNano();
            long endNano = endTime.getNano();
            if (endNano > startNano) {
                nanoAdjust = ThreadLocalRandom.current().nextLong(startNano, endNano + 1);
            }
        } else {
            nanoAdjust = ThreadLocalRandom.current().nextLong(0, 1_000_000_000);
        }

        // 构造最终的随机时间
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(randomEpochSecond, nanoAdjust),
                ZoneId.systemDefault()
        );
    }

    /**
     * 生成随机日期
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return 随机日期
     */
    public static String randomDateTimeString(LocalDateTime startTime, LocalDateTime endTime) {
        return format(randomDateTime(startTime, endTime));
    }

    //------------------------------------------------------------------------------------------------------------------时间生成

    /**
     * 按照指定频率生成时间槽（例如每天、每小时、每分钟）
     *
     * @param start    起始时间
     * @param end      结束时间
     * @param interval 间隔数量
     * @param unit     时间单位（ChronoUnit）
     * @return 时间槽列表
     */
    public static List<LocalDateTime> generateTimeSlots(LocalDateTime start, LocalDateTime end, long interval, ChronoUnit unit) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("结束时间不能早于开始时间");
        }

        List<LocalDateTime> slots = new ArrayList<>();
        LocalDateTime current = start;

        while (!current.isAfter(end)) {
            slots.add(current);
            current = current.plus(interval, unit);
        }
        return slots;
    }

}
