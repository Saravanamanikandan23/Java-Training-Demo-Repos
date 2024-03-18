package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaptopRepo extends JpaRepository<LaptopEntity,Integer>{
@Query(value ="select * from Laptop where brand like %:brand%",nativeQuery=true)

List<LaptopEntity>getByName(@Param("brand")String brand);

@Query(value="select color from Laptop",nativeQuery=true)
List<String>findData( );

@Query(value="select id,brand from Laptop",nativeQuery=true)
List<Object>getAllInfo();
}

