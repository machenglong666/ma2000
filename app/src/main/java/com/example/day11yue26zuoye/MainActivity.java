package com.example.day11yue26zuoye;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day11yue26zuoye.fragment.DescFragment;
import com.example.day11yue26zuoye.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar tooblar;
    private ViewPager vap;
    private TabLayout tabMode;
    private LinearLayout ll;
    private DrawerLayout dl;
    private NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tooblar = (Toolbar) findViewById(R.id.tooblar);
        tooblar.setTitle("标题");
        setSupportActionBar(tooblar);
        vap = (ViewPager) findViewById(R.id.vap);
        tabMode = (TabLayout) findViewById(R.id.tabMode);
        ll = (LinearLayout) findViewById(R.id.ll);
        dl = (DrawerLayout) findViewById(R.id.dl);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tooblar, 0, 0);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                ll.setX(drawerView.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new DescFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vap.setAdapter(adapter);
        tabMode.setupWithViewPager(vap);

        tabMode.getTabAt(0).setText("首页").setIcon(R.drawable.select_item);
        tabMode.getTabAt(1).setText("收藏").setIcon(R.drawable.select_item);


        nav = (NavigationView) findViewById(R.id.nav);
       nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.dian:
                       Toast.makeText(MainActivity.this, "dian", Toast.LENGTH_SHORT).show();
                       break;
               }
               return false;
           }
       });
    }
}
