package com.example.day11yue26zuoye;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apiservice  {
    //https://www.wanandroid.com/project/list/1/json?cid=294
    public static String url = "https://www.wanandroid.com/";
    @GET("project/list/{page}/json")
    Observable<XiangMuBean> getxiangmu(@Path("page") int page, @Query("cid") int cid);
}
