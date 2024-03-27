package ru.plevda.vacation.salary.calculator.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Vacation {
    private Double averageSalary;
    private Integer vacationDays;
    private LocalDate startVacationDate;
    private LocalDate endVacationDate;
}
