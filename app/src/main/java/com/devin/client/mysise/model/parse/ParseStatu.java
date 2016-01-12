package com.devin.client.mysise.model.parse;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Statu;
import com.devin.client.mysise.model.bean.Status;
import com.devin.client.mysise.model.url.Url;

public class ParseStatu {
	
	private static Status status = new Status();
	
	private static Document document;
	
	public static Status getStatus(){
		init();
		return status;
	}
	
	private static void init(){
		WebBody.initStudent(Url.studentStatusURL);
		document = WebBody.getDocument();
		parseAllStatu();
	}
	
	private static void parseAllStatu(){
		Elements elements = document.select("table.table").select("tr[^class]");
		for(Element element : elements)
			status.getStatus().add(getStatu(element.select("td")));
	}
	
	private static Statu getStatu(Elements elements){
		Statu statu = new Statu();
		statu.setDate(elements.get(0).text());
		statu.setDay(elements.get(3).text());
		statu.setTime(elements.get(4).text());
		statu.setReason(elements.get(6).text());
		return statu;
	}
	
}
