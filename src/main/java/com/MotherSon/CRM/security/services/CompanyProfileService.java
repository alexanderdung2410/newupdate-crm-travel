package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.CompanyProfile;
import com.MotherSon.CRM.repository.CompanyProfileRepository;



@Service
public class CompanyProfileService {

	@Autowired
	private CompanyProfileRepository companyprofileRepository;
	
	
	public CompanyProfile addCompanyProfile(CompanyProfile companyProfile) {
		return companyprofileRepository.save(companyProfile);
	}
	
	
	public Optional<CompanyProfile> getCompanyProfileById(Long id){
		return companyprofileRepository.findById(id);
	}
	
	
	
	public List<CompanyProfile> getAllCompanyProfile() {
		return companyprofileRepository.findAll();
	}

}
