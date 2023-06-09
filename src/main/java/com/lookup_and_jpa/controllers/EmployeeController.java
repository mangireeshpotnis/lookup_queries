package com.lookup_and_jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lookup_and_jpa.models.Employee;
import com.lookup_and_jpa.projections.EmployeeWithoutSalary;
import com.lookup_and_jpa.services.EmployeeServices;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	EmployeeServices employeeServices;

	@Autowired
	public void setEmployeeSERVICE(EmployeeServices empls) {
		this.employeeServices = empls;
	}

	@GetMapping("")
	public ResponseEntity<?> showAllEmployees() {
		return employeeServices.showAllEmployees();		
	}

	@GetMapping("/{id}")
	public Employee showEmployeeById(@PathVariable int id) {
		return employeeServices.showEmployeeById(id);		
	}

	@GetMapping("/employee-without-salary/{id}")
	public EmployeeWithoutSalary showEmployeeByIdWithoutSalary(@PathVariable int id) {
		return employeeServices.showEmployeeByIdWithoutSalary(id);
	}	

	@GetMapping("/search-by-name")
	public ResponseEntity<?> showEmployeesByNameWithoutSalary(@RequestParam String name) {
		return employeeServices.showEmployeeByNameWithoutSalary(name);
	}	


	@GetMapping("/search-by-city")
	public ResponseEntity<?> showEmployeesByNameWithoutCity(@RequestParam String city) {
		return employeeServices.showEmployeeByCityWithoutSalary(city);
	}


	@GetMapping("/universal-individual-search")
	public ResponseEntity<?> universalShowMethodForIndividualSearch(@RequestParam(required=false) String name, @RequestParam(required=false) String city,
			@RequestParam(required=false) Integer salary) {

		return employeeServices.universalFunctionalityIndividualSearch(name, city, salary);		
	}

	@GetMapping("/universal-or-search")
	public ResponseEntity<?> universalShowMethodForOrSearch(@RequestParam(required=false) String name, @RequestParam(required=false) String city) {

		return employeeServices.universalFunctionalityOrSearch(name, city);
	}
	
	@GetMapping("/universal-and-search")
	public ResponseEntity<?> universalShowMethodForAndSearch(@RequestParam(required=true) String name, @RequestParam(required=true) String city) {

		return employeeServices.universalFunctionalityAndSearch(name, city);
	}
	
	@GetMapping("/findByCityOrderBySalary")
	public ResponseEntity<?> findByCityOrderBySalary(@RequestParam String city) {
		return employeeServices.findByCityOrderBySalary(city);
		
	}
	
	@GetMapping("/findByCityOrderBySalaryDesc")
	public ResponseEntity<?> findByCityOrderBySalaryDesc(@RequestParam String city) {
		return employeeServices.findByCityOrderBySalaryDesc(city);
		
	}
	
	@GetMapping("/findByNameLike")
	public ResponseEntity<?> findByNameLike(@RequestParam String name){
		return employeeServices.findByNameLike(name);		
	}
	
	@GetMapping("/findByCityNotLike")
	public ResponseEntity<?> findByCityNotLike(@RequestParam String city){
		return employeeServices.findByCityNotLike(city);
	}
	
	@GetMapping("/findByNameStartingWith")
	public ResponseEntity<?> findByNameStartingWith(@RequestParam String name){
		return employeeServices.findByNameStartingWith(name);
	}
	
	@GetMapping("/findByCityEndingWith")
	public ResponseEntity<?>  findByCityEndingWith(@RequestParam String city){
		return employeeServices.findByCityEndingWith(city);
	}
	
	@GetMapping("/findByNameContaining")
	public ResponseEntity<?> findByNameContaining(@RequestParam String name){
		return employeeServices.findByNameContaining(name);
	}
	
	@GetMapping("/findBySalaryGreaterThan")
	public ResponseEntity<?> findBySalaryGreaterThan(@RequestParam Integer salary){
		return employeeServices.findBySalaryGreaterThan(salary);		
	}
	
	@GetMapping("/findBySalaryBetween")
	public ResponseEntity<?> findBySalaryBetween(@RequestParam Integer sal1,@RequestParam Integer sal2){
	
		return employeeServices.findBySalaryBetween(sal1,sal2);
	}
	
	@GetMapping("/findBySalaryLessThanEqual")
	public ResponseEntity<?> findBySalaryLessThanEqual(@RequestParam Integer salary){
		return employeeServices.findBySalaryLessThanEqual(salary);
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<?> addNewEmployee(@RequestBody Employee employee) {
		return employeeServices.addNewEmployee(employee);		
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployeeById(@PathVariable int id, @RequestBody Employee employee) {
		return employeeServices.updateEmployeeById(id, employee);		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
		return employeeServices.deleteEmployeeById(id);

	}





}
