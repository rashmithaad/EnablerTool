package com.demo.enablerbackendtool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.enablerbackendtool.model.Employee;
import com.demo.enablerbackendtool.service.EmployeeService;
import com.demo.enablerbackendtool.util.ReportGenerator;
import com.demo.enablerbackendtool.service.PowerBIService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private PowerBIService powerBIService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee addedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding employee", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (RuntimeException e) {
            logger.error("Error deleting employee with id " + id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error deleting employee with id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error updating employee with id " + id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Unexpected error updating employee with id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{entity_name}/{category}/{country}/{fiscal_year_start}/{fiscal_year_end}/{division}/{region}/{department_name}")
    public ResponseEntity<List<Employee>> getFilteredEmployees(
            @PathVariable String entity_name,
            @PathVariable String category,
            @PathVariable String country,
            @PathVariable String fiscal_year_start,
            @PathVariable String fiscal_year_end,
            @PathVariable String division,
            @PathVariable String region,
            @PathVariable String department_name) {
        try {
            List<Employee> filteredEmployees = employeeService.getFilteredEmployees(
                entity_name, category, country, fiscal_year_start, fiscal_year_end, division, region, department_name);
            return new ResponseEntity<>(filteredEmployees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/downloadFiltered")
    public ResponseEntity<byte[]> downloadFilteredReport(
            @RequestParam String format,
            @RequestParam(required = false) String entity_name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String fiscal_year_start,
            @RequestParam(required = false) String fiscal_year_end,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String department_name) {
        try {
            List<Employee> filteredEmployees = employeeService.getFilteredEmployees(
                    entity_name, category, country, fiscal_year_start, fiscal_year_end,
                    division, region, department_name);
            byte[] report = ReportGenerator.generateReport(filteredEmployees, format);
            if (report == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            String filename = "filtered_report." + format.toLowerCase();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .body(report);

        } catch (Exception e) {
            logger.error("Error generating filtered report in format {}: {}", format, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/downloadPowerBI")
    public ResponseEntity<Map<String, String>> downloadPowerBIReport() {
        try {
            Map<String, String> powerBIData = powerBIService.getPowerBIAccessToken();
            return new ResponseEntity<>(powerBIData, HttpStatus.OK);
        } catch (Exception e) {
            // Handle error, log it, and return appropriate response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}