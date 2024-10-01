package com.employee_management.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_management.Entity.Employee;
import com.employee_management.Service.EmployeeService;
import com.employee_management.dto.ResponseDto;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/employees/admin")
public class EmployeeAdminController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto> createEmployee(@Validated @RequestBody Employee employee) {
		employeeService.createEmployee(employee);
		return ResponseEntity.ok(ResponseDto.builder().message("Employee create successfully...").status(HttpStatus.OK)
				.success(true).build());
	}

//	@GetMapping("/view")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
//	public List<Employee> getAllEmployees() {
//		return employeeService.getAllEmployees();
//	}

//	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//		Optional<Employee> emOptional = Optional.ofNullable(employeeService.getEmployeeById(id));
//		if (emOptional.isPresent()) {
//			return ResponseEntity.ok(emOptional.get());
//		}
//		return ResponseEntity.notFound().build();
//	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Validated @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employee);
		if (updatedEmployee != null) {
			return ResponseEntity.ok(updatedEmployee);
		}
		return ResponseEntity.notFound().build();
	}

	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok(ResponseDto.builder().status(HttpStatus.OK).message("Employee deleted successfully..").build());
	}

	
	@GetMapping("/report/department-wise")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Long>> getDepartmentWiseReport() {
		Map<String, Long> departmentReport = employeeService.getDepartmentWiseReport();
		return ResponseEntity.ok(departmentReport);
	}
}
