package com.devin.client.mysise.model.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Schedule;
import com.devin.client.mysise.model.url.Url;

public class ParseSchedular {
	
	private static Schedule schedule = new Schedule();
	
	private static Document document;
	
	public static Schedule getSchedule(){
		init();
		return schedule;
	}

	private static void init(){
		WebBody.initStudent(Url.studentSchedularURL);
		document = WebBody.getDocument();
		parseAllSchedular();
	}
	
	private static void parseAllSchedular(){
		Elements elements = document.select("tr[bgcolor=#FFFFFF]");
		for(Element e : elements){
			getWeekSchedular(e.select("td"));
		}
	}

	private static void getWeekSchedular(Elements elements){
		schedule.getTime().add(getTimeSchedular(elements));
		schedule.getOne().add(getOneSchedular(elements));
		schedule.getTwo().add(getTwoSchedular(elements));
		schedule.getThree().add(getThreeSchedular(elements));
		schedule.getFour().add(getFourSchedular(elements));
		schedule.getFive().add(getFiveSchedular(elements));
	}
	
	private static String getTimeSchedular(Elements elements){
		return elements.get(0).text();
	}
	
	private static String getOneSchedular(Elements elements){
		return elements.get(1).text();
	}
	
	private static String getTwoSchedular(Elements elements){
		return elements.get(2).text();
	}
	
	private static String getThreeSchedular(Elements elements){
		return elements.get(3).text();
	}
	
	private static String getFourSchedular(Elements elements){
		return elements.get(4).text();
	}
	
	private static String getFiveSchedular(Elements elements){
		return elements.get(5).text();
	}
	
}
