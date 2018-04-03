package com.example.cz.pubuliu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cz.pubuliu.R;
import com.example.cz.pubuliu.bean.PBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CZ on 2018/4/3.
 */

public class PAdapter extends RecyclerView.Adapter<PAdapter.MyViewHolder> {
    List<PBean.DataBean> list;
    Context context;
    private int itemWidth;

    public PAdapter(List<PBean.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        //宽度除三，放三张图片
        itemWidth = width / 2;
        //初始化imageloader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_padapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.mItemImageview.getLayoutParams();

        int itemHeight = 300;

        itemHeight = new Random().nextInt(500);
        if (itemHeight < 300) {
            itemHeight = 300;
        }

        params.width = itemWidth;
        params.height = itemHeight;

        holder.mItemImageview.setLayoutParams(params);

//        holder.itemImageview.setImageResource(R.mipmap.ic_launcher);

        Glide.with(context).load(list.get(position).getCover()).into(holder.mItemImageview);

        ImageLoader.getInstance().displayImage(list.get(position).getCover(), holder.mItemImageview, getoption());

        holder.mItemTextview.setText(position + "");


        holder.mItemImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view, position);
                }
            }

        });


        holder.mItemImageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.longClick(view, position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_imageview)
        ImageView mItemImageview;
        @BindView(R.id.item_textview)
        TextView mItemTextview;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Listener listener;

    public void setIListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        public void onClick(View view, int position);

        public void longClick(View view, int position);
    }

    public DisplayImageOptions getoption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图的时片Uri为空或是错误候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                //.decodingOptions(BitmapFactory.Options decodingOptions)//设置图片的解码配置
                .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//不推荐用！！！！是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间，可能会出现闪动
                .build();//构建完成
        return options;

    }

}
