package io.github.anthonyeef.fanfoudaily.fragment;

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
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.task.TaskLoadFanfou;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FragmentDaily extends Fragment implements FanfouLoadedListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = FragmentDaily.class.getSimpleName();
    private static final String DAILY_FANFOU = "daily_fanfou";

    private ArrayList<Fanfou> listFanfous = new ArrayList<>();
    private FanfouAdapter mFanfouAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public FragmentDaily() {

    }
    public static FragmentDaily newInstance(String param1, String param2) {
       FragmentDaily fragment = new FragmentDaily();
       Bundle args = new Bundle();
       fragment.setArguments(args);
       return fragment;
    }
    public void onFanfouLoaded() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_item_list, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());
        mRecyclerView.setAdapter(mFanfouAdapter);

        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(TAG);
        } else {
            if (listFanfous.isEmpty()) {
                new TaskLoadFanfou(this).execute();
            }
        }
        mFanfouAdapter.setFanfous(listFanfous);
        return rv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(TAG, listFanfous);
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
        new  TaskLoadFanfou(this).execute();
    }

}
