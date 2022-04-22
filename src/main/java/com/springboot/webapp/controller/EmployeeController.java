package com.springboot.webapp.controller;

import com.springboot.webapp.domain.Employee;
import com.springboot.webapp.service.EmployeeServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@ComponentScan
public class EmployeeController {

	@Autowired
	EmployeeServiceInterface employeeServiceInterface;

	@GetMapping("/test/sample/page")
	public String samplepage(Model model) {
		System.out.println();
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "index";
	}
	
	
	@GetMapping("/test/sample")
	public String testpage(Model model) {
		System.out.println();
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "index";
	}
	
	
	@GetMapping("/test")
	public String savePage1(Model model) {
		System.out.println();
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/")
	public String savePage(Model model) {
		System.out.println();
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return "index";
	}
	
	@PostMapping("/employee/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,
			final RedirectAttributes redirectAttributes) {
		
		if(employeeServiceInterface.saveEmployee(employee)!=null) {
			redirectAttributes.addFlashAttribute("saveEmployee", "success");
		} else {
			redirectAttributes.addFlashAttribute("saveEmployee", "unsuccess");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/employee/{operation}/{empId}", method = RequestMethod.GET)
	public String editRemoveEmployee(@PathVariable("operation") String operation,
			@PathVariable("empId") String empId, final RedirectAttributes redirectAttributes,
			Model model) {
		if(operation.equals("delete")) {
			if(employeeServiceInterface.deleteEmployee(empId)) {
				redirectAttributes.addFlashAttribute("deletion", "success");
			} else {
				redirectAttributes.addFlashAttribute("deletion", "unsuccess");
			}
		} else if(operation.equals("edit")){
		  Employee editEmployee = employeeServiceInterface.findEmployee(empId);
		  if(editEmployee!=null) {
		       model.addAttribute("editEmployee", editEmployee);
		       return "editPage";
		  } else {
			  redirectAttributes.addFlashAttribute("status","notfound");
		  }
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("editEmployee") Employee editEmployee,
			final RedirectAttributes redirectAttributes) {
		if(employeeServiceInterface.editEmployee(editEmployee)!=null) {
			redirectAttributes.addFlashAttribute("edit", "success");
		} else {
			redirectAttributes.addFlashAttribute("edit", "unsuccess");
		}
//		return "redirect:/savepage";
		return "redirect:/";
	}
}
