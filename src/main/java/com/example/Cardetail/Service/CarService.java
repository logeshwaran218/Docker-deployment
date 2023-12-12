package com.example.Cardetail.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Cardetail.DTO.userInsitesDto;
import com.example.Cardetail.Entity.Car;
import com.example.Cardetail.Entity.User;
import com.example.Cardetail.Entity.UserInsites;
import com.example.Cardetail.Repository.CarRepository;
import com.example.Cardetail.Repository.UserInsitesRepository;
import com.example.Cardetail.extension.CarNotFoundException;
import com.example.Cardetail.extension.CustomAccessDeniedException;
import com.example.Cardetail.filter.JwtAuthFilter;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CarService {
    private final CarRepository carRepository;
 
    
    @Autowired
    public UserInsitesRepository userInsitesRepository;
    @Autowired
    public JwtAuthFilter jwtAuthFilter;
    

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car,HttpServletRequest request) {
    	 User user=jwtAuthFilter.getUserId(request).get();
    	 car.setCreatedAt(LocalDateTime.now());
    	 car.editedBy(user.getId());
    	 
        return carRepository.save(car);
    }

    public Car getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new CarNotFoundException("Car not found with id: " + carId);
        }
    }
    public Car getCarByModel(String model) {
        Optional<Car> optionalCar = carRepository.findByModel(model);
        
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new CarNotFoundException("Car not found with model: " + model);
        }
    }
    public Car updateCar(Long id, Car updatedCar,HttpServletRequest request) {
        Optional<Car> optionalExistingCar = carRepository.findById(id);
        User user=jwtAuthFilter.getUserId(request).get();
        if(user.getId()==optionalExistingCar.get().geteditedBy() || user.getRoles().equals("ADMIN"))
        {
          if (optionalExistingCar.isPresent()) {
            Car existingCar = optionalExistingCar.get();
            existingCar.setBrand(updatedCar.getBrand());
            existingCar.setModel(updatedCar.getModel());
            existingCar.setYear(updatedCar.getYear());
            existingCar.setPrice(updatedCar.getPrice());
            UserInsites userInsites=new UserInsites();
            userInsites.setCarId(existingCar.getId());
            userInsites.seteditedBy(Long.valueOf(user.getId()));
            userInsitesRepository.save(userInsites);
            existingCar.setCreatedAt(LocalDateTime.now());
            return carRepository.save(existingCar);
        }
          else {
            throw new CarNotFoundException("Car not found with id: " + id);
        }
        }
        else 
        {
        	System.out.println("Error");
        	throw new CustomAccessDeniedException("Access will be denied");
        }
//        else
//        {
//        	throw new CustomAccessDeniedException("Access will be denied");
//        }
    }
    public List<Car> getCarsBetweenPrices(double min, double max) {
        List<Car> cars = carRepository.findByPriceBetween(min, max);

        if (cars.isEmpty()) {
        	throw new CarNotFoundException("Car not found in the price range between " + min + " and " + max + ".");
        }

        return cars;
    }

    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new CarNotFoundException("Car not found with id: " + id);
        }
    }
    


	public List<userInsitesDto> getupdatedDetails(HttpServletRequest request) {
		User user=jwtAuthFilter.getUserId(request).get();
		List<userInsitesDto> userInsitesDtos=new ArrayList<>();
		if(user.getRoles().equals("ADMIN"))
		{
		    userInsitesDtos= userInsitesRepository.findAlldetails();
		} else {
			userInsitesDtos=userInsitesRepository.findAllById(user.getId());
		}
		  return userInsitesDtos;
	}

	public List<Car> getCarsByUserIdandEditedBy(HttpServletRequest request) {
		User user=jwtAuthFilter.getUserId(request).get();
		List<Car> cardetail=new ArrayList<>();
		List<Car> cardetail1=carRepository.findAll();
		if(user.getRoles().equals("ADMIN"))
		{
		   cardetail=cardetail1;
		}
		else
		{
		      cardetail=cardetail1.stream()
		    		.filter(car->car.geteditedBy()==user.getId())
		    		.collect(Collectors.toList());    		
		}
			return cardetail;

		}
	
}