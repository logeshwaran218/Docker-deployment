package com.example.Cardetail.extension;
import com.example.Cardetail.extension.CarNotFoundException;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(message);
    }
}