package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.MealsPackage;
import com.MotherSon.CRM.repository.MealsPackageRepository;

@Service
public class MealsPackageService {
	
	
	@Autowired
	private MealsPackageRepository mealspackageRepository;
	
	
	public List<MealsPackage> getAllMealsPackage(){
		return mealspackageRepository.findAll();
		
	}
	
	
	public Optional<MealsPackage> getMealsPackageById(Long id){
		return mealspackageRepository.findById(id);
	}
	
	
	public MealsPackage addMealsPackage(MealsPackage mealsPackage) {
		return mealspackageRepository.save(mealsPackage);
	}

}
