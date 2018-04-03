package com.example.cz.pubuliu.imageloder;

import android.app.Application;

/**
 * Created by CZ on 2018/4/3.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        //初始化
        ImageloaderUtil.initConfig(this);
    }
}
