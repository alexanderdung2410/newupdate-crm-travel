package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.security.services.DepartmentsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/departments")
public class DepartmentsController {
	
	

	@Autowired
	private DepartmentsService departmentsService;
	
	
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Departments> getDepartmentsById(@PathVariable Long id){
		Optional<Departments> depart = departmentsService.getDepartmentsById(id);
		return depart.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	
	@GetMapping("/getAll")
	public List<Departments> getAllDepartments(){
		return departmentsService.getAllDepartments();
	}
	
	
	
	@PostMapping("/create")
	public Departments addDepartments(@RequestBody Departments departments)
	{
		Departments depart = departmentsService.addDepartments(departments);
		return depart;
	}

}



