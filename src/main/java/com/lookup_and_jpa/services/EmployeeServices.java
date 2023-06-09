package com.lookup_and_jpa.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.lookup_and_jpa.helpers.EmployeeResponseWrapper;
import com.lookup_and_jpa.models.Employee;
import com.lookup_and_jpa.projections.EmployeeWithoutSalary;
import com.lookup_and_jpa.repositories.EmployeeRepository;

@Service
public class EmployeeServices {
	EmployeeRepository employeeRepository;

	@Autowired
	public void setEmployeeCRUD(EmployeeRepository emprs) {
		this.employeeRepository = emprs;
	}

	//get all records
	public ResponseEntity<?> showAllEmployees() {
		Iterable<Employee> empData = employeeRepository.findAll();
		Iterator<Employee> allEmployees = empData.iterator();
		if(allEmployees.hasNext()) {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Employees currently in database");
			erw.setData(allEmployees);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Employees Exist at the moment");
		}
	}	

	//get all records by id
	public Employee showEmployeeById( int id) {
		return employeeRepository.findById(id).orElseThrow(
				()-> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Employee exists for the id you requested");});		
	}

	//get all records by id while masking salary
	public EmployeeWithoutSalary showEmployeeByIdWithoutSalary(int id) {
		return  employeeRepository.findProjectedById(id).orElseThrow(
				()-> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Employee exists for the id you requested");});	
	}

	//get all records by name while masking salary
	public ResponseEntity<?> showEmployeeByNameWithoutSalary(String name) {
		List<EmployeeWithoutSalary> employeesWithoutSalary = employeeRepository.findByName(name);

		if(employeesWithoutSalary.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No employee exists for the name you requested");
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the name you requested");
			erw.setData(employeesWithoutSalary);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);
		}
	}

	//get all records by city while masking salary
	public ResponseEntity<?> showEmployeeByCityWithoutSalary(String city) {
		List<EmployeeWithoutSalary> employeesWithoutSalary = employeeRepository.findByCity(city);
		if(employeesWithoutSalary.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No employee exists for the name you requested");			
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the name you requested");
			erw.setData(employeesWithoutSalary);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);

		}
	}

