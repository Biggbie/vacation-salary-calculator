package ru.plevda.vacation.salary.calculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.plevda.vacation.salary.calculator.controller.VacationCalculatorController;
import ru.plevda.vacation.salary.calculator.model.VacationSalaryCalculatorResponse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VacationCalculatorControllerTest {

    @Test
    public void testCalculateVacationSalary_WithAllParameters_ReturnsBadRequest() {
        // Arrange
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 5000.0;
        Integer vacationDays = 10;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);

        // Act
        ResponseEntity<VacationSalaryCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, vacationDays, startVacationDate, endVacationDate);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithOnlyAverageSalary_ReturnsBadRequest() {
        // Arrange
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 5000.0;

        // Act
        ResponseEntity<VacationSalaryCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, null, null, null);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithVacationDaysAndStartVacationDate_ReturnsBadRequest() {
        // Arrange
        VacationCalculatorController controller = new VacationCalculatorController();
        Integer vacationDays = 10;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);

        // Act
        ResponseEntity<VacationSalaryCalculatorResponse> responseEntity = controller.calculateVacationSalary(null, vacationDays, startVacationDate, null);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithVacationDays_ReturnsValidResponse() {
        // Arrange
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 5000.0;
        Integer vacationDays = 10;

        // Act
        ResponseEntity<VacationSalaryCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, vacationDays, null, null);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getCalculatedVacationSalary());
        assertEquals("4354.35", responseEntity.getBody().getCalculatedVacationSalary());
    }

    @Test
    public void testCalculateVacationSalary_WithStartAndEndVacationDate_ReturnsValidResponse() {
        // Arrange
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 5000.0;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);

        // Act
        ResponseEntity<VacationSalaryCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, null, startVacationDate, endVacationDate);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getCalculatedVacationSalary());
        assertEquals("4354.35", responseEntity.getBody().getCalculatedVacationSalary());
    }
}
