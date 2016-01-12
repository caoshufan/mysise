package com.devin.client.mysise.model.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.devin.client.mysise.model.bean.Account;
import com.devin.client.mysise.model.url.SiseURL;

public class StudentLogin {
	
	//key name
	private static String keyName;
	
	//key value
	private static String keyValue;
	
	
	//post data
    private static Map<String,String> postData = new HashMap<>();
    
	public static void Instantce(Account account){
		init(account);
		getUserCookie();
	}
	
	/**
	 * 
	 * @param account username and password
	 */
	private static void init(Account account){
		postData.put("username", account.getUsername());
		postData.put("password", account.getPassword());
		getKey();
		postData.put(keyName, keyValue);
	}

	private static void getKey(){
		Document doc;
		try {
			doc = Jsoup.connect(SiseURL.URL_GETKEY).get();
			Elements elements = doc.select("input");
			keyName = elements.get(0).attr("name");
			keyValue = elements.get(0).attr("value");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	//get cookie in static
	private static void getUserCookie(){
		Connection connection = Jsoup.connect(SiseURL.URL_LOGIN_STUDENT);
		Response response = null;
		try {
			response = connection.data(postData)
					.method(Method.POST)
					.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieType.cookies = response.cookies();
	}
}
