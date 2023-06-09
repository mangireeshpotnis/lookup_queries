package com.lookup_and_jpa.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@NotNull(message="salary cannot be null!")
	int salary;
	@NotNull(message="Name cannot be null!")
	String name;
	@NotNull(message="City cannot be null!")
	String city;
	
	@CreatedDate
	Instant createdDate;
	@LastModifiedDate
	Instant modifiedDate;	
	
	
	
	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Employee() {
		
	}
	
	public Employee(int id, @NotNull(message = "salary cannot be null!") int salary,
			@NotNull(message = "Name cannot be null!") String name,
			@NotNull(message = "City cannot be null!") String city) {
		
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	

}
