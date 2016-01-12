package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Encourage;
import com.devin.client.mysise.model.bean.Encourages;
import com.devin.client.mysise.model.url.Url;

public class ParseEncouragePunish {
	
	private static Encourages encourages = new Encourages();
	
	private static Document document;
	
	public static Encourages getEncourages(){
		init();
		return encourages;
	}
	
	private static void init(){
		WebBody.initStudent(Url.encouragePunishURL);
		document = WebBody.getDocument();
		parseAllEncouragePunish();
	}
	
	private static void parseAllEncouragePunish(){
		Elements elements = document.select("table").select("tr");
		for(Element e : elements)
			if (e.select("td[class=tablebody]").hasText()) 
				encourages.getEncourages().add(getEncouragePunish(e.select("td.tablebody")));
	}
	
	private static Encourage getEncouragePunish(Elements elements){
		Encourage encourage = new Encourage();
		encourage.setYear(elements.get(0).text());
		encourage.setTerm(elements.get(1).text());
		encourage.setLevel(elements.get(2).text());
		encourage.setReason(elements.get(3).text());
		encourage.setDepartment(elements.get(4).text());
		encourage.setDate(elements.get(5).text());
		return encourage;
	}
}
