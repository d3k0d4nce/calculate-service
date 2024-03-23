package ru.kishko.calculateservice.services.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kishko.calculateservice.exceptions.DateException;
import ru.kishko.calculateservice.services.VacationPayService;
import ru.kishko.calculateservice.utils.Utils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {

    private final Utils utils;

    @Override
    public Double calculateByDays(Double averageSalary, Integer numberOfDays) {
        return utils.calculate(averageSalary, Long.valueOf(numberOfDays));
    }

    @Override
    public Double calculateByDates(Double averageSalary, LocalDate vacationStart, LocalDate vacationEnd) {
        if (!vacationEnd.isAfter(vacationStart)) {
            throw new DateException("vacationEnd must be after than vacationStart");
        }

        long resultDays = vacationStart.datesUntil(vacationEnd)
                .filter(day -> !utils.isHoliday(day) && !utils.isWeekend(day))
                .count();

        return utils.calculate(averageSalary, resultDays);
    }
}
