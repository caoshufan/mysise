package com.devin.client.mysise.ui.activity.teacher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.base.TeacherSise;
import com.devin.client.mysise.model.bean.CoursePlan;
import com.devin.client.mysise.ui.activity.SwipeBackActivity;
import com.devin.client.mysise.ui.adapter.CoursePlanAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoursePlanActivity extends SwipeBackActivity {

    private static final int INIT_SPINNER_DATA = 1;

    private static final int SEARCH_PLAN = 2;

    private ArrayList<CoursePlan> coursePlans = new ArrayList<>();

    private CoursePlanAdapter coursePlanAdapter;

    private Map<String,String> typeMap;
    private Map<String,String> gradeMap;
    private Map<String,String> specialityMap;

    @Bind(R.id.type)
    Spinner type;
    @Bind(R.id.grade)
    Spinner grade;
    @Bind(R.id.speciality)
    Spinner speciality;
    @Bind(R.id.search)
    Button search;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Override
    public boolean canBack() {
        return true;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case INIT_SPINNER_DATA:
                    initSpinner();
                    break;
                case SEARCH_PLAN:
                    refresh();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_course_plan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        setTitle("课程计划表");

        recycler.setLayoutManager(new LinearLayoutManager(this));

        coursePlanAdapter = new CoursePlanAdapter(this,coursePlans);

        recycler.setAdapter(coursePlanAdapter);

        getSelectData();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

    }

    private void getSelectData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TeacherSise.InitPlanSpinner();
                Message message = new Message();
                message.what = INIT_SPINNER_DATA;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void initSpinner(){

        typeMap = TeacherSise.getType();
        gradeMap = TeacherSise.getGrade();
        specialityMap = TeacherSise.getSpeciality();

        List<String> types = new ArrayList<>(typeMap.keySet());
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types));
        List<String> grades = new ArrayList<>(gradeMap.keySet());
        grade.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, grades));
        List<String> specialitys = new ArrayList<>(specialityMap.keySet());
        speciality.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specialitys));

    }

    private void requestData(){

        final String typekey = (String)type.getSelectedItem();
        final String gradekey  = (String) grade.getSelectedItem();
        final String specialitykey = (String)speciality.getSelectedItem();
        final String typeString = typeMap.get(typekey);
        final String gradeString  = gradeMap.get(gradekey);
        final String specialityString = specialityMap.get(specialitykey);

        new Thread(new Runnable() {
            @Override
            public void run() {
                coursePlans.clear();
                coursePlans.addAll(TeacherSise.getCoursePlans(typeString, gradeString, specialityString));
                System.out.println(coursePlans.get(0).getName());
                Message message = new Message();
                message.what = SEARCH_PLAN;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void refresh(){
        coursePlanAdapter.notifyDataSetChanged();
        Toast.makeText(this,"refresh",Toast.LENGTH_SHORT).show();
    }
}
