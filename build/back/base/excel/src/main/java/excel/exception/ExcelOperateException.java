package excel.exception;


import dispose.LocalDateTimeDispose;


/**
 * 操作错误
 *
 * @author fyy
 */
public class ExcelOperateException extends RuntimeException {
    /**
     * 操作错误
     */
    public ExcelOperateException(String s, Exception e) {
        super("[" + LocalDateTimeDispose.getYearMonthDayHourMinuteSecond() + "] -> " + s, e);
    }
}
