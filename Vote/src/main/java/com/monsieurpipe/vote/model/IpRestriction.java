package com.monsieurpipe.vote.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_IpRestriction")
public class IpRestriction {

	// "customer_seq" is Oracle sequence name.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0l;

    @Column
    Date date;
    @Column
    Long ip;
    @Column
    String votes;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getIp() {
		return ip;
	}
	public void setIp(Long ip) {
		this.ip = ip;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}

    
}