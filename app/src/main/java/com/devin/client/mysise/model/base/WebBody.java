package com.devin.client.mysise.model.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection.Response;

public class WebBody {
	
	private static Document document;
	
	public static void initStudent(String url){
		Connection connection = Jsoup.connect(url).cookies(CookieType.cookies);
		Response response;
		try {
			response = connection.execute();
			document = response.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initTeacherhasData(String url,String code,String name,String department){
		Map<String,String> data = new HashMap<>();
		data.put("nameMain",name);
		data.put("codeMain",code);
		data.put("departmentCode",department);
		Connection connection = Jsoup.connect(url).cookie("JSESSIONID", CookieType.coockie).data(data);
		Response response;
		try{
			response = connection.execute();
			document = response.parse();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void initTeacher(String url){
		Connection connection = Jsoup.connect(url).cookie("JSESSIONID", CookieType.coockie);
		Response response;
		try {
			response = connection.execute();
			document = response.parse();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void initSelectPlanData(String url,String type,String specialityid,String grade){
		Map<String,String> data = new HashMap<>();
		data.put("type",type);
		data.put("specialityid",specialityid);
		data.put("grade",grade);
		Connection connection = Jsoup.connect(url).cookie("JSESSIONID",CookieType.coockie).data(data).method(Connection.Method.POST);
		Response response;
		try{
			response = connection.execute();
			document = response.parse();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
	public static Document getDocument(){
		return document;
	}
	
}
