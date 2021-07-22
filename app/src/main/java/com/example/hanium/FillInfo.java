package com.example.hanium;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class FillInfo extends AppCompatActivity {
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_info);

        Button confirm1 = (Button) findViewById(R.id.confirm1);

        final EditText name = (EditText) findViewById(R.id.nameDevi1);
        final EditText max_value = (EditText) findViewById(R.id.maxDevi1);
        final EditText min_value = (EditText) findViewById(R.id.minDevi1);
        final EditText type = (EditText) findViewById(R.id.typeDevi1);

        builder = new AlertDialog.Builder(FillInfo.this);

        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name, Max_value, Min_value, Type;
                Name = name.getText().toString();
                Max_value = max_value.getText().toString();
                Min_value = min_value.getText().toString();
                Type = type.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(FillInfo.this);
                String url = "http://ec2-13-125-196-246.ap-northeast-2.compute.amazonaws.com:8000/api/device/1/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText("");
                                max_value.setText("");
                                min_value.setText("");
                                type.setText("");
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FillInfo.this, "Error", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", Name);
                        params.put("max_value", Max_value);
                        params.put("min_value", Min_value);
                        params.put("type", Type);
                        return params;
                    }
                };
                //queue.add(stringRequest);

                MySingleton.getInstance(FillInfo.this).addTorequestque(stringRequest);

                Intent intent = new Intent(FillInfo.this, DeviceChangeActivity.class);
                FillInfo.this.startActivity(intent);

            }
        });
    }
}
