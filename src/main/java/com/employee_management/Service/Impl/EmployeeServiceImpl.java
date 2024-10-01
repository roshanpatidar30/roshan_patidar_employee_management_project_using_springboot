package com.employee_management.Service.Impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_management.Entity.Employee;
import com.employee_management.Exception.UserNotFoundException;
import com.employee_management.Repository.EmployeeRepository;
import com.employee_management.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Long id, Employee employeeDetails) {
		Employee employee = getEmployeeById(id);
		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setDob(employeeDetails.getDob());
		employee.setDepartment(employeeDetails.getDepartment());
		return employeeRepository.save(employee);

	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee not found"));
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public Map<String, Long> getDepartmentWiseReport() {
		return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
				.collect(Collectors.groupingBy(employee -> employee.getDepartment(), Collectors.counting()));
	}
	


//	@Override
//	public Map<String, Long> getDepartmentWiseReport() {
//		Map<String,Long> departmentCountMap =  new HashMap<String, Long>();
//		Iterable<Employee> employees = employeeRepository.findAll();
//		
//	 for(Employee employee: employees) {
//		 String department = employee.getDepartment();
//		 if(departmentCountMap.containsKey(department)) {
//			 departmentCountMap.put(department, departmentCountMap.get(department)+1);
//		 }
//		 else {
//			 departmentCountMap.put(department, 1L);
//		 }
//			
//		}
//			return departmentCountMap;
//			
//	}

	
}
