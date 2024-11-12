package com.MotherSon.CRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.security.services.HotelPriceService;

@RestController
@RequestMapping("/Motherson/crm/v1/hotelprice")
@CrossOrigin(origins = "*", maxAge = 3600)
	public class HotelPriceController {
		
		@Autowired
		private HotelPriceService hotelpriceService;
		
		
		//@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping("/create")
		public ResponseEntity<?> addHotelPrice(@RequestBody HotelPrice hotelprice) {
			 HotelPrice hotelprices =hotelpriceService.addHotelPrice(hotelprice);
          return new ResponseEntity<>("Hotelprice is created",HttpStatus.CREATED);
	}
}
