package cn.fyy.mail.config.exception;

import dispose.LocalDateTimeDispose;

/**
 * 操作错误
 *
 * @author fyy
 */
public class MailException extends RuntimeException {
    /**
     * 操作错误
     */
    public MailException(String s, Exception e) {
        super("[" + LocalDateTimeDispose.getYearMonthDayHourMinuteSecond() + "] -> " + s, e);
    }
}
