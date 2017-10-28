package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_JamEvent")
public class JamEvent {

	// "customer_seq" is Oracle sequence name.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long pkid = 0l;

    @Column
    String city;
    @Column
    String dist;
    @Column
    String eventId;
    @Column
    Integer handleState;
    @Column
    Long id;
    @Column
    Integer date;
    @Column
    Integer day;
    @Column
    Integer hours;
    @Column
    Integer minutes;
    @Column
    Integer month;
    @Column
    Integer seconds;
    @Column
    Integer time;
    @Column
    Integer timzoneOffset;
    @Column
    Integer year;
    @Column
    Integer isNormal;
    @Column
    Integer jamDist;
    @Column
    Integer jamSpeed;
    @Column
    Integer longTime;
    @Column
    String province;
    @Column
    Integer pubRunStatus;
    @Column
    String roadName;
    @Column
    Integer roadType;
    @Column
    Integer state;
    @Column
    String xy;
	public Long getPkid() {
		return pkid;
	}
	public void setPkid(Long pkid) {
		this.pkid = pkid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Integer getHandleState() {
		return handleState;
	}
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getSeconds() {
		return seconds;
	}
	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getTimzoneOffset() {
		return timzoneOffset;
	}
	public void setTimzoneOffset(Integer timzoneOffset) {
		this.timzoneOffset = timzoneOffset;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getIsNormal() {
		return isNormal;
	}
	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}
	public Integer getJamDist() {
		return jamDist;
	}
	public void setJamDist(Integer jamDist) {
		this.jamDist = jamDist;
	}
	public Integer getJamSpeed() {
		return jamSpeed;
	}
	public void setJamSpeed(Integer jamSpeed) {
		this.jamSpeed = jamSpeed;
	}
	public Integer getLongTime() {
		return longTime;
	}
	public void setLongTime(Integer longTime) {
		this.longTime = longTime;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getPubRunStatus() {
		return pubRunStatus;
	}
	public void setPubRunStatus(Integer pubRunStatus) {
		this.pubRunStatus = pubRunStatus;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
    
}