package com.on.diary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.on.diary.R;
import com.on.diary.adapter.DiaryAdapter;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    List<String> list;
    RecyclerView mRvShowDiary;
    SwipeRefreshLayout refresh;

    public OneFragment() {
    }

    public static OneFragment newInstance(String param1, String param2) {
        OneFragment fragment = new OneFragment();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragment 加载布局
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        mRvShowDiary=(RecyclerView) v.findViewById(R.id.main_rv_show_diary);
        refresh=(SwipeRefreshLayout)v.findViewById(R.id.refresh);

        list=new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("第"+ i +"个");

        }
        mRvShowDiary.setLayoutManager(new GridLayoutManager(getActivity(),3));
       // mRvShowDiary.setItemAnimator(new DefaultItemAnimator());
      //  mRvShowDiary.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));
        mRvShowDiary.setAdapter(new DiaryAdapter(getActivity(),list));

        refresh();
        return v;
    }


    //刷新数据
    public void refresh() {
        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
