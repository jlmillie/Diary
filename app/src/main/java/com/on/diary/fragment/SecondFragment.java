package com.on.diary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.on.diary.R;
import com.on.diary.adapter.ReadAdapter;
import com.on.diary.bean.DuanziBean;
import com.on.diary.contants.DuanziApi;
import com.on.diary.net.VolleyHelper;
import com.on.diary.net.VolleyResponseCallback;
import com.on.diary.utils.read.GsonHelper;

import java.util.List;

//展示数据碎片
public class SecondFragment extends Fragment implements ReadAdapter.OnItemClickCallback {
    private static final String ARG_PARAM1 = "param1";
    //刷新控件
    SwipeRefreshLayout read_refresh;
    //显示列表
    RecyclerView rv_read_show;
    private String mParam1;

    //空的构造方法
    public SecondFragment() {
        // Required empty public constructor
    }

    //实例
    public static SecondFragment newInstance(String param1) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    //fragment 创建视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        read_refresh = (SwipeRefreshLayout) view.findViewById(R.id.read_refresh);
        rv_read_show = (RecyclerView) view.findViewById(R.id.rv_read_show);
        initView();
        refresh();
        return view;
    }

    private void initView() {
        //获取数据
        VolleyHelper.sendHttpGet(getActivity(), DuanziApi.GET_DUANZI, new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                List<DuanziBean> mDuanziBeanList = GsonHelper.getDuanziBeanList(response);
                if (mDuanziBeanList.size() > 4) {
                    mDuanziBeanList.remove(3);
                }
                rv_read_show.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_read_show.setAdapter(new ReadAdapter(SecondFragment.this, mDuanziBeanList, SecondFragment.this));
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("error", error + "");
            }
        });
    }


    //刷新数据
    public void refresh() {
        read_refresh.setColorSchemeResources(R.color.colorPrimary);
        read_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                read_refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
    }


}
