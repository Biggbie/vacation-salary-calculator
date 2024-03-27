package ru.plevda.vacation.salary.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;
import ru.plevda.vacation.salary.calculator.service.VacationCalculatorService;

import java.time.LocalDate;

@RestController
public class VacationCalculatorController {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<VacationSalaryCalculatorResponse> calculateVacationSalary(@RequestParam(name = "averageSalary") Double averageSalary,
                                                  @RequestParam(required = false, name = "vacationDays") Integer vacationDays,
                                                  @RequestParam(required = false, name = "startVacationDate") LocalDate startVacationDate,
                                                  @RequestParam(required = false, name = "endVacationDate") LocalDate endVacationDate) {
        if (vacationDays != null && (startVacationDate != null || endVacationDate != null)) {
            return ResponseEntity.badRequest().build();
        }
        if (startVacationDate != null && endVacationDate != null) {
            return ResponseEntity.ok(vacationCalculatorService.calculateWithHolidays(averageSalary, startVacationDate, endVacationDate));
        } else {
            if (vacationDays == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(vacationCalculatorService.calculate(averageSalary, vacationDays));
        }
    }
}
