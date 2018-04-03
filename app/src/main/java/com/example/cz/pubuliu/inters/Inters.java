package com.example.cz.pubuliu.inters;

import com.example.cz.pubuliu.bean.BBean;
import com.example.cz.pubuliu.bean.PBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by CZ on 2018/4/3.
 */

public interface Inters {
    //https://www.zhaoapi.cn/quarter/getHotVideos?token=1&page=1&source=android&appVersion=101
    @GET("quarter/getHotVideos")
    Observable<PBean> Pget(@QueryMap HashMap<String, String> map);

    //https://www.zhaoapi.cn/quarter/getAd
    @GET("quarter/getAd")
    Observable<BBean> Bget();
}
