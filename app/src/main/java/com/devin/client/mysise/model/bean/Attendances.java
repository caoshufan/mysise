package com.devin.client.mysise.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Attendances {
	
	private List<Attendance> attendances = new ArrayList<>();

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
	
}
