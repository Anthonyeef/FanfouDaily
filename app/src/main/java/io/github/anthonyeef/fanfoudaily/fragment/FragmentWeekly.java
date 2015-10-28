package io.github.anthonyeef.fanfoudaily.fragment;

/**
 * Created by anthonyeef on 10/28/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.callbacks.FanfouLoadedListener;
import io.github.anthonyeef.fanfoudaily.logging.LogUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.task.TaskLoadFanfouDaily;
import io.github.anthonyeef.fanfoudaily.task.TaskLoadFanfouWeekly;

public class FragmentWeekly extends Fragment implements FanfouLoadedListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String WEEKLY_FANFOU = "weekly_fanfou";

    private ArrayList<Fanfou> listFanfous = new ArrayList<>();
    private FanfouAdapter mFanfouAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;


    public FragmentWeekly() {

    }
//    public static FragmentDaily newInstance(String param1, String param2) {
//       FragmentDaily fragment = new FragmentDaily();
//       Bundle args = new Bundle();
//       fragment.setArguments(args);
//       return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_item_list, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeFanfous);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());
        mRecyclerView.setAdapter(mFanfouAdapter);

        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(WEEKLY_FANFOU);
        } else {
            if (listFanfous.isEmpty()) {
                new TaskLoadFanfouWeekly(this).execute();
            }
        }
        mFanfouAdapter.setFanfous(listFanfous);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(WEEKLY_FANFOU, listFanfous);
    }

    @Override
    public void onFanfouLoaded(ArrayList<Fanfou> listFanfous) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mFanfouAdapter.setFanfous(listFanfous);
    }

    @Override
    public void onRefresh() {
        LogUtils.t(getActivity(), "onRefresh");
        mSwipeRefreshLayout.setRefreshing(true);
        new TaskLoadFanfouDaily(this).execute();
    }

}
