package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.DetailSubject;

public class ParseDetailSubject {
	
	private static DetailSubject detailSubject = new DetailSubject();
	
	private static Document document;
	
	public static DetailSubject getDetailSubject(String url) {
		init(url);
		return detailSubject;
	}
	
	private static void init(String url){
		WebBody.initStudent(url);
		document = WebBody.getDocument();
		parseSubject();
	}
	
	private static void parseSubject(){
		Elements element = document.select("table").select("tr");
		if (element.select("td.tableBodyleft").hasText()) detailSubject = getDetailSubject(element.select("td.tableBodyleft"));
	}
	
	private static DetailSubject getDetailSubject(Elements elements){
		DetailSubject detailSubject = new DetailSubject();
		detailSubject.setStartTerm(elements.get(0).text());
		detailSubject.setId(elements.get(1).text());
		detailSubject.setName(elements.get(2).text());
		detailSubject.setEnglishName(elements.get(3).text());
		detailSubject.setScore(elements.get(4).text());
		detailSubject.setDepartment(elements.get(6).text());
		detailSubject.setPerview(elements.get(7).text());
		detailSubject.setStartSubjectWey(elements.get(8).text());
		detailSubject.setSummary(elements.get(9).text());
		return detailSubject; 
	}
	
	private static void parseBook(){
		
	}
}
