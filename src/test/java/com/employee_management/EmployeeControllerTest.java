package com.employee_management;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.employee_management.Entity.Employee;
import com.employee_management.Service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {	
	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EmployeeService employeeService;

	    @Test
	    @WithMockUser(username = "admin", roles = {"ADMIN"})
	    public void testGetAllEmployeesForAdmin() throws Exception {
	        Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of(new Employee()));

	        mockMvc.perform(get("/api/employees/view"))
	                .andExpect(status().isOk());
	    }

	    @Test
	    @WithMockUser(username = "normal", roles = {"NORMAL"})
	    public void testGetAllEmployeesForNormalUser() throws Exception {
	        Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of(new Employee()));

	        mockMvc.perform(get("/api/employees/view"))
	                .andExpect(status().isOk());
	    }

	    @Test
	    @WithMockUser(username = "admin", roles = {"ADMIN"})
	    public void testGetEmployeeByIdForAdmin() throws Exception {
	        Employee employee = new Employee();
	        employee.setId(1L);
	        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

	        mockMvc.perform(get("/api/employees/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(1L));
	    }

	    @Test
	    @WithMockUser(username = "normal", roles = {"NORMAL"})
	    public void testGetEmployeeByIdForNormalUser() throws Exception {
	        Employee employee = new Employee();
	        employee.setId(1L);
	        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

	        mockMvc.perform(get("/api/employees/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(1L));
	    }

	    @Test
	    @WithMockUser(username = "normal", roles = {"NORMAL"})
	    public void testGetEmployeeByIdNotFound() throws Exception {
	        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(null);

	        mockMvc.perform(get("/api/employees/1"))
	                .andExpect(status().isNotFound());
	    }
	}