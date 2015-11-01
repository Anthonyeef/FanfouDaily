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

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.callbacks.FanfouLoadedListener;
import io.github.anthonyeef.fanfoudaily.logging.LogUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.task.TaskLoadFanfouDaily;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FragmentDaily extends Fragment implements FanfouLoadedListener, SwipeRefreshLayout.OnRefreshListener {
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext());
//            @Override
//            public void onItemClick(View v, int position) {
//                Toast.makeText(getContext(), "You click item:" + position, Toast.LENGTH_LONG).show();
//                Fanfou fanfou = listFanfous.get(position);
//                Intent intent = new Intent(getActivity(), UIStatus.class);
//                intent.putExtra("fanfou", fanfou);

//                startActivity(intent);
//                getActivity().startActivity(intent);

//            }
//        });
        mRecyclerView.setAdapter(mFanfouAdapter);

        if (savedInstanceState != null) {
            listFanfous = savedInstanceState.getParcelableArrayList(DAILY_FANFOU);
        } else {
            if (listFanfous.isEmpty()) {
                new TaskLoadFanfouDaily(this).execute();
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
