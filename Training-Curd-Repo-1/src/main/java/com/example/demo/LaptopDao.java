package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopDao {
	@Autowired
	
	LaptopRepo lr;
	
	public String addLaptop(LaptopEntity le) {
		 lr.save(le);		
		return "Super Laptop";
	}
    public String updateInfo(LaptopEntity le) {
    	lr.save(le).getId();
    	return "data updated";
    }
    public String deleteInfo(Integer id) {
    	lr.deleteById(id);
    	return "data deleted";
    }
    public List<LaptopEntity>getAll(){
    	return lr.findAll();
    }
    public List<LaptopEntity>getByName (String name) {
    	List<LaptopEntity>li=lr.getByName(name);
    	return li;
    }
    public List<String>findData(String color){
    	List<String>li=lr.findData();
    	return li;
    }
    public List<Object>getAllInfo(){
    	List<Object>li=lr.getAllInfo();
    	return li;
    }
}



