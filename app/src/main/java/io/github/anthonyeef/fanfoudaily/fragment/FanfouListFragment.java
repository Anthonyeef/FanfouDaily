package io.github.anthonyeef.fanfoudaily.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FanfouListFragment extends Fragment {
    private static final String TAG = FanfouListFragment.class.getSimpleName();

    private FanfouAdapter mFanfouAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_item_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        mFanfouAdapter = new FanfouAdapter(getContext(), );
        recyclerView.setAdapter(mFanfouAdapter);
    }

}
