package com.on.diary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.on.diary.R;

import java.util.List;

/**
 * 日记界面的 Adapter
 * <p>
 * Created by developerHaoz on 2017/5/3.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> list;

    public DiaryAdapter(Context context, List<String> list) {
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(mLayoutInflater.inflate(R.layout.item_rv_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiaryViewHolder holder, final int position) {
        // String dateSystem = GetDate.getDate().toString();//获取系统时间
        holder.main_tv_date.setText(list.get(position));
        holder.main_iv_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,list.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {
        ImageView main_iv_circle;
        TextView main_tv_date;

        DiaryViewHolder(View view) {
            super(view);
            main_iv_circle = (ImageView) view.findViewById(R.id.main_iv_circle);
            main_tv_date = (TextView) view.findViewById(R.id.main_tv_date);

        }
    }
}
