package util;

import java.time.LocalDateTime;
import java.time.YearMonth;

public abstract class DateUtil {
    public static LocalDateTime getThisMonthFirstDayZero(LocalDateTime now) {
        return getThisMonthFirstDayZero(now.getYear(), now.getMonthValue());
    }

    public static LocalDateTime getThisMonthFirstDayZero(int year, int month) {
        return YearMonth.of(year, month).atDay(1).atStartOfDay();
    }

    public static LocalDateTime getNextMonthFirstDayZero(LocalDateTime now) {
        return getNextMonthFirstDayZero(now.getYear(), now.getMonthValue());
    }

    public static LocalDateTime getNextMonthFirstDayZero(int year, int month) {
        return YearMonth.of(year, month).plusMonths(1).atDay(1).atStartOfDay();
    }
}
