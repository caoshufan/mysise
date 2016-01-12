package com.devin.client.mysise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.devin.client.mysise.R;
import com.devin.client.mysise.ui.activity.student.StudentMainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WellcomeActivity extends AppCompatActivity {


    @Bind(R.id.welcome_bg)
    ImageView welcomeBg;

    Animation entrace;

    SharedPreferences sharedPreferences;
    String isTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        isTeacher = sharedPreferences.getString("isteacher", "");

        entrace = AnimationUtils.loadAnimation(this,R.anim.entrance);
        entrace.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO 检测是否有sharePreference,如果有就写入，没有就跳入login
                if(checkLoginedStudent()){
                    Intent intent = new Intent(WellcomeActivity.this,StudentMainActivity.class);
                    startActivity(intent);
                } else{
                    Intent intent = new Intent(WellcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
                overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        welcomeBg.startAnimation(entrace);
    }

    private boolean checkLoginedStudent(){
        return isTeacher.equals("no");
    }
}
