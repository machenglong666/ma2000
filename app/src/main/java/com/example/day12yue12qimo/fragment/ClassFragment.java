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
import com.example.day12yue12qimo.R;
import com.example.day12yue12qimo.adapter.CjAdapter;
import com.example.day12yue12qimo.bean.BannerBean;
import com.example.day12yue12qimo.bean.ChengJiBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassFragment extends Fragment {

    private View view;
    private RecyclerView recycler;
    private ArrayList<ChengJiBean.StudenlistBean> list1;
    private CjAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ban_item, null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.CJ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class)
                .CJ()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChengJiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChengJiBean chengJiBean) {
                        List<ChengJiBean.StudenlistBean> studenlist = chengJiBean.getStudenlist();
                        list1.addAll(studenlist);
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
        recycler = view.findViewById(R.id.recycle);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list1 = new ArrayList<>();
        adapter = new CjAdapter(getActivity(), list1);
        recycler.setAdapter(adapter);
        adapter.setSetOnClickListener(new CjAdapter.setOnClickListener() {
            @Override
            public void Onclick(int pos) {
                list1.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
