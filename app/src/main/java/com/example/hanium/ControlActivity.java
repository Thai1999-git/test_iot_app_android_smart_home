//package com.example.hanium;
//
//
//import android.app.Activity;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.CompoundButton;
//import android.widget.Switch;
//import androidx.appcompat.app.AppCompatActivity;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//public class ControlActivity extends AppCompatActivity {
//
//    //username, password
//    String loginId;
//    String loginPwd;
//
//    // MQTT Appliances
//    MqttAndroid mqttsFan;
//    MqttAndroid mqttsLight;
//    MqttAndroid mqttsLight2;
//    MqttAndroid mqttsHumid;
//
//    //switch icon
//    Switch Fan;
//    Switch Light;
//    Switch Light2;
//    Switch Humid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //get
//        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
//        loginId = auto.getString("inputId",null);
//        loginPwd = auto.getString("inputPwd",null);
//        String name = auto.getString("inputName",null);
//        setContentView(R.layout.activity_control);
//
//        //switch connect
//        Fan = (Switch)findViewById(R.id.fan);
//        Light =(Switch)findViewById(R.id.light);
//        Light2 =(Switch)findViewById(R.id.light2);
//        Humid =(Switch)findViewById(R.id.humid);
//
//        //them nut quay lai
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //mqtt connect
//        MqttAllSetting();
//    }
//
//
//    //MQTT pub, sub
//    //modul hoa cac chuc nang
//
//    public void MqttAllSetting(){
//        mqttsFan = new MqttAndroid(this,loginId+"_fan",Fan);
//        mqttsLight = new MqttAndroid(this,loginId+"_light",Light);
//        mqttsLight2 = new MqttAndroid(this,loginId+"_light2",Light2);
//        mqttsHumid = new MqttAndroid(this,loginId+"_humid",Humid);
//
//
//        Fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
//                try {
//                    if (isCheck) {
//                        mqttsFan.publish(loginId+"_fan", new MqttMessage("1".getBytes()));
//                    }
//                    else {
//                        mqttsFan.publish(loginId+"_fan",new MqttMessage("0".getBytes()));
//                    }
//                }catch (MqttException e) {
//                    e.printStackTrace();
//                }
//                //Toast.makeText(ControlActivity.this,"kiểm tra trạng thái = "+isCheck+": ",Toast.LENGTH_SHORT).show();
//            }
//        });
//        Light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
//                try {
//                    if (isCheck) {
//                        mqttsFan.publish(loginId+"_light", new MqttMessage("1".getBytes()));
//                    }
//                    else {
//                        mqttsFan.publish(loginId+"_light",new MqttMessage("0".getBytes()));
//                    }
//                }catch (MqttException e) {
//                    e.printStackTrace();
//                }
//                //Toast.makeText(ControlActivity.this,"kiểm tra = "+isCheck+": ",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Light2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
//                try {
//                    if (isCheck) {
//                        mqttsFan.publish(loginId+"_light2", new MqttMessage("1".getBytes()));
//                    }
//                    else {
//                        mqttsFan.publish(loginId+"_light2",new MqttMessage("0".getBytes()));
//                    }
//                }catch (MqttException e) {
//                    e.printStackTrace();
//                }
//                //Toast.makeText(ControlActivity.this,"kiểm tra = "+isCheck+": ",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Humid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
//                try {
//                    if (isCheck) {
//                        mqttsFan.publish(loginId+"_humid", new MqttMessage("1".getBytes()));
//                    }
//                    else {
//                        mqttsFan.publish(loginId+"_humid",new MqttMessage("0".getBytes()));
//                    }
//                }catch (MqttException e) {
//                    e.printStackTrace();
//                }
//                //Toast.makeText(ControlActivity.this,"kiểm tra trạng thái = "+isCheck+": ",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public  boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//        switch (id){
//            case android.R.id.home:
//            {
//                finish();
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        MqttAllSetting();
//    }
//}
