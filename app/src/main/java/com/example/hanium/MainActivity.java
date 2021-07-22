package com.example.hanium;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // username va password khi dang nhap
    String loginId, loginPwd;
    boolean loginSuccess;
    Response.Listener<String> responseListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginSuccess = false;
        // Nav_drawer thiet lap doi tuong
        //----------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    //-----------------------------------------------------------------------------------------------

        // SharedPrefernces doi tuong nhan noi dung!!
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        // Response received from the server
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int success = jsonResponse.getInt("success");
                    SharedPreferences auto = getSharedPreferences("auto",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLogin = auto.edit();

                    //dang nhap thanh cong
                    if (success == 1) {
                        String name = jsonResponse.getString("name");
                        String username = jsonResponse.getString("username");
                        String password = jsonResponse.getString("password");
                        String mail = jsonResponse.getString("mail");
                        loginSuccess = true;
                        //luu tru thong tin ca nhan hoac cap nhat,
                        autoLogin.putString("inputId",username);
                        autoLogin.putString("inputPwd",password);
                        autoLogin.putString("inputMail",mail);
                        autoLogin.putString("inputName",name);
                        autoLogin.commit();

                        // them thong tin dang nhap
                        // hien thi thong tin ca nhan trong TextView
                        TextView loginUsername = (TextView)findViewById(R.id.loginUsername);
                        loginUsername.setText(username);
                        TextView loginName = (TextView)findViewById(R.id.loginName);
                        loginName.setText(name);

                        // dang nhap thanh cong Nav_drawer thay "đăng nhập" thanh "đăng xuất"
                        NavigationView navigationView = findViewById(R.id.nav_view);
                        navigationView.getMenu().findItem(R.id.nav_login).setTitle("Đăng xuất");

                        // Nav_drawer thay hinh anh tieu de
                        ImageView loginImage = (ImageView)findViewById(R.id.loginImage);
                        loginImage.setImageResource(R.drawable.bkhn);
                    }
                    //mat khau khong chinh xac
                    else if(success == 2){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Login Failed (wrong password).")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                        //mat khau ve NULL
                        autoLogin.putString("inputPwd",null);
                        autoLogin.commit();
                        loginPwd = null;
                    }
                    //username sai
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Login Failed (wrong username).")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                        //dua ve null
                        autoLogin.putString("inputId",null);
                        autoLogin.putString("inputPwd",null);
                        autoLogin.commit();
                        loginId = null;
                        loginPwd = null;
                    }

                }
                //loi dang nhap
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Login error")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    e.printStackTrace();
                }
            }
        };

        // luu dang nhap
        if(loginPwd != null && loginId != null) {
            com.example.hanium.LoginRequest loginRequest = new com.example.hanium.LoginRequest(loginId, loginPwd, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);
            Toast.makeText(MainActivity.this,"đăng nhập username \""+loginId+"\"",Toast.LENGTH_SHORT).show();
        }
    }


    // back được nhấn
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int gid = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (gid == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Nav click item
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int gid = item.getItemId();

        // nhan nut dang nhap
        // intent LoginActivity
        if (gid == R.id.nav_login) {

            //nhan dang xuat
            if(loginPwd != null && loginId != null && loginSuccess) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("bạn muốn đăng xuất?")
                        .setPositiveButton("không", null)
                        .setNeutralButton("có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor autoLogout = auto.edit();
                                autoLogout.putString("inputId",null);
                                autoLogout.putString("inputPwd",null);
                                autoLogout.putString("inputMail",null);
                                autoLogout.putString("inputName",null);
                                autoLogout.commit();

                                //tin nhan dang xuat
                                Toast.makeText(MainActivity.this,"\""+loginId+"\" Đăng xuất",Toast.LENGTH_SHORT).show();

                                // toi trang dang xuat
                                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                MainActivity.this.startActivity(intent);
                                finish();
                            }
                        })
                        .create()
                        .show();
            }
            // neu khong co thong tin dang nhap.
            // di den trang dang nhap
            else{
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
                //dang nhap thanh cong, MainActivity bi xoa
                finish();
            }
        }
        // intent UserAreaActivity
        else if (gid == R.id.nav_control) {
            //if(loginPwd != null && loginId != null && loginSuccess) {
                Intent intent = new Intent(MainActivity.this, HomeList.class);
                MainActivity.this.startActivity(intent);
            //}
            //neu chua dang nhap (Alert)
//            else{
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Chưa đăng nhập.")
//                        .setNegativeButton("Retry", null)
//                        .create()
//                        .show();
//            }
        } else if (gid == R.id.nav_home_status) {
            if(loginPwd != null && loginId != null && loginSuccess) {
                Intent intent = new Intent(MainActivity.this, HomeList.class);
                MainActivity.this.startActivity(intent);
            }

            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Chưa đăng nhập.")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }  else if (gid == R.id.nav_userinfo) {
            if(loginPwd != null && loginId != null && loginSuccess) {
                Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                MainActivity.this.startActivity(intent);
            }

            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Chưa đăng nhập.")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }  else if (gid == R.id.all_home){
                Intent intent = new Intent(MainActivity.this, HomeList.class);
                MainActivity.this.startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
