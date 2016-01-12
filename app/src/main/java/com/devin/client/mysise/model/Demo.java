package com.devin.client.mysise.model;

import com.devin.client.mysise.model.base.CookieType;
import com.devin.client.mysise.model.base.HandleURL;
import com.devin.client.mysise.model.base.StudentLogin;
import com.devin.client.mysise.model.bean.Account;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Demo {
	public static void main(String[] args) throws IOException{
		Account account = new Account();
		account.setUsername("1340124146");
		account.setPassword("tyntf");
		StudentLogin.Instantce(account);
		HandleURL.Instantce();
		
		Connection connection = Jsoup.connect("http://class.sise.com.cn:7001/sise/common/course_view.jsp?id=1255").cookies(CookieType.cookies);
		Response response = connection.execute();
		System.out.println(response.body());
		
		Document document = response.parse();
		
	}
}
