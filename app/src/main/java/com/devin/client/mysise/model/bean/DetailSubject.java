package com.devin.client.mysise.model.bean;

import java.io.Serializable;

public class DetailSubject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startTerm;
	
	private String id;
	
	private String name;
	
	private String EnglishName;
	
	private String score;
	
	private String department;
	
	private String perview;
	
	private String startSubjectWey;
	
	private String summary;
	
	public String getStartTerm() {
		return startTerm;
	}
	public void setStartTerm(String startTerm) {
		this.startTerm = startTerm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglishName() {
		return EnglishName;
	}
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPerview() {
		return perview;
	}
	public void setPerview(String perview) {
		this.perview = perview;
	}
	public String getStartSubjectWey() {
		return startSubjectWey;
	}
	public void setStartSubjectWey(String startSubjectWey) {
		this.startSubjectWey = startSubjectWey;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
