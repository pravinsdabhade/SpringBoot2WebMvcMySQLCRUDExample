package com.example.mvc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc.model.Employee;
import com.example.mvc.repository.EmployeeRepository;
import com.example.mvc.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee e) {
		// Local variable Type inference(DataType is decided by Java Compiler)
		var empSalary = e.getEmpSalary();
		var empHra = empSalary * 20 / 100;
		var empTa = empSalary * 10 / 100;

		// Set back to the object.
		e.setEmpHra(empHra);
		e.setEmpTa(empTa);
		return repo.save(e).getEmpId();
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = repo.findAll();
		return emps;
//		 return emps.stream().sorted((e1,e2)-> e2.getEmpId().compareTo(e1.getEmpId())).collect(Collectors.toList());
		/*
		 * emps.stream() .sorted( //(e1,e2)->e1.getEmpId().compareTo(e2.getEmpId())
		 * //ASC (e1,e2)->e2.getEmpId().compareTo(e1.getEmpId()) //DESC )
		 * .collect(Collectors.toList());)
		 */
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer empId) {
		return repo.findById(empId);
	}

	@Override
	public void updateEmployee(Employee e) {
		if (repo.existsById(e.getEmpId())) {
			var empSalary = e.getEmpSalary();
			var empHra = empSalary * 20 / 100;
			var empTa = empSalary * 10 / 100;

			// Set back to the object.
			e.setEmpHra(empHra);
			e.setEmpTa(empTa);
			repo.save(e);
		}
	}

	@Override
	public void deleteEmployeeById(Integer id) {
		repo.deleteById(id);
	}

}
