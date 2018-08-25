package com.springhibernate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhibernate.model.Employee;
import com.springhibernate.repository.EmployeeRepository;

import java.util.List;
@Service
public class EmployeeDAO {
	@Autowired
	EmployeeRepository employeeRepository;
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	public List<Employee> findAll(){
		return employeeRepository.findAll ();
	}
	public Employee findOne(Long empid) {
		return employeeRepository.findOne(empid);
	}
	public void delete(Employee emp) {
		 employeeRepository.delete(emp);
	}
}
