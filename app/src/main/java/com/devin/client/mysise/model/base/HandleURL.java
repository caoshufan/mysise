package com.devin.client.mysise.model.base;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.url.SiseURL;
import com.devin.client.mysise.model.url.Url;

public class HandleURL {
	
	private static final String ATTR_ONCLICK = "onclick";
	
	private static Elements elements = null;
	
	public static void Instantce(){
		try {
			getURL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getURL() throws IOException{
		Connection connection2 = Jsoup.connect(SiseURL.URL_MAIN).cookies(CookieType.cookies);
		Response response2 = connection2.execute();
		Document document = response2.parse();
		elements = document.select("table[bgcolor=#ebebeb]").select("td");
		initURL();
	}
	
	//handle URL
	private static void initURL(){
		initUserinfoURL();
		initStudentSchedularURL();
		initExamTimeURL();
		initAttendanceURL();
		initCommonResultURL();
		initEncouragePunishURL();
		initStudentStatusURL();
		initSelectClassCourseURL();
	}
	
	
	private static void initUserinfoURL(){
		Url.userInfoURL = parseURL(Url.originalUserInfoURLPosition, "S",0);	
	}
	
	private static void initStudentSchedularURL(){
		Url.studentSchedularURL = parseURL(Url.originalStudentSchedularURLPosition, "/",1);
	}
	
	private static void initExamTimeURL(){
		Url.examTimeURL = parseURL(Url.originalExamTimeURLPosition, "S",0);
	}
	
	private static void initAttendanceURL(){
		Url.attendanceURL = parseURL(Url.originalAttendanceURLPosition, "S",0);
	}
	
	private static void initCommonResultURL(){
		Url.commonResultURL = parseURL(Url.originalCommonResultURLPosition, "/",1);
	}
	
	private static void initEncouragePunishURL(){
		Url.encouragePunishURL = parseURL(Url.originalEncouragePunishURLPosition, "/",1);
	}
	
	private static void initStudentStatusURL(){
		Url.studentStatusURL = parseURL(Url.originalStudentStatusURLPosition, "S",0);
	}
	
	private static void initSelectClassCourseURL(){
		Url.selectClassCourseURL = parseURL(Url.originalSelectClassCoursePosition, "/", 1);
	}
	
	/**
	 * Handle URL
	 * @param position sign of position
	 * @param findIndex  find subString location
	 * @param add maybe substring font;
	 * @return complete URL
	 */
	private static String parseURL(int position,String findIndex,int add) {
		String temp = elements.get(position).attr(ATTR_ONCLICK);
		int index = temp.indexOf(findIndex);
		return SiseURL.URL + temp.substring(index+add, temp.length()-1);
	}
	
}
