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

import com.MotherSon.CRM.models.MealsPackage;
import com.MotherSon.CRM.security.services.MealsPackageService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/mealspackage")
public class MealsPackageController {
	
	
	@Autowired
	private MealsPackageService mealspackageService;
	
	
	@GetMapping("/getall")
	public List<MealsPackage> getAllMealsPackage(){
		return mealspackageService.getAllMealsPackage();
		
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<MealsPackage> getMealsPackageById(@PathVariable Long id) {
		Optional<MealsPackage> mealsPackage = mealspackageService.getMealsPackageById(id);
		return mealsPackage.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
		
		
		
	
	
	@PostMapping("/create")
	public MealsPackage addMealsPackage(@RequestBody MealsPackage mealsPackage) {
		return this.mealspackageService.addMealsPackage(mealsPackage);
		
		
	}

}
