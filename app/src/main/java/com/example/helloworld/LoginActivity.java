package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Common.CommonVar;
import com.example.helloworld.http.OkHttpCallback;
import com.example.helloworld.http.OkHttpUtils;
import com.example.helloworld.http.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText input_username;
    private EditText input_pwd;
    private Button btn_login;
    private TextView tv_register;
    private TextView tv_boom;//彩蛋！！！！！
    private String str_username;
    private String str_pwd;
    public SharedPreferencesUtils sp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_username=(EditText)findViewById(R.id.et_username);
        input_pwd=(EditText)findViewById(R.id.et_password);
        tv_register=(TextView) findViewById(R.id.tv_register);

        btn_login=(Button)findViewById(R.id.btn_login);
        tv_boom=(TextView)findViewById(R.id.tv_boom);

        setAllOnClickListener();
       sp1=SharedPreferencesUtils.getInstance(this);
    }

    public void login(){
        str_username=input_username.getText().toString();
        str_pwd=input_pwd.getText().toString();
        Log.e(getClass().getSimpleName(),"usrname = " + str_username);
        RequestBody body = new FormBody.Builder().add("screen_name",str_username).add("password",str_pwd).build();
        OkHttpUtils.post("http://3a955v7566.wicp.vip/user/login",body,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) throws JSONException {
                Log.e(getClass().getSimpleName(),"msg = " + msg);
                JSONObject ele = new JSONObject(msg);
                int flag=ele.getInt("errorCode");

                Log.e(getClass().getSimpleName(),"flag = " + flag);
                if(flag==0){
                    JSONObject eledatas=ele.getJSONObject("datas");

                   sp1.putString("p",eledatas.getString("token"));

                    Log.e(getClass().getSimpleName(),"datas = " +eledatas.toString());
                    Log.e(getClass().getSimpleName(),"token = " +sp1.readString("p"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                            dialog.setTitle("登录成功");
                            dialog.setMessage("欢迎用户"+str_username);

                            dialog.setNegativeButton(null,null);
                            dialog.show();
                        }
                    });
                    Intent intent=new Intent(LoginActivity.this,DrawerActivity_test1.class);//打开新activity：
                    startActivity(intent);
                    //Toast.makeText(LoginActivity.this,"登录-成功", LENGTH_SHORT).show();
                   // JSONObject datas_js = new JSONObject(datas);
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                            dialog.setTitle("登录失败");
                            dialog.setMessage("请重新输入。");
                            dialog.setNegativeButton(null,null);
                            dialog.show();
                        }
                    });
                }



//
//          int errcode= ele.getInt("errorCode");
            }
        });

    }
    private void setAllOnClickListener(){
        tv_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_boom.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //登录：
            case R.id.btn_login:
                login();


                break;
            case R.id.tv_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);//打开新activity：
                startActivity(intent);
                break;
            case R.id.tv_boom:
//                Intent intent1=new Intent(LoginActivity.this,DrawerActivity_test1.class);//打开新activity：
//                startActivity(intent1);
                Intent intent2 = new Intent();
                intent2.setClassName("com.hisilicon.camplayer", "com.hihope.histreaminglinklite.RtspActivity");
                startActivity(intent2);
                break;
        }

    }
}