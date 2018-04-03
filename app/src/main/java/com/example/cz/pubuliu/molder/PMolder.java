package com.example.cz.pubuliu.molder;

import com.example.cz.pubuliu.app.MyApp;
import com.example.cz.pubuliu.bean.PBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CZ on 2018/4/3.
 */

public class PMolder {
    // //https://www.zhaoapi.cn/quarter/getHotVideos?token=1&page=1&source=android&appVersion=101
    public void get(String token, String page, String source, String appVersion, final PCallBack callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("page", page);
        map.put("source", source);
        map.put("appVersion", appVersion);
        MyApp.inters.Pget(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PBean>() {
                    @Override
                    public void accept(PBean bean) throws Exception {
                        callBack.success(bean);
                    }
                });
    }

    public interface PCallBack {
        public void success(PBean bean);

        public void failure(String e);
    }
}
