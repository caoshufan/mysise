package com.devin.client.mysise.model.bean;

public class Exam {
	
	private String name;

	private String date;

	private String examroom;
	//your exam seat
	private String seat;
	
	private String state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExamroom() {
		return examroom;
	}
	public void setExamroom(String examroom) {
		this.examroom = examroom;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
