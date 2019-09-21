package com.hk.discount_calculator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Activity extends AppCompatActivity {
ImageView rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        //starting rotate animation when start the apps
        rotate=(ImageView) findViewById(R.id.img_id);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clock_rotate);
        rotate.startAnimation(animation);
        //call delayed function activity
        splashed();
    }
    public void splashed(){
        //delayed some moments in welcome screen
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        },4000);
    }
}
