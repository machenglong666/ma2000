package com.example.day12yue12qimo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.day12yue12qimo.adapter.BannerAdapter;
import com.example.day12yue12qimo.adapter.FragmentAdapter;
import com.example.day12yue12qimo.bean.BannerBean;
import com.example.day12yue12qimo.fragment.ClassFragment;
import com.example.day12yue12qimo.fragment.XueXiaoFragment;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private Banner banner;
    private TabLayout tabMode;
    private ViewPager vap;
    private Toolbar tooblar;
    private RecyclerView recycler;
    private ArrayList<BannerBean.BannerlistBean> list;
    private BannerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.banner)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class)
                .banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<BannerBean.BannerlistBean> bean = bannerBean.getBannerlist();
                        list.addAll(bean);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        tooblar = (Toolbar) findViewById(R.id.tooblar);
        tooblar.setTitle("首页");
        setSupportActionBar(tooblar);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new BannerAdapter(this, list);
        recycler.setAdapter(adapter);


        tabMode = (TabLayout) findViewById(R.id.tabMode);
        vap = (ViewPager) findViewById(R.id.vap);

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new XueXiaoFragment());
        list.add(new ClassFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vap.setAdapter(adapter);
        tabMode.setupWithViewPager(vap);

        tabMode.getTabAt(0).setText("学校新闻");
        tabMode.getTabAt(1).setText("班级成绩查询");




    }
}
