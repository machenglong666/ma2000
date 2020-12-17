package com.example.day12yue12qimo;

import com.example.day12yue12qimo.bean.BannerBean;
import com.example.day12yue12qimo.bean.ChengJiBean;
import com.example.day12yue12qimo.bean.XinWenBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //http://cdwan.cn:7000/exam2003/abannerlist.json

    public static String  banner = "http://cdwan.cn:7000/exam2003/";
    @GET("abannerlist.json")
    Observable<BannerBean> banner();



    //http://cdwan.cn:7000/exam2003/astudent.json

    public static String  CJ = "http://cdwan.cn:7000/exam2003/";
    @GET("astudent.json")
    Observable<ChengJiBean> CJ();

    //http://cdwan.cn:7000/exam2003/anewslist.json

    public static String  XINWEN = "http://cdwan.cn:7000/exam2003/";
    @GET("anewslist.json")
    Observable<XinWenBean> XINWEN();
}
