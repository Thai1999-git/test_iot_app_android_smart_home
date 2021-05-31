package com.example.hanium;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //username va password
    String loginId, loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UN va PW luu tru
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        // Response received from the server
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    //thanh cong dang nhap
                    int success = jsonResponse.getInt("success");

                    //khi thanh cong
                    if (success == 1) {

                        //nhan thong tin khi dang nhap thanh cong
                        String name = jsonResponse.getString("name");
                        String username = jsonResponse.getString("username");
                        String password = jsonResponse.getString("password");
                        int age = jsonResponse.getInt("age");

                        SharedPreferences auto = getSharedPreferences("auto",Activity.MODE_PRIVATE);
                        SharedPreferences.Editor autoLogin = auto.edit();
                        autoLogin.putString("inputId",username);
                        autoLogin.putString("inputPwd",password);
                        autoLogin.putInt("inputAge",age);
                        autoLogin.putString("inputName",name);
                        autoLogin.commit();

                        //thong tin dang nhap trong MainActivity
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();
                    }

                    //mat khau sai
                    else if(success == 2){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed.")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    //username khong ton tai
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed.")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                }
                // loi truy cap may chu
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Login error")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    e.printStackTrace();
                }
            }
        };


        setContentView(R.layout.activity_login);

        //nut quay lai
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // activity
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);

        //nut dang ky!
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, com.example.hanium.RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


        //nut dang nhap
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // dien un, pw
                com.example.hanium.LoginRequest loginRequest = new com.example.hanium.LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    //an quay lai
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }


    // nut quay lai dau menu
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
            {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
