package com.employee_management.Service;

import java.util.List;
import java.util.Map;

import com.employee_management.Entity.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee updateEmployee(Long id, Employee employee);

	void deleteEmployee(Long id);

	Employee getEmployeeById(Long id);

	Map<String, Long> getDepartmentWiseReport();
}
