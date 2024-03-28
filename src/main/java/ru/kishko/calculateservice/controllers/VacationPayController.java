package ru.kishko.calculateservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kishko.calculateservice.dtos.input.InputVacationPayDTO;
import ru.kishko.calculateservice.exceptions.InputException;
import ru.kishko.calculateservice.services.VacationPayService;

@RestController
@RequiredArgsConstructor
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@Valid InputVacationPayDTO inputVacationPayDTO) {
        if (inputVacationPayDTO.getVacationDays() != null) {
            return ResponseEntity.ok(vacationPayService.calculateByDays(inputVacationPayDTO));
        } else if (inputVacationPayDTO.getVacationStart() != null && inputVacationPayDTO.getVacationEnd() != null) {
            return ResponseEntity.ok(vacationPayService.calculateByDates(inputVacationPayDTO));
        }
        throw new InputException("There are some problems with DTO format: " + inputVacationPayDTO);
    }
}
