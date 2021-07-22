package com.example.hanium;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //dia chi ket noi web server
    private static final String LOGIN_REQUEST_URL = "http://ec2-13-125-196-246.ap-northeast-2.compute.amazonaws.com:8000/api/login/";
    private Map<String, String> params;

    //constructor
    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        //post
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        //luu cac thong so
        params.put("Username", username);
        params.put("Password", password);
    }

    //tham so
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
