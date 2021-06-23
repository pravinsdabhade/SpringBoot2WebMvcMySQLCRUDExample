package com.example.mvc.service;

import java.util.List;
import java.util.Optional;

import com.example.mvc.model.Employee;

public interface IEmployeeService {
	
	Integer saveEmployee(Employee e);
	
	List<Employee> getEmployees();
	
	Optional<Employee> getEmployeeById(Integer id);
	
	void updateEmployee(Employee e);
	
	void deleteEmployeeById(Integer id);
}
