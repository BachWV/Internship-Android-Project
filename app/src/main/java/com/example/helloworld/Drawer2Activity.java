package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Drawer2Activity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);//关闭标题
        setContentView(R.layout.activity_drawer2_test);

       // Intent intent=getIntent();
//mDrawerLayout=(DrawerLayout)findViewById(R.id.nav_view);

       // TextView tv_title = (TextView) findViewById(R.id.tv_title);
       // tv_title.setText("注册");
       // tv_title.setGravity(Gravity.CENTER);
        setTitle("xx志愿者APP");

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.outline_segment_white_36);
        }
        //tab 栏 start here
        viewPager =findViewById(R.id.view_pager);
        tabLayout =findViewById(R.id.tab_layout);

        ArrayList<String> arrayList= new ArrayList<String>();
        arrayList.add("项目介绍");
        arrayList.add("新闻专栏");
        arrayList.add("志愿招募");
        prepareViewPager(viewPager,arrayList);
        tabLayout.setupWithViewPager(viewPager);

        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
     // navView.setCheckedItem(R.id.nav_1);
      navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
              switch (item.getItemId()){

                  case R.id.nav_1:Intent intent1=new Intent(Drawer2Activity.this,DrawerActivity_test1.class);
                      startActivity(intent1);
                      break;

                  case R.id.nav_3:Intent intent2=new Intent(Drawer2Activity.this,Drawer3Activity.class);
                      startActivity(intent2);

                      break;
                  default:
              }

              return true;
          }
      });


    }
    public boolean onCreateOptionsMenu(Menu menu){


        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {//点击事件

            switch (item.getItemId()){
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
                case R.id.setting2:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
                case R.id.newpost:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
                default:
            }

            return  true;}


    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter =new MainAdapter(getSupportFragmentManager());
        MainFragment fragment=new MainFragment();
        for(int i=0;i<arrayList.size();i++){
            Bundle bundle=new Bundle();
            bundle.putString("title",arrayList.get(i));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment,arrayList.get(i));
            fragment=new MainFragment();//new fragment
        }
        viewPager.setAdapter(adapter);
    }//tab栏

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList=new ArrayList<String>();//新建arraylist存标题
        List<Fragment> fragmentList=new ArrayList<>();//新建arraylist存fragment
        public void addFragment(Fragment fragment,String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }
        public MainAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);//改title
        }
    }//tab的适配器

}