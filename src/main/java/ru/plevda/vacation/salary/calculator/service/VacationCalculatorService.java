package ru.plevda.vacation.salary.calculator.service;


import org.springframework.stereotype.Service;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;

@Service
public class VacationCalculatorService {

    public VacationSalaryCalculatorResponse calculate(double averageSalary, int vacationDays) {
        // Простой рассчет без учета начала отпуска
        return new VacationSalaryCalculatorResponse();
    }

}
