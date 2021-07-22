package com.example.hanium;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DeviceChangeActivity extends AppCompatActivity {
    //String id, name, stat, aut, pin, max, min, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.device_changes_1);

//        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
//        id = auto.getString("inputId", null);
//        name = auto.getString("inputName", null);
//        stat = auto.getString("inputStatus", null);
//        pin = auto.getString("inputPinNumber", null);
//        max = auto.getString("inputMaxValue", null);
//        min = auto.getString("inputMinValue", null);
//        type = auto.getString("inputType", null);

        final CheckBox status = (CheckBox) findViewById(R.id.statDevi1);
        final CheckBox auto = (CheckBox) findViewById(R.id.autoDevi1);

        final TextView id = findViewById(R.id.idDevi1);
        final TextView name = findViewById(R.id.nameDevi1);
        //final TextView status = findViewById(R.id.statDevi1);
        //final TextView auto = findViewById(R.id.autoDevi1);
        final TextView pin_number = findViewById(R.id.pinDevi1);
        final TextView max_value = findViewById(R.id.maxDevi1);
        final TextView min_value = findViewById(R.id.minDevi1);
        final TextView type = findViewById(R.id.typeDevi1);

        final TextView headDevice1 = findViewById(R.id.headDevi1);
//        headDevice1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestQueue queue = Volley.newRequestQueue(DeviceChangeActivity.this);
//                String url = "http://ec2-13-125-196-246.ap-northeast-2.compute.amazonaws.com:8000/api/device/1/";
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        id.setText(response);
//                        name.setText(response);
//                        status.setText(response);
//                        auto.setText(response);
//                        pin_number.setText(response);
//                        max_value.setText(response);
//                        min_value.setText(response);
//                        type.setText(response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        id.setText("empty");
//                        name.setText("empty");
//                        //status.setText("empty");
//                        //auto.setText("empty");
//                        pin_number.setText("empty");
//                        max_value.setText("empty");
//                        min_value.setText("empty");
//                        type.setText("empty");
//                    }
//                });
//
//                queue.add(stringRequest);
//            }
//        });

        Thread t = new Thread(){
            @Override
            public void run(){
                while (!isInterrupted()){
                    try{
                        Thread.sleep(5000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                RequestQueue queue = Volley.newRequestQueue(DeviceChangeActivity.this);
                                String url = "http://ec2-13-125-196-246.ap-northeast-2.compute.amazonaws.com:8000/api/device/1/";
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        id.setText(response);
                                        name.setText(response);
                                        //status.setText(response);
                                        //auto.setText(response);
                                        pin_number.setText(response);
                                        max_value.setText(response);
                                        min_value.setText(response);
                                        type.setText(response);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        id.setText("empty");
                                        name.setText("empty");
                                        //status.setText("empty");
                                        //auto.setText("empty");
                                        pin_number.setText("empty");
                                        max_value.setText("empty");
                                        min_value.setText("empty");
                                        type.setText("empty");
                                    }
                                });

                                queue.add(stringRequest);
                            }
                        });
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        Button fill1 = (Button) findViewById(R.id.fill1);
        fill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceChangeActivity.this, FillInfo.class);
                DeviceChangeActivity.this.startActivity(intent);
            }
        });
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
