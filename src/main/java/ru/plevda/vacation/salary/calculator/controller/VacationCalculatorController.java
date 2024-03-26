package ru.plevda.vacation.salary.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;
import ru.plevda.vacation.salary.calculator.service.VacationCalculatorService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VacationCalculatorController {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public VacationSalaryCalculatorResponse calculateVacationSalary(@RequestParam double averageSalary,
                                                                    @RequestParam int vacationDays,
                                                                    @RequestParam(required = false) List<LocalDate> vacationDates) {
        if (vacationDates != null && !vacationDates.isEmpty()) {
            return vacationCalculatorService.calculateWithHolidays(averageSalary, vacationDays, vacationDates);
        } else {
            return vacationCalculatorService.calculate(averageSalary, vacationDays);
        }
    }
}

