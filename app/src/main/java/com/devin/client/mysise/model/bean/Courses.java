package com.devin.client.mysise.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 书凡 on 2015-12-23.
 */
public class Courses {
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
