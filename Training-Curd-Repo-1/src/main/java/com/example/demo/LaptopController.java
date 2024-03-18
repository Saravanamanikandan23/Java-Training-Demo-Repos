package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaptopController {
	@Autowired
	LaptopService ls;
	@PostMapping(value="add")
	
	public String addLaptop (@RequestBody LaptopEntity ll) {
		return ls.addLaptop(ll);
	}
    @PutMapping(value="update/{id}")
    
    public String updateInfo(@RequestBody LaptopEntity ll) {
    	return ls.upadteInfo(ll);
    }
    @DeleteMapping (value="/delete/{id}")
    
    public String  deleteInfo (@PathVariable Integer id) {
    	return ls.deleteInfo(id);
    	
    }
    @GetMapping(value="/getInfo")
    
    public List<LaptopEntity>getAll(){
    	return ls.getAll();
    }
    @GetMapping (value="select/{name}")
    
    public List<LaptopEntity>getByName(@PathVariable String name){
    	return ls.getByName (name);
    }
    @GetMapping(value="findData/{color}")
    
    public List<String>findData(@PathVariable String color){
    	return ls.findData( color);
    }
    @GetMapping (value="/info")
    
    public List<Object>getAllInfo(){
    	return ls.getAllInfo();
    }
    
    
}



