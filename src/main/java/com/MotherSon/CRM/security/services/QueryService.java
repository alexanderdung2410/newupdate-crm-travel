package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Query;
import com.MotherSon.CRM.repository.QueryRepository;

@Service
public class QueryService {
	
	
	@Autowired
	private QueryRepository queryRepository;
	
	
	
	public Optional<Query> getQueryById(Long id){
		return queryRepository.findById(id);
	}
	
	
	public List<Query> getAllQuery(){
		return queryRepository.findAll();
	}
	
	
	public Query addQuery(Query query) {
		return queryRepository.save(query);
	}
	
	
	public Query updateQuery(Query qu) {
		return queryRepository.save(qu);
	}
	
	
	public void deleteById(long id) {
		queryRepository.deleteById(id);
	}

	public Query findById(Long id) {
		return null;

}}