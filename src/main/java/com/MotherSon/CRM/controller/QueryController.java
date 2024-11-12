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

import com.MotherSon.CRM.models.Query;
import com.MotherSon.CRM.security.services.QueryService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/query")
public class QueryController {
	
	
	
	@Autowired
	private QueryService queryService;
	
	
	
	@GetMapping("/getall")
	public List<Query> getAllQuery(){
		return queryService.getAllQuery();
	}
	
	
	@GetMapping("/getby/{id}")
	public ResponseEntity<Query> getQueryById(@PathVariable Long id){
		Optional<Query> query = queryService.getQueryById(id);
		return (ResponseEntity<Query>) query.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/create")
	public Query addQuery(@RequestBody Query query) {
		return this.queryService.addQuery(query);
	}
	
	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<Query> updateQuery(@PathVariable Long id , @RequestBody Query query){
		if(query != null)
		{
			Query qu = new Query();
			
			qu.setId(id);
			
			qu.setProposalId(query.getProposalId());
			qu.setRequirementType(query.getRequirementType());
			qu.setTravelDate(query.getTravelDate());
			qu.setDays(query.getDays());
			qu.setNights(query.getNights());
			
			qu.setTotalTravellers(query.getTotalTravellers());
			qu.setAdults(query.getAdults());
			qu.setKids(query.getKids());
			qu.setInfants(query.getInfants());
			
			qu.setSalutation(query.getSalutation());
			qu.setFname(query.getFname());
			qu.setLname(query.getLname());
			qu.setEmailId(query.getEmailId());
			
			qu.setContactNo(query.getContactNo());
			qu.setLeadSource(query.getLeadSource());
			qu.setFoodPreferences(query.getFoodPreferences());
			qu.setBasicCost(query.getBasicCost());
			
			qu.setGst(query.getGst());
			qu.setTotalCost(query.getTotalCost());
			qu.setQuery_Date(query.getQuery_Date());
			qu.setQueryType(query.getQueryType());
			
			qu.setQueryCreatedFrom(query.getQueryCreatedFrom());
			qu.setQueryAssigned(query.getQueryAssigned());
			qu.setEmailStatus(query.isEmailStatus());
			qu.setLeadStatus(query.isLeadStatus());
			qu.setLastUpdated_Date(query.getLastUpdated_Date());
			
			
		
		
			queryService.updateQuery(qu);
		return ResponseEntity.ok(qu);
	}

	else
	{
		return ResponseEntity.notFound().build();
	}
	}
	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Query> deleteQuery(@PathVariable Long id){
		
		try
		{
			queryService.findById(id);
			queryService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
	}
	}