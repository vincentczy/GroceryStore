package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  
import com.example.demo.model.*;

public interface JamEventRepository extends JpaRepository<JamEvent, Long> {
	public List<JamEvent> findByCity(String city);
}


