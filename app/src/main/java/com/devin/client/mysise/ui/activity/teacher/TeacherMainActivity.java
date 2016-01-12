package com.devin.client.mysise.ui.activity.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.devin.client.mysise.R;
import com.devin.client.mysise.model.base.TeacherSise;
import com.devin.client.mysise.model.base.WebBody;
import com.devin.client.mysise.model.bean.Course;
import com.devin.client.mysise.model.bean.Courses;
import com.devin.client.mysise.model.url.SiseURL;
import com.devin.client.mysise.ui.adapter.CourseAdapter;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeacherMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int INIT_SPINNER = 1;

    private static final int INIT_RECYCLER = 2;

    private Map<String,String> options;

    private List<Course> courses = new ArrayList<>();

    private CourseAdapter courseAdapter;

    private String codeString;

    private String nameString;

    private String department;

    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.search)
    Button search;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case INIT_SPINNER:
                    initSpinner();
                    break;
                case INIT_RECYCLER:
                    resetRecyler();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        initView();
    }

    private void initView(){
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getOptionData();

        recycler.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(this,courses);

        recycler.setAdapter(courseAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewData();
            }
        });

    }


    private void getOptionData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    options = TeacherSise.getCourseOptions();
                    Message message = new Message();
                    message.what = INIT_SPINNER;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void resetRecyler(){
        courseAdapter.notifyDataSetChanged();
        Toast.makeText(this,"refresh",Toast.LENGTH_SHORT).show();
    }

    private void getNewData(){
        codeString = code.getText().toString();
        nameString = name.getText().toString();
        String key = (String)spinner.getSelectedItem();
        department = options.get(key);
        new Thread(new Runnable() {
            @Override
            public void run() {
                courses.clear();
                courses.addAll(TeacherSise.getCourses(codeString, nameString, department));
                Message message = new Message();
                message.what = INIT_RECYCLER;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void initSpinner(){
        List<String> option = new ArrayList<>(options.keySet());
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,option));
        getNewData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(TeacherMainActivity.this,CoursePlanActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
