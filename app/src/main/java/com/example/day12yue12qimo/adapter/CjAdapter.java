package com.example.day12yue12qimo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day12yue12qimo.R;
import com.example.day12yue12qimo.bean.ChengJiBean;

import java.util.ArrayList;

public class CjAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ChengJiBean.StudenlistBean> list;

    public CjAdapter(Context context, ArrayList<ChengJiBean.StudenlistBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.liebiao_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChengJiBean.StudenlistBean bean = list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.name.setText(bean.getName());
        holder1.jishi.setText(bean.getSkill());
        holder1.lilun.setText(bean.getTheory());
        holder1.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListener.Onclick(position);
            }
        });
    }

    public interface setOnClickListener{
        void Onclick(int pos);
    }
    setOnClickListener setOnClickListener;

    public void setSetOnClickListener(CjAdapter.setOnClickListener setOnClickListener) {
        this.setOnClickListener = setOnClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView name;
        public TextView jishi;
        public TextView lilun;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.jishi = (TextView) rootView.findViewById(R.id.jishi);
            this.lilun = (TextView) rootView.findViewById(R.id.lilun);
        }

    }
}
