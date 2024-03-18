package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Laptop")
public class LaptopEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int price;
private String brand;
private String color;
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

	
}




