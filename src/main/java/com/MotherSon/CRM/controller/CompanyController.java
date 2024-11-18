package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.security.services.CompanyService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/company")
public class CompanyController {
    
	@Autowired
	private CompanyService companyservice;
	
	@PostMapping("/create")
	public Company createcontroller(@RequestBody Company company)
	{
		    Company finaldatasave=companyservice.createcompanyser(company);
		     return finaldatasave;
		
	}
	
	@GetMapping("/getall")
	public List<Company> getallcompanycon()
	{
		
		List<Company>getallcompc=companyservice.getallcompanyser();
		return getallcompc;
		
	}
	
	@GetMapping("getbyid/{id}")
	public Optional<Company> getcompanybyidcon(@PathVariable Long id)
	{
		
		 Optional<Company> getcompanybyidc=companyservice.getcompanybyid(id);
		 
		return getcompanybyidc;
		
	}
	
	@PutMapping("updatebyid/{id}")
	public Company updatebyidcon(@RequestBody Company company,@PathVariable Long id)
	{
		     Company updatecombyid=companyservice.updatebyid(company, id);
		return updatecombyid;
		
	}
	
	  @DeleteMapping("deletebyid/{id}")
	    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
	        try {
	        	companyservice.deleteById(id);
	            return ResponseEntity.ok("Company with ID " + id + " deleted successfully.");
	        } catch (EntityNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	
	
	
	
	
	
	
}
