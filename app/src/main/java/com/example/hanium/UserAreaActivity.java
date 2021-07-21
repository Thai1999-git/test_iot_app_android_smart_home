package com.example.hanium;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;


public class UserAreaActivity extends AppCompatActivity {
    String loginId,loginPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId", null);
        loginPwd = auto.getString("inputPwd", null);
        String name = auto.getString("inputName", null);
        String username = loginId;
        String mail = auto.getString("inputMail", null);

        setContentView(R.layout.activity_user_area);

        // xml
        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        TextView etUsername = (TextView) findViewById(R.id.etUsername);
        TextView etMail = (TextView) findViewById(R.id.etMail);

        // dau ra thong tin nguoi dung
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etMail.setText(mail);

    }


    //xu ly nut trang chu
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
            {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }


}

