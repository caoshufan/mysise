package com.devin.client.mysise.model.bean;

/**
 * Created by 书凡 on 2015-12-24.
 */
public class CoursePlan {
    //课程名称
    private String name;
    //学分
    private String score;
    //课程代码
    private String id;
    //考核类型
    private String check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
