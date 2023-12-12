package com.example.Cardetail.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private int year;
	private double price;
	private int editedBy;
	private LocalDateTime createdAt;
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + year + ", price=" + price + "]";
	}
	public Car() {
		super();
	}
	public Car(Long id, String brand, String model, int year, double price,int editedBy) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
		this.editedBy=editedBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int geteditedBy() {
		return editedBy;
	}
	public void editedBy(int editedBy) {
		this.editedBy = editedBy;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}