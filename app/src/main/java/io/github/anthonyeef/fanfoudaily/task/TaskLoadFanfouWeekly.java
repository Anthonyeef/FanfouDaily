package io.github.anthonyeef.fanfoudaily.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.callbacks.FanfouLoadedListener;
import io.github.anthonyeef.fanfoudaily.extras.FanfouUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.network.VolleySingleton;

/**
 * Created by anthonyeef on 10/28/15.
 */
public class TaskLoadFanfouWeekly extends AsyncTask<Void, Void, ArrayList<Fanfou>> {
    private FanfouLoadedListener mListener;
    private VolleySingleton mVolleySingleton;
    private RequestQueue mRequestQueue;

    public TaskLoadFanfouWeekly(FanfouLoadedListener listener) {
        this.mListener = listener;
        mVolleySingleton = VolleySingleton.getInstance();

        mRequestQueue = mVolleySingleton.getRequestQueue();
    }

    @Override
    protected ArrayList<Fanfou> doInBackground(Void... params) {
        ArrayList<Fanfou> listFanfous = FanfouUtils.loadFanfouWeeklyFeeds(mRequestQueue);
        return listFanfous;
    }

    @Override
    protected void onPostExecute(ArrayList<Fanfou> listFanfous) {
        if (mListener != null) {
            mListener.onFanfouLoaded(listFanfous);
        }
    }
}
