package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.DetailSubject;
import com.devin.client.mysise.model.bean.OpenSubject;
import com.devin.client.mysise.model.bean.OpenSubjects;
import com.devin.client.mysise.model.url.SiseURL;
import com.devin.client.mysise.model.url.Url;

public class ParseOpenSubject {
	
	private static OpenSubjects openSubjects = new OpenSubjects();
	
	private static Document document;
	
	public static OpenSubjects getOpenSubjects(){
		init();
		return openSubjects;
	}
	
	private static void init(){
		WebBody.initStudent(Url.selectClassCourseURL);
		document = WebBody.getDocument();
		parseAllOpenSubject();
	}
	
	private static void parseAllOpenSubject(){
		Elements elements = document.select("table").select("tr");
		for(Element element : elements){
			if(element.select("td[class=tablebody]").hasText())
				openSubjects.getSubjects().add(getOpenSubject(element.select("td[class=tablebody]")));
		}
	}
	
	private static OpenSubject getOpenSubject(Elements elements){
		OpenSubject openSubject = new OpenSubject();
		String sub = elements.select("a").attr("href");
		openSubject.setUrl(SiseURL.URL + sub.substring(1, sub.length()));
		openSubject.setId(elements.get(0).text());
		openSubject.setName(elements.get(1).text());
		openSubject.setScore(elements.get(2).text());
		openSubject.setExammode(elements.get(3).text());
		openSubject.setPreview(elements.get(4).text());
		return openSubject;
	}
	
	//android onItemClick
	public static DetailSubject getDetailSubject(int position){
		String url = openSubjects.getSubjects().get(position).getUrl();
		return ParseDetailSubject.getDetailSubject(url);
	}
}
