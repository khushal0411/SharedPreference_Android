package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OneTimeScreenActivity extends AppCompatActivity {

    ImageView nextbtn;
    SharedPreferences sharedPreferences;
    Boolean firsttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_one_time_screen);
        nextbtn=findViewById(R.id.img_btnnext);
        sharedPreferences= getSharedPreferences(Constants.MYPREF,MODE_PRIVATE);

        firsttime=sharedPreferences.getBoolean(Constants.ONETIME,true);

            if(firsttime){

                nextbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        firsttime = false;
                        editor.putBoolean(Constants.ONETIME,firsttime);
                        editor.apply();

                        Intent intent = new Intent(OneTimeScreenActivity.this, DrawerActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }

            else {

                Intent intent = new Intent(OneTimeScreenActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();

            }
    }
}