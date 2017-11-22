package com.hero.zhaoq.sessionlibdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hero.zhaoq.sessionlibdemo.MainActivity;
import com.hero.zhaoq.sessionlibdemo.R;

public class SplashActivity extends CheckPremissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.open_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

    }



}
