package ru.plevda.vacation.salary.calculator.service;

import org.springframework.stereotype.Service;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;
import java.text.DecimalFormat;
import java.time.YearMonth;
import java.util.stream.IntStream;

@Service
public class VacationCalculatorService {

    private static final int MONTHS_IN_YEAR = 12;

    public VacationSalaryCalculatorResponse calculate(double averageSalary, int vacationDays) {
        // Обычный рассчет
        YearMonth currentYear = YearMonth.now();
        int totalDaysInYear = IntStream.rangeClosed(1, MONTHS_IN_YEAR)
                .map(month -> currentYear.lengthOfMonth())
                .sum();
        double averageDaysInMonth = (double) totalDaysInYear / MONTHS_IN_YEAR;
        double calculatedVacationSalary = (averageSalary / averageDaysInMonth) * vacationDays;
        String formattedSalary = new DecimalFormat("#.##").format(calculatedVacationSalary);
        return new VacationSalaryCalculatorResponse(formattedSalary);
    }

}
