package com.example.cz.pubuliu.persenter;

import com.example.cz.pubuliu.bean.BBean;
import com.example.cz.pubuliu.molder.BMolder;
import com.example.cz.pubuliu.view.BView;

/**
 * Created by CZ on 2018/4/3.
 */

public class BPersenter {
    BView view;
    private final BMolder molder;

    public BPersenter(BView view) {
        this.view = view;
        molder = new BMolder();
    }

    public void getData() {
        molder.get(new BMolder.BCallBack() {
            @Override
            public void success(BBean bean) {
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

    public void Bpersenter() {
        this.view = null;
    }
}
