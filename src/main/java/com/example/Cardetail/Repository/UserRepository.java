package com.example.Cardetail.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.Cardetail.Entity.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByName(String username);

	

}
