package com.demo.enablerbackendtool.service;

import java.util.List;
import com.demo.enablerbackendtool.model.Employee;

public interface EmployeeService {
    
    public Employee addEmployee(Employee employee);
    
    public List<Employee> getAllEmployee();
    
    public Employee getEmployeeById(int id);
    
    public void deleteEmployee(int id);
    
    public Employee updateEmployee(int id, Employee employee);
    
    public List<Employee> getFilteredEmployees(
            String entity_name, String category, String country,
            String fiscal_year_start, String fiscal_year_end, String division,
            String region, String department_name);
}
