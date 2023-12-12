package com.example.Cardetail.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.Cardetail.DTO.userInsitesDto;
import com.example.Cardetail.Entity.Car;
import com.example.Cardetail.Service.CarService;
//import com.example.Cardetail.Service.TokenExpiration;
import com.example.Cardetail.extension.CarNotFoundException;
//import com.example.Cardetail.extension.JwtTokenExpiredException;
import com.example.Cardetail.extension.CustomAccessDeniedException;
//import com.example.Cardetail.extension.ErrorResponse;
//import com.example.Cardetail.extension.TokenExpiredException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cardetails")
public class CarController {
    private final CarService carService;
    
//    @Autowired
//    public TokenExpiration tokenExpiration;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/welcome")
    public String welcome() {
    	return "Welcome to our car site";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') ")
    @PostMapping("/create")
    public Car createCar(@RequestBody Car car, HttpServletRequest request) throws Exception {
        try {
        	
//            String token = TokenExpiration.getToken(request);
                return carService.createCar(car, request);
            } 
            
         catch (Exception e) {
            throw new Exception("An error occurred while creating the car.");
        }
       
    }
    	
    	
    	
    
    

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') ")
    @GetMapping("/{id}")
    public  Car getCarById(@PathVariable Long id) {
        try {
//        	Car car=carService.getCarById(id);
            return carService.getCarById(id);
            
        }
    
   	 catch (CarNotFoundException e) {
   		throw new CarNotFoundException("Car not found with id"+id);
        }
    }
   
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/findByModel")
    public Car getCarByModel(@RequestParam String model) {
        try {
            return carService.getCarByModel(model);
        } 
      
   	 catch (CarNotFoundException e) {
            throw new CarNotFoundException("Car not found with model: " + model);
        }
    }
    
       @GetMapping("/by-price-range")
        public List<Car> getCarsByPriceRange(
                @RequestParam("min") double min,
                @RequestParam("max") double max) {
    	  
            return carService.getCarsBetweenPrices(min, max);}
    	  
        


     @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') ")
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id,@RequestBody Car updatedCar,HttpServletRequest request) {
        try {
            return carService.updateCar(id, updatedCar,request);
            
        }
       
   	 catch (CarNotFoundException e) {
            throw new CarNotFoundException("You have not access to edit this car id: " + id);
        }
    }
     
     @PreAuthorize("hasAuthority('ADMIN')")
     @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        try {
            carService.deleteCar(id);
            return "Car with id " + id + " deleted successfully.";
        }
       
        catch (CarNotFoundException e) {
            throw new CarNotFoundException("Car not found with id: " + id);
        }
    }
     @GetMapping("/all")
     public List<userInsitesDto> getupdatedetail(HttpServletRequest request) throws Exception
     {
    	 try {
    		   return carService.getupdatedDetails(request);
    		
    	 }
    	 
    	 catch(Exception e) {
    		 throw new Exception();
    	 }
     }
     @GetMapping("/editedBy")
     public List<Car> getCarsByUserIdandEditedBy(HttpServletRequest request){
    	
              return  carService.getCarsByUserIdandEditedBy(request);}
    	
     

    @ExceptionHandler(CarNotFoundException.class)
    public String handleCarNotFoundException(CarNotFoundException e) {
        return e.getMessage();
    }
//    @ExceptionHandler(CustomAccessDeniedException.class)
//	public ResponseEntity<Car> handleCustomAccessDeniedException(CustomAccessDeniedException ex,WebRequest request){
//		Car errorCar=new Car();
//		String errorMessage = null;
//		errorCar.setModel(errorMessage);
//		return handleExceptionInternal(ex,new HttpHeaders(),errorCar, HttpStatus.FORBIDDEN,request);
//		
//	}
    @ExceptionHandler(CustomAccessDeniedException.class)
    public String handleCustomAccessDeniedException(CustomAccessDeniedException ex) {
        return ex.getMessage();
    }
//	private ResponseEntity<Car> handleExceptionInternal(CustomAccessDeniedException ex, HttpHeaders httpHeaders,
//			Car errorCar, HttpStatus forbidden, WebRequest request) {
//		Car errorCar1=new Car();
//		String errorMessage = null;
//		errorCar.setModel(errorMessage);
//		return handleExceptionInternal(ex,new HttpHeaders(),errorCar1, HttpStatus.FORBIDDEN,request);
//		
//	}
//    @ExceptionHandler(TokenExpiredException.class)
//    public String handleTokenExpiredException(TokenExpiredException eq) {
//    	return eq.getMessage();
//    	
//    }
//    @ExceptionHandler(JwtTokenExpiredException.class)
//    public ResponseEntity<?> handleExpiredJwtException(JwtTokenExpiredException ew,WebRequest request){
//    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("jwt Expired");
//    }
//    
}