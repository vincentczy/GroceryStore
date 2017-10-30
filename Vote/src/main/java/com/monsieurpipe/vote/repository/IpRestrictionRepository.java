package com.monsieurpipe.vote.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monsieurpipe.vote.model.*;

public interface IpRestrictionRepository extends JpaRepository<IpRestriction, Long> {
	public List<IpRestriction> findAll();
	
	public List<IpRestriction> findByIpAndDateBetween(Long ip, Date start, Date end);
}


