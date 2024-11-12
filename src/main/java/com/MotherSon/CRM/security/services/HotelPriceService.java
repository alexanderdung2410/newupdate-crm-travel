package com.MotherSon.CRM.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.repository.HotelPriceRepository;

@Service
	public class HotelPriceService {
		
		
		@Autowired
	    private HotelPriceRepository hotelpriceRepository;
		


		public HotelPrice addHotelPrice(HotelPrice hotelprice) {
			return hotelpriceRepository.save(hotelprice);
		}
	}

