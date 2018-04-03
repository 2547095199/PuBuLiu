package com.example.cz.pubuliu.view;

import com.example.cz.pubuliu.bean.PBean;

/**
 * Created by CZ on 2018/4/3.
 */

public interface PView {
    public void success(PBean bean);

    public void failure(String e);
}
