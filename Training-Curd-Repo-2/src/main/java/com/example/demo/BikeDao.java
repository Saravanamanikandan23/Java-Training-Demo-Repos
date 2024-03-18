package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDao {
	@Autowired
	BikeRepo br;
	
	public String addBike(BikeEntity be) {
		br.save(be);
		return "data created";
	}

}
