package io.github.anthonyeef.fanfoudaily.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.Utils.DateUtils;
import io.github.anthonyeef.fanfoudaily.Utils.HttpUtils;
import io.github.anthonyeef.fanfoudaily.Utils.LogUtils;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.callbacks.RecyclerItemClickListener;
import io.github.anthonyeef.fanfoudaily.MyApplication;
import io.github.anthonyeef.fanfoudaily.extras.FanfouUtils;
import io.github.anthonyeef.fanfoudaily.model.Date;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.ui.UIStatus;
import io.github.anthonyeef.fanfoudaily.volley.VolleySingleton;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FragmentDaily extends Fragment {
    private static final String DAILY_FANFOU = "daily_fanfou";

    private ArrayList<Fanfou> listFanfous = new ArrayList<>();
    private FanfouAdapter mFanfouAdapter;

    private String temp = DateUtils.getCurrentDate();

    @Bind(R.id.swipeFanfous) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;

    public FragmentDaily() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(
                R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        EventBus.getDefault().register(this);

        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(DAILY_FANFOU);
        } else {
            if (listFanfous.isEmpty()) {
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if (HttpUtils.isNetworkConnected(getContext())) {
                            fetchData(temp);
                        } else {
                            Snackbar.make(view, "Hey you don't have internet yet.", Snackbar.LENGTH_LONG).show();
                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }
                });
                mFanfouAdapter.setFanfous(listFanfous);
            }
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Date date) {
        MyApplication.setDate(date);
        fetchData(MyApplication.getDate().getDate());
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DAILY_FANFOU, listFanfous);
    }

    private void setupSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange,
                R.color.green, R.color.red, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(HttpUtils.isNetworkConnected(getContext())) {
                            fetchData(temp);
                        } else {
                            Snackbar.make(getView(), "Hey you don't have internet yet.", Snackbar.LENGTH_LONG).show();
                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }
                }, 2500);
            }
        });
    }

    private void setupRecyclerView() {

        mRecyclerView.addOnItemTouchListener(
               new RecyclerItemClickListener(getContext(),
                       new RecyclerItemClickListener.OnItemClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {
                       startActivity(position);
                   }
               })
        );
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());
        mRecyclerView.setAdapter(mFanfouAdapter);
    }

    public class TaskLoadFanfouDaily extends AsyncTask<Void, Void, Integer> {

        private VolleySingleton mVolleySingleton;
        private RequestQueue mRequestQueue;
        String date;

        public TaskLoadFanfouDaily(String date) {
            this.date = date;
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
            try {
                listFanfous = FanfouUtils.loadFanfouDailyFeeds(mRequestQueue, date);
                throw new VolleyError("Ya Volley not working");
            } catch (VolleyError e) {
                LogUtils.m("Something happend:"+ e );
            }
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

    private void startActivity(int position) {
        Intent intent = new Intent(getActivity(), UIStatus.class);
        Fanfou feed = listFanfous.get(position);
        intent.putExtra("feed", feed);
        startActivity(intent);
    }

    public void fetchData(String date) {
//        Toast.makeText(getContext(), "Start loading Fanfou on date:" + date, Toast.LENGTH_SHORT).show();
        Snackbar.make(mRecyclerView, "Loading Fanfou on date:" + date, Snackbar.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(true);
        new TaskLoadFanfouDaily(date).execute();
        mFanfouAdapter.setFanfous(listFanfous);
    }
}
