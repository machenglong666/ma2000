package com.example.day12yue12qimo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day12yue12qimo.ApiService;
import com.example.day12yue12qimo.IView;
import com.example.day12yue12qimo.JsonP;
import com.example.day12yue12qimo.R;
import com.example.day12yue12qimo.adapter.XueXiaoAdapter;
import com.example.day12yue12qimo.bean.BannerBean;
import com.example.day12yue12qimo.bean.XinWenBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class XueXiaoFragment extends Fragment implements IView {

    private View view;
    private RecyclerView recycler;
    private ArrayList<XinWenBean.NewsBean> list;
    private JsonP jsonP;
    private XueXiaoAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_item, null);
        initView();
        initData();

        return view;
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.XINWEN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class)
                .XINWEN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XinWenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XinWenBean xinWenBean) {
                        List<XinWenBean.NewsBean> news = xinWenBean.getNews();
                        list.addAll(news);
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
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new XueXiaoAdapter(getActivity(), list);
        recycler.setAdapter(adapter);

    }

    @Override
    public void JieOK(Object object) {

    }

    @Override
    public void JieON(String string) {

    }
}
