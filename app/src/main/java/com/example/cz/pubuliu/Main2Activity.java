package com.example.cz.pubuliu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cz.pubuliu.adapter.BAdapter;
import com.example.cz.pubuliu.bean.BBean;
import com.example.cz.pubuliu.persenter.BPersenter;
import com.example.cz.pubuliu.view.BView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements BView {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;
    BPersenter persenter = new BPersenter(this);
    List<BBean.DataBean> list = new ArrayList<BBean.DataBean>();
    private List<ImageView> images;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = pager.getCurrentItem();
                pager.setCurrentItem(currentItem + 1);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        persenter.getData();
        initDos();
    }

    @Override
    public void success(BBean bean) {
        for (int i = 0; i < bean.getData().size(); i++) {
            list.add(bean.getData().get(i));
        }
        BAdapter adapter = new BAdapter(list, Main2Activity.this);
        pager.setAdapter(adapter);
        //自动循环
        pager.setCurrentItem(list.size() * 10000);
        //设置时间
        handler.sendEmptyMessageDelayed(0, 2000);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置viewpager下的标题
                title.setText(list.get(position % list.size()).getTitle());
                //循环小圆点的个数
                for (int i = 0; i < images.size(); i++) {
                    if (i == position % images.size()) {
                        images.get(i).setImageResource(R.drawable.yes);
                    } else {
                        images.get(i).setImageResource(R.drawable.no);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void failure(String e) {
        Toast.makeText(Main2Activity.this, "错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.Bpersenter();
    }

    //设置小圆点
    private void initDos() {
        images = new ArrayList<>();
        linearlayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(Main2Activity.this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.yes);
            } else {
                imageView.setImageResource(R.drawable.no);
            }
            images.add(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置小圆点个数
            params.setMargins(3, 0, 3, 0);
            linearlayout.addView(imageView, params);

        }
    }

}
