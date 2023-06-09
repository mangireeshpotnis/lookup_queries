package com.lookup_and_jpa.projections;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeWithoutSalary {
	
	@Value("#{target.id}")
	public int getId();
	@Value("#{target.name}")
	public String getName();
	@Value("#{target.city}")
	public String getCity();

}
