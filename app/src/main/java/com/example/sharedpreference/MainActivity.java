package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView img,storage;
    Animation center,t,top;
    TextView logo_intro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

         img=findViewById(R.id.img_logo);
         storage=findViewById(R.id.img_storagelogo);
         logo_intro=findViewById(R.id.tv_name);
        t=AnimationUtils.loadAnimation(this,R.anim.translate);
        top=AnimationUtils.loadAnimation(this,R.anim.toptranslation);
        storage.setAnimation(top);
         center= AnimationUtils.loadAnimation(this,R.anim.animation);
         logo_intro.setAnimation(t);
         img.setAnimation(center);

         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, OneTimeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }


}