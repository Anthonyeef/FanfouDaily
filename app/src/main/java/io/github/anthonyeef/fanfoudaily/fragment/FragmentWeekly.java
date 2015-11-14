package io.github.anthonyeef.fanfoudaily.fragment;

/**
 * Created by anthonyeef on 10/28/15.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.Utils.DateUtils;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.callbacks.RecyclerItemClickListener;
import io.github.anthonyeef.fanfoudaily.extras.FanfouUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.volley.VolleySingleton;
import io.github.anthonyeef.fanfoudaily.ui.UIStatus;

public class FragmentWeekly extends Fragment {
    private static final String WEEKLY_FANFOU = "weekly_fanfou";

    private ArrayList<Fanfou> listFanfous = new ArrayList<>();
    private FanfouAdapter mFanfouAdapter;

    private String temp = DateUtils.getCurrentMonday();

    @Bind(R.id.swipeFanfous) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;

    public FragmentWeekly() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.red, R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new TaskLoadFanfouWeekly().execute();
                        mFanfouAdapter.setFanfous(listFanfous);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), UIStatus.class);
                        Fanfou feed = listFanfous.get(position);
                        intent.putExtra("feed", feed);
                        startActivity(intent);
                    }
                }));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());

        mRecyclerView.setAdapter(mFanfouAdapter);
        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(WEEKLY_FANFOU);
        } else {
            if (listFanfous.isEmpty()) {
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        new TaskLoadFanfouWeekly().execute();
                    }
                });
                mFanfouAdapter.setFanfous(listFanfous);
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listFanfous.isEmpty()){
            new TaskLoadFanfouWeekly().execute();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(WEEKLY_FANFOU, listFanfous);
    }



    public class TaskLoadFanfouWeekly extends AsyncTask<Void, Void, Integer> {
        private VolleySingleton mVolleySingleton;
        private RequestQueue mRequestQueue;

        public TaskLoadFanfouWeekly() {
            mVolleySingleton = VolleySingleton.getInstance();
            mRequestQueue = mVolleySingleton.getRequestQueue();
        }

        @Override
        protected void onPreExecute() {
            mSwipeRefreshLayout.setRefreshing(true);
            listFanfous.clear();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer result = 0;
            temp = DateUtils.getCurrentMonday();
            listFanfous = FanfouUtils.loadFanfouWeeklyFeeds(mRequestQueue, temp);
            result = 1;
            return result;
        }
        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1) {
                mFanfouAdapter.setFanfous(listFanfous);
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }

}
