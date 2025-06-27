package schedule.service;

/**
 * 公历
 *
 * @author fyy
 */
class GregorianCalendar {
    /**
     * 获取月份多少天
     *
     * @param month 月份
     * @param year  年
     */
    public static int getDaysOfMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> (year % 400) == 0 || (year % 100) != 0 && (year % 4) == 0 ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 0;
        };
    }
}
