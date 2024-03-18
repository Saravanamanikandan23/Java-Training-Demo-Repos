package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {
	@Autowired
	LaptopDao ld;
	public String addLaptop(LaptopEntity lk) {
		return ld.addLaptop(lk);
	}
    public String upadteInfo(LaptopEntity lk) {
    	return ld.updateInfo(lk);
    }
    public String deleteInfo (Integer id) {
    	return ld.deleteInfo(id);
    }
    public List<LaptopEntity>getAll(){
    	return ld.getAll();
    }
    public List<LaptopEntity>getByName (String name){
    	return ld.getByName(name);
    }
    public List<String>findData(String color){
    	return ld.findData(color);
    }
    public List<Object>getAllInfo (){
    	return ld.getAllInfo();
    }
}
