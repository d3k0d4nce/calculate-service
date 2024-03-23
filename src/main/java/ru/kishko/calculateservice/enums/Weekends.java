package ru.kishko.calculateservice.enums;

import java.time.Month;

public enum Weekends {

    NOVY_GOD(Month.JANUARY, 1, 2, 3, 4, 5, 6, 8),
    ROZHDESTVO(Month.JANUARY, 7),
    ZASHITNIK(Month.FEBRUARY, 23),
    MEZHDUNARODNYI_ZHENSKIY_DEN(Month.MARCH, 8),
    PRAZDNIK_VESNY_TRUDA(Month.MAY, 1),
    DEN_POBEDY(Month.MAY, 9),
    DEN_ROSSII(Month.JUNE, 12),
    DEN_NARODNOGO_EDINSTVA(Month.NOVEMBER, 4);
    private final Month month;
    private final int[] days;

    Weekends(Month month, int... days) {
        this.month = month;
        this.days = days;
    }

    public Month getMonth() {
        return month;
    }

    public int[] getDays() {
        return days;
    }

    public boolean isDayExist(int day) {
        for (Integer temp_day : days) {
            if (temp_day == day) return true;
        }
        return false;
    }
}

