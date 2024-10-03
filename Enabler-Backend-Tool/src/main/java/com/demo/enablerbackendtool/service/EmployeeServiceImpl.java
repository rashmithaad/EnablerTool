package com.demo.enablerbackendtool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.enablerbackendtool.model.Employee;
import com.demo.enablerbackendtool.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee oldEmployee = optionalEmployee.get();
            employee.setId(oldEmployee.getId());
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @Override
    public List<Employee> getFilteredEmployees(String entity_name, String category, String country, 
            String fiscal_year_start, String fiscal_year_end, String division, 
            String region, String department_name) {
        // Implement the logic to filter employees based on the criteria
        return employeeRepository.findFilteredEmployees(
                entity_name, category, country, fiscal_year_start, fiscal_year_end, 
                division, region, department_name);
    }
}
