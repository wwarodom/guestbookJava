package com.myapp.model;

public class Guestbook {

	private int id;
	private String name;
	private String message;
	
	public Guestbook(int id,String name, String message) {
		this.id = id;
		this.name = name;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
