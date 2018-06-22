package com.on.diary.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.on.diary.R;
import com.on.diary.bean.DuanziBean;
import com.on.diary.utils.Check;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/2/9.
 */
//recycleview的使用
public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ReadViewHolder> {
    //itemd点击监听回调
    private OnItemClickCallback mCallback;

    public interface OnItemClickCallback {
        void onItemClick(int position);
    }

    private Fragment mFragment;
    private List<DuanziBean> mDuanziBeanList;

    //构造函数，将数据传过来
    public ReadAdapter(Fragment fragment, List<DuanziBean> mDuanziBeanList, OnItemClickCallback callback) {
        this.mFragment = fragment;
        this.mDuanziBeanList = mDuanziBeanList;
        this.mCallback = callback;
    }

    //获取视图
    @Override
    public ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read, null);
        return new ReadViewHolder(view);
    }

    //绑定组件
    @Override
    public void onBindViewHolder(ReadViewHolder holder, int position) {
        RecyclerView rvTest = (RecyclerView) holder.itemView.getParent();
        try {
            DuanziBean readBean = mDuanziBeanList.get(position);
            if (!Check.isEmpty(readBean.getGroupBean().getUser().getAvatar_url())) {
                Glide.with(mFragment).load(readBean.getGroupBean().getUser().getAvatar_url()).into(holder.mCivAvatar);
            }
            holder.mTvContent.setText(readBean.getGroupBean().getText());
            holder.mTvAuthor.setText(readBean.getGroupBean().getUser().getName());
            Log.e("段子姓名", readBean.getGroupBean().getUser().getName() + "");
            Log.e("段子wenzhang", readBean.getGroupBean().getText() + "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return mDuanziBeanList.size();
    }

    //自定义类继承recyclerview的viewholder
    class ReadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView mCivAvatar;
        private TextView mTvAuthor;
        private TextView mTvContent;

        public ReadViewHolder(View itemView) {
            super(itemView);
            mCivAvatar = (CircleImageView) itemView.findViewById(R.id.duanzi_civ_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.duanzi_tv_author);
            mTvContent = (TextView) itemView.findViewById(R.id.duanzi_tv_content);
        }

        @Override
        public void onClick(View v) {

            mCallback.onItemClick(getAdapterPosition());
        }
    }
}
