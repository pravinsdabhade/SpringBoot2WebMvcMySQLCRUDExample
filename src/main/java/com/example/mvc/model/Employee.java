package com.example.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EMPLOYEE")
public class Employee {

	@Id
	//auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer empId;

	@Column(name = "ENAME")
	private String empName;

	@Column(name = "Esalary")
	private Double empSalary;

	@Column(name = "EDEPT")
	private String empDept;

	@Column(name = "EHRA")
	private Double empHra;

	@Column(name = "ETA")
	private Double empTa;
}
