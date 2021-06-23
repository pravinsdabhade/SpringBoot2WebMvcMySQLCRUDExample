package com.example.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc.model.Employee;
import com.example.mvc.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService service;

	// 1. show Register page
	/**
	 * If End user enters /register, GET type then we should display one Register
	 * page in browser
	 */
	@GetMapping("/register")
	public String showRegisterForm() {
		System.out.println("Inside register...");
		return "EmployeeRegister";
	}

	// 2. save() : Click Form submit
	/***
	 * On Click HTML FORM SUBMIT READ DATA AS MODLEATTRIBUTE SAVE USING SERVICE
	 * RETURN MESSAGE TO UI
	 */
	@PostMapping("/save")
	public String saveEmployeeData(@ModelAttribute Employee employee, Model mdodel) {
		System.out.println("Inside save employee data...");
		Integer saveEmployeeId = service.saveEmployee(employee);
		String message = "Employee '" + saveEmployeeId + "' created...";
		mdodel.addAttribute("EmployeeMessage", message);
		return "EmployeeRegister";
	}

	// 3. display all rows
	/***
	 * FETCH DATA FROM DATABASE as LIST<T> SEND THIS TO UI. USE FOR EACH AND DISPLAY
	 * AS HTML TABLE
	 */
	@GetMapping("/all")
	public String getEmployeeData(Model model) {
		System.out.println("Inside get employee data...");
		List<Employee> employees = service.getEmployees();
		model.addAttribute("employeeList", employees);
		return "EmployeeData";
	}

	// 4. Delete employee by id
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam Integer id, Model model) {
		System.out.println("Inside delete employee....");

		// delete recode by id
		service.deleteEmployeeById(id);

		// send message to UI
		model.addAttribute("message", "Employee '" + id + "' deleted...");

		// load latest data
		List<Employee> employees = service.getEmployees();
		model.addAttribute("employeeList", employees);

		return "EmployeeData";
	}

	// 5. Show Data in Edit (by id)
	/***
	 * On click Edit Link , read id and load object from DB if exist goto Edit page,
	 * else redirect to all page
	 */
	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id, Model model) {
		System.out.println("Inside edit...");
		String page = null;
		// try to load data from DB
		Optional<Employee> employeeById = service.getEmployeeById(id);
		if (employeeById.isPresent()) {
			model.addAttribute("employee", employeeById.get());
			page = "EmployeeEdit";
		} else {
			// no data exist
			page = "redirect:all"; // Go to EmployeeData page.
		}
		return page;
	}

	// 6. Update data
	/**
	 * On Click Update button, read Form data as ModelAttribute Update in DB and
	 * send success message to UI. Also load latest data
	 */
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Employee e, Model model) {
		service.updateEmployee(e);

		// send message to ui
		model.addAttribute("updateMessage", "Employee '" + e.getEmpId() + "' updated...");

		// load latest data
		List<Employee> employees = service.getEmployees();
		model.addAttribute("employeeList", employees);

		return "EmployeeData";

	}

}
