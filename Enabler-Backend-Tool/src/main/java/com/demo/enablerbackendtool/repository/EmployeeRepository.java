package com.demo.enablerbackendtool.repository;

import com.demo.enablerbackendtool.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.entity_name = :entity_name AND e.category = :category AND e.country = :country AND e.fiscal_year_start = :fiscal_year_start AND e.fiscal_year_end = :fiscal_year_end AND e.division = :division AND e.region = :region AND e.department_name = :department_name AND e.employee_status = 0")
    List<Employee> findFilteredEmployees(
        @Param("entity_name") String entity_name,
        @Param("category") String category,
        @Param("country") String country,
        @Param("fiscal_year_start") String fiscal_year_start,
        @Param("fiscal_year_end") String fiscal_year_end,
        @Param("division") String division,
        @Param("region") String region,
        @Param("department_name") String department_name);
}
