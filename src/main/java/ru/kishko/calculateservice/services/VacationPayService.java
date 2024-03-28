package ru.kishko.calculateservice.services;

import ru.kishko.calculateservice.dtos.input.InputVacationPayDTO;

import java.time.LocalDate;

public interface VacationPayService {

    Double calculateByDays(InputVacationPayDTO inputVacationPayDTO);

    Double calculateByDates(InputVacationPayDTO inputVacationPayDTO);
}
