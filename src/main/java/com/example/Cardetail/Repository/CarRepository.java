package com.example.Cardetail.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Cardetail.Entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	Optional<Car> findByModel(String model);
   
    List<Car> findByPriceBetween(double min, double max);

	
}