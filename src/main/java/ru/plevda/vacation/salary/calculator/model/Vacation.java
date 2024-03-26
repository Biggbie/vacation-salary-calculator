package ru.plevda.vacation.salary.calculator.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Vacation {
    private double averageSalary;
    private int vacationDays;
    private List<LocalDate> vacationDates;
}
