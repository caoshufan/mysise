package com.devin.client.mysise.model.bean;

public class Book {
	
	private String ISBN;
	
	private String name;
	
	private String author;
	
	private String publics;
	
	private String money;
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublics() {
		return publics;
	}
	public void setPublics(String publics) {
		this.publics = publics;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
}
