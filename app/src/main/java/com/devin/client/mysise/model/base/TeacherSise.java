package com.devin.client.mysise.model.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.devin.client.mysise.model.bean.Course;
import com.devin.client.mysise.model.bean.CoursePlan;
import com.devin.client.mysise.model.url.SiseURL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by 书凡 on 2015-12-23.
 */
public class TeacherSise {

    private static Map<String,String> typeMap = new HashMap<>();

    private static Map<String,String> gradeMap = new HashMap<>();

    private static Map<String,String> specialityMap = new HashMap<>();

    public static Bitmap getRandImage() throws  Exception{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(SiseURL.URL_CODE);
        HttpResponse httpResponse = client.execute(post);

        CookieType.coockie = ((AbstractHttpClient)client).getCookieStore().getCookies().get(0).getValue();
        byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    public static void login(String user,String password,String code) throws Exception{
        Map<String,String> data = new HashMap<>();
        data.put("username",user);
        data.put("password",password);
        data.put("rand",code);
        data.put("JSESSIONID", CookieType.coockie);

        Connection connection = Jsoup.connect(SiseURL.URL_LOGIN_TEACHER);
        Connection.Response response = connection.data(data).method(Connection.Method.POST).execute();
        securityLogin(password);
    }

    private static void securityLogin(String password) throws Exception{
        Map<String, String> postDataSecurity = new HashMap<>();
        postDataSecurity.put("j_username", "guest");
        postDataSecurity.put("j_password", password);
        postDataSecurity.put("JSESSIONID", CookieType.coockie);

        Connection connection = Jsoup.connect(SiseURL.URL_SECURITY_LOGIN);
        Response response = connection.data(postDataSecurity).method(Connection.Method.POST).execute();
    }

    public static Map<String,String> getCourseOptions() throws Exception{
        WebBody.initTeacher(SiseURL.COURES_URL);
        Document document = WebBody.getDocument();
        Elements options = document.select("option");
        Map<String,String> op = new HashMap<>();
        for (Element option : options){
            op.put(option.text(), option.attr("value"));
        }
        return op;
    }

    public static ArrayList<Course> getCourses(String code,String name,String department){
        WebBody.initTeacherhasData(SiseURL.COURES_URL, code, name, department);
        Document document = WebBody.getDocument();
        ArrayList<Course> courses = new ArrayList<>();
        Elements courseOptions = document.select("div[align=center] tbody tr");
        for (Element courseOption : courseOptions){
            Elements courseItem = courseOption.select("td");
            Course course = new Course();
            course.setClassNO(courseItem.get(0).text());
            course.setClassName(courseItem.get(1).text());
            course.setCreadit(courseItem.get(2).text());
            course.setCheckType(courseItem.get(3).text());
            course.setStatus(courseItem.get(4).text());
            course.setCheck(courseItem.get(5).text());
            courses.add(course);
        }
        return courses;
    }

    public static void InitPlanSpinner(){
        WebBody.initSelectPlanData(SiseURL.URL_SEARCHER_TEACH_PLAN,"2","245","2010");
        Document document = WebBody.getDocument();

        System.out.println(document.body());

        Elements types = document.select("select[name=type] option");
        Elements grades = document.select("select[name=grade] option");
        Elements specialitys = document.select("select[name=specialityid] option");

        for (Element element : types)
            typeMap.put(element.text(),element.attr("value"));
        for (Element element : grades)
            gradeMap.put(element.text(),element.attr("value"));
        for (Element element : specialitys){
            specialityMap.put(element.text(),element.attr("value"));
            System.out.println(element.text() + element.attr("value"));
        }
        System.out.println(specialityMap.get("CI-计算机科学与技术(物联网技术)"));
    }

    public static Map<String, String> getType() {
        return typeMap;
    }

    public static Map<String, String> getGrade() {
        return gradeMap;
    }

    public static Map<String, String> getSpeciality() {
        return specialityMap;
    }

    public static ArrayList<CoursePlan> getCoursePlans(String type,String grade,String speciality){
        System.out.println(type + " " + speciality + " " + grade);
        WebBody.initSelectPlanData(SiseURL.URL_SEARCHER_TEACH_PLAN,type,speciality,grade);
        Document document = WebBody.getDocument();
        ArrayList<CoursePlan> coursePlans = new ArrayList<>();
        Elements teacherPlans = document.select("table[class=table] tbody tr");

        for (Element teacherPlan : teacherPlans){
            CoursePlan coursePlan = new CoursePlan();
            Elements coureses = teacherPlan.select("td");
            coursePlan.setId(coureses.get(1).text());
            coursePlan.setName(coureses.get(2).text());
            coursePlan.setScore(coureses.get(3).text());
            coursePlan.setCheck(coureses.get(4).text());
            coursePlans.add(coursePlan);
        }
        return coursePlans;
    }

}
