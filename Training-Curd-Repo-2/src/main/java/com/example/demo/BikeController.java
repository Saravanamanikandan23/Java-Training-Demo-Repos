package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BikeController {
	@Autowired
	BikeService bs;
	@PostMapping(value="/add")
	
	public String addBike (@RequestBody BikeEntity be) {
		return bs.addBike(be);
	}
}
