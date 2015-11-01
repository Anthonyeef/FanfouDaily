package io.github.anthonyeef.fanfoudaily.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.callbacks.FanfouLoadedListener;
import io.github.anthonyeef.fanfoudaily.callbacks.RecyclerItemClickListener;
import io.github.anthonyeef.fanfoudaily.extras.FanfouUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.network.VolleySingleton;
import io.github.anthonyeef.fanfoudaily.ui.UIStatus;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FragmentDaily extends Fragment /*implements FanfouLoadedListener SwipeRefreshLayout.OnRefreshListener*/ {
    private static final String DAILY_FANFOU = "daily_fanfou";

    private ArrayList<Fanfou> listFanfous = new ArrayList<>();
    private FanfouAdapter mFanfouAdapter;

    @Bind(R.id.swipeFanfous) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;
    public FragmentDaily() {

    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), UIStatus.class);
                        Fanfou feed = listFanfous.get(position);
                        intent.putExtra("feed", feed);
                        startActivity(intent);
                    }
                })
        );
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());
        mRecyclerView.setAdapter(mFanfouAdapter);

        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(DAILY_FANFOU);
        } else {
            if (listFanfous.isEmpty()) {
                new TaskLoadFanfouDaily().execute();
                mFanfouAdapter.setFanfous(listFanfous);
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(DAILY_FANFOU, listFanfous);
    }

//    @Override
//    public void onFanfouLoaded(ArrayList<Fanfou> listFanfous) {
//
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
//        mFanfouAdapter.setFanfous(listFanfous);
//    }

//    @Override
//    public void onRefresh() {
//        LogUtils.t(getActivity(), "onRefresh");
//        mSwipeRefreshLayout.setRefreshing(true);
//        new TaskLoadFanfouDaily(this).execute();
//    }
//
    public class TaskLoadFanfouDaily extends AsyncTask<Void, Void, Integer> {
        private FanfouLoadedListener mListener;
        private VolleySingleton mVolleySingleton;
        private RequestQueue mRequestQueue;

        public TaskLoadFanfouDaily(/*FanfouLoadedListener listener*/) {
//            this.mListener = listener;
            mVolleySingleton = VolleySingleton.getInstance();

            mRequestQueue = mVolleySingleton.getRequestQueue();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer result = 0;
            listFanfous = FanfouUtils.loadFanfouDailyFeeds(mRequestQueue);
            result = 1;
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1) {
                mFanfouAdapter.setFanfous(listFanfous);
            }
        }
    }

}
