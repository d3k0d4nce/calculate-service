package ru.kishko.calculateservice.services.Impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.kishko.calculateservice.dtos.input.InputVacationPayDTO;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class VacationPayServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    InputVacationPayDTO inputVacationPayDTO;

    @BeforeEach
    void setUp() {

        inputVacationPayDTO = InputVacationPayDTO.builder()
                .averageSalary(100000.0)
                .vacationStart(LocalDate.of(2024, Month.MARCH, 19))
                .vacationEnd(LocalDate.of(2024, Month.APRIL, 19))
                .vacationDays(32)
                .build();

    }

    @Test
    void calculateWithDates() throws Exception {
        double expectedVacationPay = 78498.29;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", String.valueOf(inputVacationPayDTO.getAverageSalary()))
                        .param("vacationStart", inputVacationPayDTO.getVacationStart().toString())
                        .param("vacationEnd", inputVacationPayDTO.getVacationEnd().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedVacationPay)));
    }

    @Test
    void calculateWithoutDates() throws Exception {
        double expectedVacationPay = 109215.02;


        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", String.valueOf(inputVacationPayDTO.getAverageSalary()))
                        .param("vacationDays", String.valueOf(inputVacationPayDTO.getVacationDays())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedVacationPay)));
    }

    @Test
    void calculateWithoutRequiredParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("vacationDays", String.valueOf(inputVacationPayDTO.getVacationDays())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void calculateWithWrongDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", String.valueOf(inputVacationPayDTO.getAverageSalary()))
                        .param("vacationStart", inputVacationPayDTO.getVacationEnd().toString())
                        .param("vacationEnd", inputVacationPayDTO.getVacationEnd().toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
