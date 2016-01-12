package com.devin.client.mysise.model.bean;

import java.util.List;

public class User {
	
	private String userid;
	
	private String name;
	
	private String professional;
	// teacher
	private String headTeacher;
	
	private String grade;
	// class
	private String administartiveClass;

	private String instructor;

	private String email;
	
	private String GPA;
	
	private List<Subject> subjects;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getHeadTeacher() {
		return headTeacher;
	}

	public void setHeadTeacher(String headTeacher) {
		this.headTeacher = headTeacher;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAdministartiveClass() {
		return administartiveClass;
	}

	public void setAdministartiveClass(String administartiveClass) {
		this.administartiveClass = administartiveClass;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGPA() {
		return GPA;
	}

	public void setGPA(String gPA) {
		GPA = gPA;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}


}
