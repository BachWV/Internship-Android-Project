package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class DrawerActivity_test1 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
     DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_test1);
        //Intent intent=getIntent();

        setTitle("文明xx新闻资讯");
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.outline_segment_white_36);//菜单栏提示图标
        }
           // actionBar.setHomeAsUpIndicator(R.mipmap.ic_drawer_my);

        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
       // navView.setCheckedItem(R.id.nav_1);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_2:
                        Intent intent2=new Intent(DrawerActivity_test1.this,Drawer2Activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_3:
                        Intent intent3=new Intent(DrawerActivity_test1.this,Drawer3Activity.class);
                        startActivity(intent3);
                        break;
                    default:
                }
                return true;
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){//创建menu


        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {//点击事件

    switch (item.getItemId()){
        case android.R.id.home:
            mDrawerLayout.openDrawer(GravityCompat.START);
        case R.id.setting2:
           mDrawerLayout.openDrawer(GravityCompat.START);
            break;
        case R.id.newpost:
            Intent intent2=new Intent(DrawerActivity_test1.this,NewPostActivity.class);//打开新activity：newPost
            startActivity(intent2);
            break;
        default:
    }

    return  true;}


}