package com.example.hanium;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //dia chi PHP ket noi may chu
    private static final String LOGIN_REQUEST_URL = IpPath.WEBIP + "/login.php";
    // string,string
    private Map<String, String> params;

    //constructor
    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        //post
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        //luu cac thong so
        params.put("username", username);
        params.put("password", password);
    }

    //tham so
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
