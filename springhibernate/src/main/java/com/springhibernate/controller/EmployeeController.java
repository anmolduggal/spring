package com.springhibernate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springhibernate.dao.EmployeeDAO;
import com.springhibernate.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
@Autowired
private EmployeeDAO employeeDao;

@RequestMapping(value = "/employee",method = RequestMethod.POST)
public Employee createEmployee(@Valid @RequestBody Employee emp)
{
	return employeeDao.save(emp);
}
@RequestMapping(value="/employee",method=RequestMethod.GET)
public List<Employee> getAllEmployees(){
	return employeeDao.findAll();
}
@RequestMapping(value="/note/{id}",method=RequestMethod.GET)
public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
	Employee emp=employeeDao.findOne(empid);
	if(emp==null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(emp);
}
@RequestMapping(value="/employee/{id}",method=RequestMethod.PUT)
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
@RequestMapping(value="/employee/{id}",method=RequestMethod.DELETE)
public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
	Employee emp=employeeDao.findOne(empid);
	if(emp==null) {
		return ResponseEntity.notFound().build();
	}
	employeeDao.delete(emp);
	return ResponseEntity.ok().build();
}

}