	//Universal-search: INDIVIDUAL  [ byName(),bySalary(),byCity() ]
	public ResponseEntity<?>  universalFunctionalityIndividualSearch(String name, String city, Integer salary) {
		if(name != null ) {
			List<EmployeeWithoutSalary> returnedEmployees = employeeRepository.findByName(name);
			if( returnedEmployees.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with this name");
			}
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the name you requested");
			erw.setData(returnedEmployees);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}
		else if(city !=null) {
			List<EmployeeWithoutSalary> returnedEmployees = employeeRepository.findByCity(city);
			if( returnedEmployees.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with this city");
			}
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the city you requested");
			erw.setData(returnedEmployees);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);					
		}
		else {
			List<Employee> returnedEmployees = employeeRepository.findBySalary(salary);
			if( returnedEmployees.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with this salary");
			}
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the salary you requested");
			erw.setData(returnedEmployees);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}
	}

	//Universal Search: OR  [ byName()ORbyCity() ]
	public ResponseEntity<?> universalFunctionalityOrSearch(String name,String city) {
		List<EmployeeWithoutSalary> employeesByNameOrCity = employeeRepository.findByNameOrCity(name, city);
		if(employeesByNameOrCity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists for either of these parameters");
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the parameter you requested");
			erw.setData(employeesByNameOrCity);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);
		}

	}

	//Universal Search: AND [byName()AndbyCity() ]
	public ResponseEntity<?> universalFunctionalityAndSearch(String name,String city) {
		List<EmployeeWithoutSalary> returnedEmployees = employeeRepository.findByNameAndCity(name, city);
		if( returnedEmployees.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with these parameters");
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the parameters you requested");
			erw.setData(returnedEmployees);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);
		}		
	}

	//findByCityOrderBySalary(String city)
	public ResponseEntity<?> findByCityOrderBySalary(String city) {
		List<Employee> employeesByCityOrderedBySalary = employeeRepository.findByCityOrderBySalary(city);
		if(employeesByCityOrderedBySalary.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with the city you requested");			
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the parameters you requested");
			erw.setData(employeesByCityOrderedBySalary);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}


	//findByCityOrderBySalaryDESC(String city)
	public ResponseEntity<?> findByCityOrderBySalaryDesc(String city) {
		List<Employee> employeesByCityOrderBySalaryDesc = employeeRepository.findByCityOrderBySalaryDesc(city);
		if(employeesByCityOrderBySalaryDesc.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No record exists with the city you requested");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the parameters you requested");
			erw.setData(employeesByCityOrderBySalaryDesc);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);		

		}
	}

	//get employees whose name is like(String name)
	public ResponseEntity<?> findByNameLike(String name) {
		List<EmployeeWithoutSalary> employeesWithNames = employeeRepository.findByNameLike(name);
		if(employeesWithNames.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No with the name you requested");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the name you requested");
			erw.setData(employeesWithNames);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);		

		}
	}

	//get employees whose city is like(String city)
	public ResponseEntity<?> findByCityNotLike(String city) {
		List<EmployeeWithoutSalary> notLikeCity = employeeRepository.findByCityNotLike(city);
		if(notLikeCity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees excluding the city you entered");
			erw.setData(notLikeCity);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}

	//get employees whose name starts with(String name)
	public ResponseEntity<?>  findByNameStartingWith(String name) {
		List<EmployeeWithoutSalary> employeeStartingBy = employeeRepository.findByNameStartingWith(name);
		if(employeeStartingBy.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees excluding the city you entered");
			erw.setData(employeeStartingBy);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}

	//get employees whose city ends with(String city)
	public ResponseEntity<?> findByCityEndingWith(String city){
		List<EmployeeWithoutSalary> endingWithCity = employeeRepository.findByCityEndingWith(city);
		if( endingWithCity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees excluding the city you entered");
			erw.setData(endingWithCity);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}

	//get employees whose name contains(String name)
	public ResponseEntity<?>  findByNameContaining(String name){
		List<EmployeeWithoutSalary> containingCity = employeeRepository.findByNameContaining(name);
		if( containingCity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees excluding the city you entered");
			erw.setData(containingCity);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}

	//get salaries greater than(String salary)
	public ResponseEntity<?> findBySalaryGreaterThan(Integer salary){
		List<Employee> employeesWithRelevantSalaries = employeeRepository.findBySalaryGreaterThan(salary);
		if( employeesWithRelevantSalaries.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the relevant salary");
			erw.setData(employeesWithRelevantSalaries);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}		
	}

	//get salaries between(sal1,sal2)	
	public ResponseEntity<?> findBySalaryBetween(int sal1, int sal2){
		List<Employee> employeesWithRelevantSalaries = employeeRepository.findBySalaryBetween(sal1, sal2);
		if( employeesWithRelevantSalaries.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the relevant range");
			erw.setData(employeesWithRelevantSalaries);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}			
	}

	//get salaries lessThanEqual(int sal)
	public ResponseEntity<?>  findBySalaryLessThanEqual(int sal){
		List<Employee> employeesWithRelevantSalaries = employeeRepository.findBySalaryLessThanEqual(sal);
		if( employeesWithRelevantSalaries.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such records ");		
		}
		else {
			EmployeeResponseWrapper erw = new EmployeeResponseWrapper();
			erw.setMessage("Found the following employees with the relevant salary");
			erw.setData(employeesWithRelevantSalaries);
			return new ResponseEntity<>(erw,HttpStatus.FOUND);			
		}			
	}
	
	

	

	//add new record
	public ResponseEntity<?> addNewEmployee( Employee employee) {
		employeeRepository.save(employee);
		return new ResponseEntity<>("Employee Added successfully",HttpStatus.CREATED);		
	}

	//update existing record
	public ResponseEntity<?> updateEmployeeById( int id,  Employee employee) {
		Employee employeeAtId = this.showEmployeeById(id);
		employee.setCreatedDate(employeeAtId.getCreatedDate());
		employee.setId(id);
		employeeRepository.save(employee);
		return new ResponseEntity<>("Employee Updated successfully",HttpStatus.OK);
	}

	//delete a record
	public ResponseEntity<?> deleteEmployeeById( int id) {
		this.showEmployeeById(id);
		employeeRepository.deleteById(id);
		return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);		
	}


}
