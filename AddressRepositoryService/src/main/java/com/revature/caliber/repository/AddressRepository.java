package com.revature.caliber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	Address findByAddressId(Integer addressId);
	
}
