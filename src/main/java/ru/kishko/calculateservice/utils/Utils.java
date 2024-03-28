package ru.kishko.calculateservice.utils;

import java.time.LocalDate;

public interface Utils {
    boolean isWeekend(LocalDate date);

    boolean isHoliday(LocalDate date);

    Double calculate(Double averageSalary, Long numberOfDays);
}
