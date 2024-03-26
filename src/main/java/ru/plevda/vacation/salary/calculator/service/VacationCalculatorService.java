package ru.plevda.vacation.salary.calculator.service;

import org.springframework.stereotype.Service;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;
import ru.plevda.vacation.salary.calculator.util.PublicHolidays;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class VacationCalculatorService {

    private static final int MONTHS_IN_YEAR = 12;

    public VacationSalaryCalculatorResponse calculate(double averageSalary, int vacationDays) {
        // Обычный расчет
        YearMonth currentYear = YearMonth.now();
        int totalDaysInYear = IntStream.rangeClosed(1, MONTHS_IN_YEAR)
                .map(month -> currentYear.lengthOfMonth())
                .sum();
        double averageDaysInMonth = (double) totalDaysInYear / MONTHS_IN_YEAR;
        double calculatedVacationSalary = (averageSalary / averageDaysInMonth) * vacationDays;
        String formattedSalary = new DecimalFormat("#.##").format(calculatedVacationSalary);
        return new VacationSalaryCalculatorResponse(formattedSalary);
    }

    public VacationSalaryCalculatorResponse calculateWithHolidays(double averageSalary, int vacationDays, List<LocalDate> vacationDates) {
        // Расчет с учетом праздничных дней

        double totalVacationSalary = 0;

        for (LocalDate vacationDate : vacationDates) {
            // Получаем месяц и год даты отпуска
            int year = vacationDate.getYear();
            int month = vacationDate.getMonthValue();

            // Получаем количество рабочих дней в месяце
            int workingDays = calculateWorkingDays(year, month);

            // Вычисляем среднюю зарплату за рабочий день в этом месяце
            double averageSalaryPerDay = averageSalary / workingDays;

            // Получаем количество рабочих дней в отпуске
            int vacationDaysInMonth = calculateVacationDaysInMonth(vacationDate, vacationDays);

            // Рассчитываем отпускные для этого месяца и добавляем к общей сумме
            totalVacationSalary += averageSalaryPerDay * vacationDaysInMonth;
        }

        // Форматируем результат и возвращаем
        String formattedSalary = new DecimalFormat("#.##").format(totalVacationSalary);
        return new VacationSalaryCalculatorResponse(formattedSalary);
    }

    private int calculateWorkingDays(int year, int month) {
        // Рассчитываем количество рабочих дней в указанном месяце и году

        int workingDays = 0;
        YearMonth yearMonth = YearMonth.of(year, month);

        int daysInMonth = yearMonth.lengthOfMonth();

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            // Проверяем, является ли день рабочим
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }

        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        // Проверяем, является ли указанный день рабочим (не субботой, не воскресеньем и не праздничным)
        return date.getDayOfWeek().getValue() < 6 && !PublicHolidays.HOLIDAYS.contains(date);
    }

    private int calculateVacationDaysInMonth(LocalDate vacationDate, int vacationDays) {
        // Вычисляем количество рабочих дней в отпуске для указанного месяца

        YearMonth yearMonth = YearMonth.of(vacationDate.getYear(), vacationDate.getMonth());
        int daysInMonth = yearMonth.lengthOfMonth();

        int remainingVacationDays = vacationDays;
        int workingDaysInVacation = 0;

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(vacationDate.getYear(), vacationDate.getMonth(), day);
            if (isWorkingDay(date)) {
                workingDaysInVacation++;
                if (--remainingVacationDays == 0) {
                    break; // Заканчиваем, когда заканчиваются отпускные дни
                }
            }
        }

        return workingDaysInVacation;
    }
}
