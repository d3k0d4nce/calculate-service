package ru.kishko.calculateservice.utils;

import org.springframework.stereotype.Component;
import ru.kishko.calculateservice.enums.Weekends;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class UtilsImpl implements Utils {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    @Override
    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        return Arrays.stream(Weekends.values())
                .anyMatch(weekend -> weekend.getMonth() == date.getMonth() && weekend.isDayExist(date.getDayOfMonth()));
    }

    @Override
    public Double calculate(Double averageSalary, Long numberOfDays) {
        return Math.round(averageSalary / AVERAGE_DAYS_IN_MONTH * numberOfDays * 100.0) / 100.0;
    }
}
