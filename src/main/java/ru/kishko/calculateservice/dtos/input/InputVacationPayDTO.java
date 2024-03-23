package ru.kishko.calculateservice.dtos.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InputVacationPayDTO {

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false, message = "must be more than 0.00")
    private Double averageSalary;

    @Max(value = 45)
    @Min(value = 1, message = "must be more than 1")
    private Integer vacationDays;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate vacationStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate vacationEnd;

}
