package com.example.hanium;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DeviceChangeActivity extends AppCompatActivity {
    String id, name, stat, aut, pin, max, min, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        id = auto.getString("inputId", null);
        name = auto.getString("inputName", null);
        stat = auto.getString("inputStatus", null);
        pin = auto.getString("inputPinNumber", null);
        max = auto.getString("inputMaxValue", null);
        min = auto.getString("inputMinValue", null);
        type = auto.getString("inputType", null);

        setContentView(R.layout.device_changes_1);

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
