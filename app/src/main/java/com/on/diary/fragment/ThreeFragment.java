package com.on.diary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.on.diary.R;
import com.on.diary.adapter.MeiziAdapter;
import com.on.diary.bean.MeiziBean;
import com.on.diary.contants.MeiziApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static String response = "";
    RecyclerView meizi_rv_show_meizi;
    SwipeRefreshLayout meizi_refresh;
    List<MeiziBean> meiziBeanList = new ArrayList<>();
    MeiziAdapter mMeiziAdapter;
    private String mParam1;
    private int lastVisibleItem;
    private int page = 1;
    private ItemTouchHelper itemTouchHelper;

    public ThreeFragment() {
        // Required empty public constructor
    }

    public static ThreeFragment newInstance(String param1, String param2) {
        ThreeFragment fragment = new ThreeFragment();
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
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        meizi_rv_show_meizi = (RecyclerView) view.findViewById(R.id.meizi_rv_show_meizi);
        meizi_refresh = (SwipeRefreshLayout) view.findViewById(R.id.meizi_refresh);
        //调整SwipeRefreshLayout的位置
        meizi_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        initView();
        refreshMeizi();
        return view;
    }

    private void initView() {
        Set<MeiziBean> meiziSet = new HashSet<>();
        meiziSet.addAll(MeiziApi.getMeiziBeanList());
        meiziBeanList.clear();
        meiziBeanList.addAll(meiziSet);
        Collections.shuffle(meiziBeanList);
        meizi_rv_show_meizi.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        meizi_rv_show_meizi.setAdapter(new MeiziAdapter(meiziBeanList, ThreeFragment.this));
    }

    /**
     * 刷新当前界面
     */
    private void refreshMeizi() {
        meizi_refresh.setColorSchemeResources(R.color.pink);
        //swipeRefreshLayout刷新监听
        meizi_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

    }


}
