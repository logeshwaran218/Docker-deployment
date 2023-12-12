package com.example.Cardetail.Repository;

import org.springframework.stereotype.Repository;

import com.example.Cardetail.DTO.userInsitesDto;
import com.example.Cardetail.Entity.UserInsites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserInsitesRepository extends JpaRepository<UserInsites,Integer>{
	@Query("select new com.example.Cardetail.DTO.userInsitesDto(c.brand, c.model, u.name, u.roles) "
			+ "from UserInsites ui left join Car c on c.id = ui.carId "
			+ "left join User u on u.id = ui.editedBy")
	public List<userInsitesDto> findAlldetails();
	
	@Query("select new com.example.Cardetail.DTO.userInsitesDto(c.brand, c.model, u.name, u.roles) "
			+ "from UserInsites ui left join Car c on c.id = ui.carId "
			+ "left join User u on u.id = ui.editedBy where u.id = ?1 ")
	public List<userInsitesDto> findAllById(int id);
}
