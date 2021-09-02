package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.Common.CommonVar;
import com.example.helloworld.http.OkHttpCallback;
import com.example.helloworld.http.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText input_regster_username;
    private EditText input_regester_pwd;
    private Button btn_regester;
    private String str_reg_username;
    private String str_reg_pwd;
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_reg);//引用toolbar布局
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.outline_arrow_back_ios_white_24);//菜单栏提示返回图标，修改图片
        }
        input_regester_pwd=(EditText)findViewById(R.id.et_reg_password) ;
        input_regster_username=(EditText) findViewById(R.id.et_reg_username);
        btn_regester=(Button)findViewById(R.id.btn_register) ;
        setAllOnClickListener();
    }
    public boolean onCreateOptionsMenu(Menu menu){//创建menu


        getMenuInflater().inflate(R.menu.toolbarnewpost,menu);//加载menu/toolbar中的布局
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {//点击事件

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;

            default:
        }    return  true;}
    public void register(){

        str_reg_username=input_regster_username.getText().toString();
        str_reg_pwd=input_regester_pwd.getText().toString();
        Log.i("dsd",str_reg_pwd);

        RequestBody body = new FormBody.Builder().add("screen_name",str_reg_username).add("password",str_reg_pwd).build();
        OkHttpUtils.post("http://3a955v7566.wicp.vip/user/register",body,new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) throws JSONException {

                Log.e(getClass().getSimpleName(),"msg = " + msg);
                JSONObject ele = new JSONObject(msg);
                flag=ele.getInt("errorCode");

                if(flag==0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                            dialog.setTitle("注册成功");
                            dialog.setMessage("欢迎用户"+str_reg_username);

                            dialog.setNegativeButton(null,null);
                            dialog.show();
                        }
                    });
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);//打开新activity：
                    startActivity(intent);

                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                            dialog.setTitle("注册失败");
                            dialog.setMessage("请重新尝试");

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
       btn_regester.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_register:
                register();
                break;
        }
    }
}