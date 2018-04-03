package com.example.cz.pubuliu.view;

import com.example.cz.pubuliu.bean.BBean;

/**
 * Created by CZ on 2018/4/3.
 */

public interface BView {
    public void success(BBean bean);

    public void failure(String e);
}
