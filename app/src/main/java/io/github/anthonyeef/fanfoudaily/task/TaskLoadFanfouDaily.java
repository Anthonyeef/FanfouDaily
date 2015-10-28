package io.github.anthonyeef.fanfoudaily.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.callbacks.FanfouLoadedListener;
import io.github.anthonyeef.fanfoudaily.extras.FanfouUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.network.VolleySingleton;

/**
 * Created by anthonyeef on 10/17/15.
 */
public class TaskLoadFanfouDaily extends AsyncTask<Void, Void, ArrayList<Fanfou>>{
    private FanfouLoadedListener mListener;
    private VolleySingleton mVolleySingleton;
    private RequestQueue mRequestQueue;

    public TaskLoadFanfouDaily(FanfouLoadedListener listener) {
        this.mListener = listener;
        mVolleySingleton = VolleySingleton.getInstance();

        mRequestQueue = mVolleySingleton.getRequestQueue();
    }

    @Override
    protected ArrayList<Fanfou> doInBackground(Void... params) {
//        ArrayList<Fanfou> listFanfous = new ArrayList<>();
//        if (params.equals("daily_fanfou")) {
//            listFanfous = FanfouUtils.loadFanfouDailyFeeds(mRequestQueue);
//        } else if (params.equals("weekly_fanfou"))
//            listFanfous = FanfouUtils.loadFanfouWeeklyFeeds(mRequestQueue);
        ArrayList<Fanfou> listFanfous = FanfouUtils.loadFanfouDailyFeeds(mRequestQueue);
        return listFanfous;
    }

    @Override
    protected void onPostExecute(ArrayList<Fanfou> listFanfous) {
        if (mListener != null) {
            mListener.onFanfouLoaded(listFanfous);
        }
    }
}
