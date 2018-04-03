package com.example.cz.pubuliu.molder;

import com.example.cz.pubuliu.app.MyApp;
import com.example.cz.pubuliu.bean.BBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CZ on 2018/4/3.
 */

public class BMolder {
    //https://www.zhaoapi.cn/quarter/getAd
    public void get(final BCallBack callBack) {
        MyApp.inters.Bget()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BBean>() {
                    @Override
                    public void accept(BBean bean) throws Exception {
                        callBack.success(bean);
                    }
                });
    }

    public interface BCallBack {
        public void success(BBean bean);

        public void failure(String e);
    }

    ;

}
