package com.monsieurpipe.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monsieurpipe.vote.model.*;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	public List<Vote> findAll();
	
	public Vote findOneByName(String name);
}


