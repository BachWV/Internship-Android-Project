package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Common.CommonUrl;
import com.example.helloworld.http.OkHttpCallback;
import com.example.helloworld.http.OkHttpUtils;
import com.example.helloworld.http.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//TODO:打开图片，上传图片，请求头加token这些东西因为开发进度还没做


public class NewPostActivity extends AppCompatActivity {
    private EditText input_title;
    private EditText input_content;
    private Button input_image;
    String post_title;
    public SharedPreferencesUtils sp1;
    String post_content;
    public  String result,is;
    boolean success_post_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);//引用布局
        setTitle("创建新的发布内容");
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_newpost);//引用toolbar布局
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.outline_arrow_back_ios_white_24);//菜单栏提示返回图标，修改图片
        }
        input_title=(EditText) findViewById(R.id.input_title);//获取tilte文本框
        input_content=(EditText) findViewById(R.id.input_content);//获取content文本框
        sp1=SharedPreferencesUtils.getInstance(this);

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
            case R.id.setting2:
//Menu菜单栏待定
                break;
            case R.id.send://请求发送
               sendPost();
                break;
            default:
        }

        return  true;}

        public boolean sendPost(){

            post_title=input_title.getText().toString();//获取新发布内容的标题
            post_content=input_content.getText().toString();//获取新发布内容的内容
            Log.e(getClass().getSimpleName(),"title = " + post_title);
            RequestBody body = new FormBody.Builder().add("title",post_title)
                    .add("text",post_content)
                    .add("created_at","2021-07-13 22:22:22")
                    .add("pic","dsds")
                    .add("pic_ids","12")
                    .add("sourse","dusersd")
                    .add("user_id","1").build();
            OkHttpUtils.postWithToken("http://3a955v7566.wicp.vip/update",body,sp1.readString("p"),new OkHttpCallback(){
                @Override
                public void onFinish(String status, String msg) throws JSONException {
                    Log.e(getClass().getSimpleName(),"msg = " + msg);
                    JSONObject ele = new JSONObject(msg);
                    int flag=ele.getInt("errorCode");

                    Log.e(getClass().getSimpleName(),"flag = " + flag);
                    if(flag==0){

                       // Log.e(getClass().getSimpleName(),"token = " +sp1.readString("p"));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog.Builder dialog = new AlertDialog.Builder(NewPostActivity.this);
                                dialog.setTitle("发表成功");
                                dialog.setMessage("欧克！");

                                dialog.setNegativeButton(null,null);
                                dialog.show();

                            }
                        });

                        //Toast.makeText(LoginActivity.this,"登录-成功", LENGTH_SHORT).show();
                        // JSONObject datas_js = new JSONObject(datas);
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(NewPostActivity.this);
                                dialog.setTitle("发表失败");
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



            return true;
        }



}