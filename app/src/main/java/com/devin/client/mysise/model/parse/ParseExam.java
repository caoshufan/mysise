package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Exam;
import com.devin.client.mysise.model.bean.Exams;
import com.devin.client.mysise.model.url.Url;

public class ParseExam {
	
	private static Exams exams = new Exams();
	
	private static Document document;
	
	public static Exams getExams(){
		init();
		return exams;
	}
	
	private static void init(){
		WebBody.initStudent(Url.examTimeURL);
		document = WebBody.getDocument();
		parseAllExam();
	}
	
	private static void parseAllExam(){
		Elements elements = document.select("table.table").select("tr[^class]");
		for(Element element : elements)
			exams.getExams().add(getExam(element.select("td")));
	}
	
	private static Exam getExam(Elements elements){
		Exam exam = new Exam();
		exam.setName(elements.get(1).text());
		exam.setDate(elements.get(2).text() + " " + elements.get(3).text());
		exam.setExamroom(elements.get(4).text());
		exam.setSeat(elements.get(6).text());
		exam.setState(elements.get(7).text());
		return exam;
	}
	
}
