package ru.plevda.vacation.salary.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationSalaryCalculatorResponse {
    private String calculatedVacationSalary;
}
