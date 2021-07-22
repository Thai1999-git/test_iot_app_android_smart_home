package com.example.hanium;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        final TextView idDevi1 = findViewById(R.id.idDevi1);
        final TextView nameDevi1 = findViewById(R.id.nameDevi1);
        final TextView statDevi1 = findViewById(R.id.statDevi1);
        final TextView autoDevi1 = findViewById(R.id.autoDevi1);
        final TextView pinDevi1 = findViewById(R.id.pinDevi1);
        final TextView maxDevi1 = findViewById(R.id.maxDevi1);
        final TextView minDevi1 = findViewById(R.id.minDevi1);
        final TextView typeDevi1 = findViewById(R.id.typeDevi1);

        final Button headDevice1 = (Button) findViewById(R.id.headDevi1);
        headDevice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(DeviceChangeActivity.this);
                String url = "http://ec2-13-125-196-246.ap-northeast-2.compute.amazonaws.com:8000/api/device/1/";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        idDevi1.setText(response);
                        nameDevi1.setText(response);
                        statDevi1.setText(response);
                        autoDevi1.setText(response);
                        pinDevi1.setText(response);
                        maxDevi1.setText(response);
                        minDevi1.setText(response);
                        typeDevi1.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        idDevi1.setText("unknown");
                        nameDevi1.setText("unknown");
                        statDevi1.setText("unknown");
                        autoDevi1.setText("unknown");
                        pinDevi1.setText("unknown");
                        maxDevi1.setText("unknown");
                        minDevi1.setText("unknown");
                        typeDevi1.setText("unknown");
                    }
                });

                queue.add(stringRequest);
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
