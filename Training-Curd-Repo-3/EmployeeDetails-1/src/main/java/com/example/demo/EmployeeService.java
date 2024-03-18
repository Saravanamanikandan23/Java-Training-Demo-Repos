package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	Employee saveEmployee(Employee employee) throws Exception;

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployeeById(long id);

	Employee updateEmployee(Employee updatedEmployee);

	void deleteEmployee(long id);
}
