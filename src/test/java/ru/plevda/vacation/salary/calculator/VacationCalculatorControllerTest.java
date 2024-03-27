package ru.plevda.vacation.salary.calculator;

import org.junit.jupiter.api.Test;
import ru.plevda.vacation.salary.calculator.controller.VacationCalculatorController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationCalculatorControllerTest {

    @Test
    public void testCalculateVacationSalary() {
        VacationCalculatorController controller = new VacationCalculatorController();

        // Тестирование без учета праздников
        VacationSalaryCalculatorResponse response = controller.calculateVacationSalary(50000, 10, null);
        assertEquals("20833.33", response.getCalculatedVacationSalary());

        // Тестирование с учетом праздников
        response = controller.calculateVacationSalary(50000, 10, Collections.singletonList(LocalDate.of(2024, 1, 10)));
        assertEquals("20982.86", response.getCalculatedVacationSalary());
    }
}
