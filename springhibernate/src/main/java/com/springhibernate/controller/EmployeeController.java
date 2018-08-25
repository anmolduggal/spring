package com.springhibernate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springhibernate.dao.EmployeeDAO;
import com.springhibernate.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
@Autowired
EmployeeDAO employeeDao;

@PostMapping("/employee")
public Employee createEmployee(@Valid @RequestBody Employee emp)
{
	return employeeDao.save(emp);
}
@GetMapping("/employee")
public List<Employee> getAllEmployees(){
	return employeeDao.findAll();
}
@GetMapping("/note/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
	Employee emp=employeeDao.findOne(empid);
	if(emp==null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(emp);
}
@PutMapping("/employee/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid ,@Valid @RequestBody Employee empDetails){
	Employee emp=employeeDao.findOne(empid);
	if(emp==null) {
		return ResponseEntity.notFound().build();
	}
	emp.setName(empDetails.getName());
	emp.setDesignation(empDetails.getDesignation());
	emp.setExpertise(empDetails.getExpertise());
	Employee updateEmployee=employeeDao.save(emp);
	return ResponseEntity.ok().body(updateEmployee);
	
}
@DeleteMapping("/employee/{id}")
public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
	Employee emp=employeeDao.findOne(empid);
	if(emp==null) {
		return ResponseEntity.notFound().build();
	}
	employeeDao.delete(emp);
	return ResponseEntity.ok().build();
}

}

