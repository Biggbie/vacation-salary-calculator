package ru.plevda.vacation.salary.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.plevda.vacation.salary.calculator.controller.VacationCalculatorController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VacationCalculatorControllerTest {

    private VacationCalculatorController controller;

    @BeforeEach
    void setUp() {
        controller = new VacationCalculatorController();
    }

    @Test
    void testCalculateVacationSalaryWithVacationDays() {
        // Подготавливаем данные для теста
        Double averageSalary = 5000.0;
        Integer vacationDays = 14;

        // Вызываем метод контроллера
        ResponseEntity<VacationSalaryCalculatorResponse> httpResponse = controller.calculateVacationSalary(averageSalary, vacationDays, null, null);

        // Проверяем HTTP статус ответа
        assertEquals(HttpStatus.OK, httpResponse.getStatusCode());

        // Проверяем, что объект ответа не null
        assertNotNull(httpResponse.getBody());

        // Другие проверки, если необходимо
    }

    @Test
    void testCalculateVacationSalaryWithStartAndEndDate() {
        // Подготавливаем данные для теста
        Double averageSalary = 5000.0;
        LocalDate startVacationDate = LocalDate.of(2024, 4, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 4, 14);

        // Вызываем метод контроллера
        ResponseEntity<VacationSalaryCalculatorResponse> httpResponse = controller.calculateVacationSalary(averageSalary, null, startVacationDate, endVacationDate);

        // Проверяем HTTP статус ответа
        assertEquals(HttpStatus.OK, httpResponse.getStatusCode());

        // Проверяем, что объект ответа не null
        assertNotNull(httpResponse.getBody());

        // Другие проверки, если необходимо
    }

    @Test
    void testCalculateVacationSalaryWithInvalidParameters() {
        // Подготавливаем данные для теста
        Double averageSalary = 5000.0;
        Integer vacationDays = 14;
        LocalDate startVacationDate = LocalDate.of(2024, 4, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 4, 14);

        // Вызываем метод контроллера
        ResponseEntity<VacationSalaryCalculatorResponse> httpResponse = controller.calculateVacationSalary(averageSalary, vacationDays, startVacationDate, endVacationDate);

        // Проверяем HTTP статус ответа
        assertEquals(HttpStatus.BAD_REQUEST, httpResponse.getStatusCode());

        // Другие проверки, если необходимо
    }
}
