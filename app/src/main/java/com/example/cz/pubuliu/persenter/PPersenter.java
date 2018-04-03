package com.example.cz.pubuliu.persenter;

import com.example.cz.pubuliu.bean.PBean;
import com.example.cz.pubuliu.molder.PMolder;
import com.example.cz.pubuliu.view.PView;

/**
 * Created by CZ on 2018/4/3.
 */

public class PPersenter {
    PView view;
    private final PMolder molder;

    public PPersenter(PView view) {
        this.view = view;
        molder = new PMolder();
    }

    public void getData(String token, String page, String source, String appVersion) {
        molder.get(token, page, source, appVersion, new PMolder.PCallBack() {
            @Override
            public void success(PBean bean) {
                if (view != null) {
                    view.success(bean);
                }
            }

            @Override
            public void failure(String e) {
                if (view != null) {
                    view.failure(e);
                }
            }
        });
    }

    public void Ppersenter() {
        this.view = null;
    }
}
