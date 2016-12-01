package org.hibernate;

public class Student {
	private long id;
	private String name;
	
	public Student(long id, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
