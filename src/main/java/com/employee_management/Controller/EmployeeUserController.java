package com.employee_management.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_management.Entity.Employee;
import com.employee_management.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeUserController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/view")
	@PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> emOptional = Optional.ofNullable(employeeService.getEmployeeById(id));
		if (emOptional.isPresent()) {
			return ResponseEntity.ok(emOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

}
