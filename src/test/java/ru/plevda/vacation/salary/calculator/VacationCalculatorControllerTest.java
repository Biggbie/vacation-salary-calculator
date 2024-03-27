package ru.plevda.vacation.salary.calculator;

import org.junit.jupiter.api.Test;
import ru.plevda.vacation.salary.calculator.controller.VacationCalculatorController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationCalculatorControllerTest {

    @Test
    public void testCalculateVacationSalary() {
        VacationCalculatorController controller = new VacationCalculatorController();

        // Тестирование без учета праздников
        VacationSalaryCalculatorResponse response = controller.calculateVacationSalary(50000.0, 10, null, null);
        assertEquals("20833.33", response.getCalculatedVacationSalary());

        // Тестирование с учетом праздников
        response = controller.calculateVacationSalary(50000.0, null, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        assertEquals("19546.67", response.getCalculatedVacationSalary());

        // Тестирование вывода сообщения при указании как vacationDays, так и startVacationDate
        assertThrows(IllegalArgumentException.class, () -> controller.calculateVacationSalary(50000.0, 10, LocalDate.of(2024, 1, 1), null));

        // Тестирование вывода сообщения при указании как vacationDays, так и endVacationDate
        assertThrows(IllegalArgumentException.class, () -> controller.calculateVacationSalary(50000.0, 10, null, LocalDate.of(2024, 1, 10)));
    }
}
