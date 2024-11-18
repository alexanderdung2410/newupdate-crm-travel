package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.repository.DepartmentsRepository;



@Service
public class DepartmentsService {
	
	
@Autowired
	
	private DepartmentsRepository departmentsRepository;
	
	
	
	public Optional<Departments> getDepartmentsById(Long id){
		return departmentsRepository.findById(id);
	}
	
	
	public List<Departments> getAllDepartments(){
		return departmentsRepository.findAll();
	}
	
	
	public Departments addDepartments(Departments departments)
	
	{
		
		Departments department = departmentsRepository.save(departments);
		return department;
	}
	

}



