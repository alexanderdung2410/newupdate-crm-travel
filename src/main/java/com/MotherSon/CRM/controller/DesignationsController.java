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

import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.security.services.DesignationsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/designations")
public class DesignationsController {
	
	
	@Autowired
	private DesignationsService designationsService;
	
	
	
	@GetMapping("/getall")
	public List<Designations> getAllDesignations(){
		return designationsService.getAllDesignations();
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Designations> getDesignationsById(@PathVariable Long id)
	{
		Optional<Designations> designation = designationsService.getDesignationsById(id);
		return designation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/create")
	public Designations addDesignations(@RequestBody Designations designations) {
		Designations design = designationsService.addDesignations(designations);
		return design;
	}

}
