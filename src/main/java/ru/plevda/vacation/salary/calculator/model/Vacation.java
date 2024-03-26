package ru.plevda.vacation.salary.calculator.model;

import lombok.Data;

import java.util.List;

@Data
public class Vacation {
    private double averageSalary;
    private int vacationDays;
    private List<Integer> vacationDates;
}
