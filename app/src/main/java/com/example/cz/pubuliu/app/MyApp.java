package com.example.cz.pubuliu.app;

import android.app.Application;

import com.example.cz.pubuliu.inters.Inters;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CZ on 2018/4/3.
 */

public class MyApp extends Application {

    public static Inters inters;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Retrofit retrofit = new Retrofit.Builder()
                //https://www.zhaoapi.cn/quarter/getHotVideos?token=1&page=1&source=android&appVersion=101
                .baseUrl("https://www.zhaoapi.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        inters = retrofit.create(Inters.class);
    }
}
