package com.devin.client.mysise.model.parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Subject;
import com.devin.client.mysise.model.bean.User;
import com.devin.client.mysise.model.url.Url;

public class ParseUser {
	
	private static User user = new User();
	
	private static Document document;
	
	public static User getUser(){
		init();
		return user;
	}
	
	private static void init(){
		WebBody.initStudent(Url.userInfoURL);
		document = WebBody.getDocument();
		parseUserInfo();
		parseSubjects();
		parseGPA();
	}
	
	

	private static void parseUserInfo(){
		List<String> list = getResultList("td[class=td_left]");
		setUserInfo(list);
	}


	private static void parseSubjects(){
		List<Subject> subjects = new ArrayList<>();
		subjects.addAll(parseCompulsorysSubjects());
		subjects.addAll(parseOptionalsSubjects());
		user.setSubjects(subjects);
	}
	
	private static void parseGPA(){
		Elements elements = document.select("td").select("div[align=left]").select("font[color=#FF0000]");
		user.setGPA(elements.get(4).text());
	}
	

	private static List<Subject> parseCompulsorysSubjects(){
		List<Subject> list = new ArrayList<>();
		Elements compulsorys = document.select("table[class=table]").first().select("tbody").select("tr[^class]");
		for(Element compulsory : compulsorys)
			list.add(getCompulsorysSubject(compulsory.select("td")));
		return list;
	}


	private static List<Subject> parseOptionalsSubjects() {
		List<Subject> list = new ArrayList<>();
		Elements optionals = document.select("table[class=table]").last().select("tbody").select("tr[^class]");
		for(Element optional : optionals)
			list.add(getOptionalsSubject(optional.select("td")));
		return list;
	}
	
	private static Subject getCompulsorysSubject(Elements elements){
		Subject subject = new Subject();
		subject.setId(elements.get(1).text());
		subject.setName(elements.get(2).text());
		subject.setTerm(elements.get(7).text());
		subject.setScore(elements.get(8).text());
		return subject;
	}
	
	

	private static Subject getOptionalsSubject(Elements elements){
		Subject subject = new Subject();
		subject.setId(elements.get(0).text());
		subject.setName(elements.get(1).text());
		subject.setTerm(elements.get(6).text());
		subject.setScore(elements.get(7).text());
		return subject;
	}
	

	private static List<String> getResultList(String CssQuery){
		Elements elements = document.select(CssQuery);
		List<String> list = new ArrayList<>();
		for(Element e : elements)
			if (e.hasText()) 
				list.add(e.text());
		return list;
	}

	private static void setUserInfo(List<String> list){
		user.setUserid(list.get(0));
		user.setName(list.get(1));
		user.setGrade(list.get(2));
		user.setProfessional(list.get(3));
		user.setEmail(list.get(5));
		user.setAdministartiveClass(list.get(6));
		user.setHeadTeacher(list.get(7));
		user.setInstructor(list.get(8));
	}
	
}
