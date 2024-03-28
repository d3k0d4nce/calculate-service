package ru.kishko.calculateservice.services.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kishko.calculateservice.dtos.input.InputVacationPayDTO;
import ru.kishko.calculateservice.exceptions.DateException;
import ru.kishko.calculateservice.services.VacationPayService;
import ru.kishko.calculateservice.utils.Utils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {

    private final Utils utils;

    @Override
    public Double calculateByDays(InputVacationPayDTO inputVacationPayDTO) {
        return utils.calculate(inputVacationPayDTO.getAverageSalary(), Long.valueOf(inputVacationPayDTO.getVacationDays()));
    }

    @Override
    public Double calculateByDates(InputVacationPayDTO inputVacationPayDTO) {
        LocalDate vacationStart = inputVacationPayDTO.getVacationStart();
        LocalDate vacationEnd = inputVacationPayDTO.getVacationEnd();

        if (!vacationEnd.isAfter(vacationStart)) {
            throw new DateException("vacationEnd must be after than vacationStart");
        }

        long resultDays = vacationStart.datesUntil(vacationEnd)
                .filter(day -> !utils.isHoliday(day) && !utils.isWeekend(day))
                .count();

        return utils.calculate(inputVacationPayDTO.getAverageSalary(), resultDays);
    }
}
