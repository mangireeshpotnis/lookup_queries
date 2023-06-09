package com.lookup_and_jpa.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lookup_and_jpa.models.Employee;
import com.lookup_and_jpa.projections.EmployeeWithoutSalary;

public interface EmployeeRepository extends CrudRepository<Employee , Integer> {
	
	//Overriden CRUD method that masks the salary
	Optional<EmployeeWithoutSalary> findProjectedById(int id);
	
	
	//get all employee details by name while hiding salary
	List<EmployeeWithoutSalary> findByName(String name);
	
	//get all employee details by city while hiding salary
	List<EmployeeWithoutSalary> findByCity(String city);
	
	
	//get all employee details by salary ( we wont use projection here as we dont wanna mask our salary) 
	List<Employee> findBySalary(int sal);
	 
	
	//get all employee details by either name or city while hiding the salary
	List<EmployeeWithoutSalary> findByNameOrCity(String name, String city);
	
	//get all employee details using both name and city while hiding the salary
	List<EmployeeWithoutSalary> findByNameAndCity(String name, String city);
	
	//get all by city order by salary ascending
	List<Employee> findByCityOrderBySalary(String city);
	
	//get all by city order by salary descending
	List<Employee> findByCityOrderBySalaryDesc(String city);
	
	//get employees whose name is like(String name)
	List<EmployeeWithoutSalary> findByNameLike(String name);
	
	//get employees whose city is like(String city)
	List<EmployeeWithoutSalary> findByCityNotLike(String city);
	
	//get employees whose name starts with(String name)
	List<EmployeeWithoutSalary> findByNameStartingWith(String name);
	
	//get employees whose city ends with(String city)
	List<EmployeeWithoutSalary> findByCityEndingWith(String city);
	
	//get employees whose name contains(String name)
	List<EmployeeWithoutSalary> findByNameContaining(String name);
	
	//get salaries greater than(String salary)
	List<Employee> findBySalaryGreaterThan(int salary);
		
	//get salaries between(sal1,sal2)	
	List<Employee>	findBySalaryBetween(int sal1, int sal2);
		
	//get salaries less than(int sal)
	List<Employee>findBySalaryLessThanEqual(int sal);
	
	
	

	
	
	
	

}
