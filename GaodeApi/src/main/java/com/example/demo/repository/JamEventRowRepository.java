package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  
import com.example.demo.model.*;

public interface JamEventRowRepository extends JpaRepository<JamEventRow, Long> {
	public List<JamEvent> findByEventId(Long eventId);
}


