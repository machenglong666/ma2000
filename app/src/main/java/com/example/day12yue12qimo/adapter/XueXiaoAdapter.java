package com.example.day12yue12qimo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day12yue12qimo.R;
import com.example.day12yue12qimo.bean.XinWenBean;

import java.util.ArrayList;

public class XueXiaoAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<XinWenBean.NewsBean> list;

    public XueXiaoAdapter(Context context, ArrayList<XinWenBean.NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.xinwen_item, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XinWenBean.NewsBean bean = list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.desc.setText(bean.getContent());
        holder1.title.setText(bean.getTile());
        Glide.with(context).load(bean.getImageUrl()).into(holder1.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends BannerAdapter.ViewHolder {
        public View rootView;
        public ImageView image;
        public TextView title;
        public TextView desc;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.desc = (TextView) rootView.findViewById(R.id.desc);
        }

    }
}
