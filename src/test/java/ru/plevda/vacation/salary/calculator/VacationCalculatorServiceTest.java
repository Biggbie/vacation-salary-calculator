package ru.plevda.vacation.salary.calculator;

import org.junit.jupiter.api.Test;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;
import ru.plevda.vacation.salary.calculator.service.VacationCalculatorService;
import java.time.LocalDate;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationCalculatorServiceTest {

    private final VacationCalculatorService service = new VacationCalculatorService();

    @Test
    public void testCalculate() {
        VacationSalaryCalculatorResponse response = service.calculate(50000, 10);
        assertEquals("20833.33", response.getCalculatedVacationSalary());
    }

    @Test
    public void testCalculateWithHolidays() {
        VacationSalaryCalculatorResponse response = service.calculateWithHolidays(50000, 10, Collections.singletonList(LocalDate.of(2024, 1, 10)));
        assertEquals("20982.88", response.getCalculatedVacationSalary());
    }
}
