package com.example.Cardetail.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserInsites {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Override
	public String toString() {
		return "UserInsites [id=" + id + ", carId=" + carId + ", userId=" + editedBy + "]";
	}

	public UserInsites() {
		super();
	}

	public UserInsites(Long id, Long carId, Long editedBy) {
		super();
		this.id = id;
		this.carId = carId;
		this.editedBy = editedBy;
	}

	private Long carId;

	private Long editedBy;
	

	public  Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long geteditedBy() {
		return editedBy;
	}

	public void seteditedBy(Long editedBy) {
		this.editedBy = editedBy;
	}
	

}

