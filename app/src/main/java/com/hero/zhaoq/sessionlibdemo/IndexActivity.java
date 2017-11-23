package com.hero.zhaoq.sessionlibdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndexActivity extends CheckPremissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        findViewById(R.id.open_emotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                startActivity(new Intent(IndexActivity.this, EmotionsActivity.class));
            }
        });

        findViewById(R.id.open_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                startActivity(new Intent(IndexActivity.this, EmotionsActivity.class));
            }
        });

        findViewById(R.id.open_message_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                startActivity(new Intent(IndexActivity.this, EmotionsActivity.class));
            }
        });
    }



}
