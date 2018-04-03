package com.example.cz.pubuliu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.cz.pubuliu.adapter.PAdapter;
import com.example.cz.pubuliu.bean.PBean;
import com.example.cz.pubuliu.persenter.PPersenter;
import com.example.cz.pubuliu.view.PView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PView {

    PPersenter persenter = new PPersenter(this);
    List<PBean.DataBean> list = new ArrayList<PBean.DataBean>();
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        persenter.getData("1", "1", "android", "101");
    }

    @Override
    public void success(PBean bean) {
        for (int i = 0; i < bean.getData().size(); i++) {
            list.add(bean.getData().get(i));
        }

        PAdapter adapter = new PAdapter(list, MainActivity.this);
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        mRecycler.setAdapter(adapter);
    }

    @Override
    public void failure(String e) {
        Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.Ppersenter();
    }
}
