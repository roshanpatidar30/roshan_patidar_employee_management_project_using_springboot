package com.employee_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_management.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
