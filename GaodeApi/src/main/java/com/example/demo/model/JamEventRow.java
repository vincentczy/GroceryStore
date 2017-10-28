package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_JamEventRow")
public class JamEventRow {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long pkid;
	
	@Column
	Long eventId;
	
	@Column
	Date createTime;

	@Column
	Integer dirvingDir;
	
	@Column
	Integer eventJamDist;
	
	@Column
	Integer eventJamSpeed;
	
	@Column
	Integer length;
	
	@Column
	String linkId;
	
	@Column
	Integer pubRunStatus;
	
	@Column
	Integer reliability;
	
	@Column
	String roadName;
	
	@Column
	Integer roadType;
	
	@Column
	String sectionInfo;
	
	@Column
	Integer sectionNum;
	
	@Column
	Integer speed;
	
	@Column
	Integer state;
	
	@Column
	Integer travelTime;
	
	@Column
	String xy;
	
	@Column
	String xys;

	public Long getPkid() {
		return pkid;
	}

	public void setPkid(Long pkid) {
		this.pkid = pkid;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDrivingDir() {
		return dirvingDir;
	}

	public void setDrivingDir(Integer drivingDir) {
		this.dirvingDir = drivingDir;
	}

	public Integer getEventJamDist() {
		return eventJamDist;
	}

	public void setEventJamDist(Integer eventJamDist) {
		this.eventJamDist = eventJamDist;
	}

	public Integer getEventJamSpeed() {
		return eventJamSpeed;
	}

	public void setEventJamSpeed(Integer eventJamSpeed) {
		this.eventJamSpeed = eventJamSpeed;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Integer getPubRunStatus() {
		return pubRunStatus;
	}

	public void setPubRunStatus(Integer pubRunStatus) {
		this.pubRunStatus = pubRunStatus;
	}

	public Integer getRealiability() {
		return reliability;
	}

	public void setRealiability(Integer realiability) {
		this.reliability = realiability;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public Integer getRoadType() {
		return roadType;
	}

	public void setRoadType(Integer roadType) {
		this.roadType = roadType;
	}

	public String getSectionInfo() {
		return sectionInfo;
	}

	public void setSectionInfo(String sectionInfo) {
		this.sectionInfo = sectionInfo;
	}

	public Integer getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(Integer sectionNum) {
		this.sectionNum = sectionNum;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getXys() {
		return xys;
	}

	public void setXys(String xys) {
		this.xys = xys;
	}
	
}
