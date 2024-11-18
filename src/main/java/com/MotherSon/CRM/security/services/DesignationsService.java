package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.repository.DesignationsRepository;

@Service
public class DesignationsService {
	
	
	@Autowired
	private DesignationsRepository designationsRepository;
	
	
	public Designations addDesignations(Designations designations) 
	{
		Designations sdesign = designationsRepository.save(designations);
		return sdesign;
		
	}
	
	public List<Designations> getAllDesignations(){
		return designationsRepository.findAll();
	}
	
	public Optional<Designations> getDesignationsById(Long id){
		return designationsRepository.findById(id);
	}

}
