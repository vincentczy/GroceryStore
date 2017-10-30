package com.monsieurpipe.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "T_Votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0l;

    @Column(unique = true)
    String name;
    @Column
    String type;
    @Column
    String logo;
    @Column
    Integer votes;
    @Transient
    private Boolean voted;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getVotes() {
		return votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	public Boolean getVoted() {
		return voted;
	}
	public void setVoted(Boolean voted) {
		this.voted = voted;
	}
    
}