package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Address;
import com.revature.caliber.repository.AddressRepository;

@Service
public class AddressRepositoryRequestDispatcher {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address processSingleAddressRequest(JsonObject request) {
		Address result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			int addressId = request.get("addressId").getAsInt();
			result = addressRepository.findOne(addressId);
		}
		
		return result;
	}
	
	public List<Address> processListAddressRequest(JsonObject request) {
		List<Address> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = addressRepository.findAll();
		}
		
		return result;
	}
	
}
