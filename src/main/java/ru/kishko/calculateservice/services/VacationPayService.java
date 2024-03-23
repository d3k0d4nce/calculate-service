package ru.kishko.calculateservice.services;

import java.time.LocalDate;

public interface VacationPayService {

    Double calculateByDays(Double averageSalary, Integer numberOfDays);

    Double calculateByDates(Double averageSalary, LocalDate vacationStart, LocalDate vacationEnd);
}
