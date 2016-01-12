package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Attendance;
import com.devin.client.mysise.model.bean.Attendances;
import com.devin.client.mysise.model.url.Url;

public class ParseAttendence {
	
	private static Attendances attendances = new Attendances();
	
	private static Document document;
	
	public static Attendances getAttendances(){
		init();
		return attendances;
	}
	
	private static void init(){
		WebBody.initStudent(Url.attendanceURL);
		document = WebBody.getDocument();
		Elements elements = document.select("table.table").select("tr[^class]");
		for(Element e : elements){
			attendances.getAttendances().add(getAttendance(e.select("td")));
		}
	}
	
	private static Attendance getAttendance(Elements elements){
		Attendance attendance = new Attendance();
		attendance.setName(elements.get(1).text());
		attendance.setStatu(elements.get(2).text());
		return attendance;
	}
	
}
