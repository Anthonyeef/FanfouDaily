package io.github.anthonyeef.fanfoudaily;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import im.fir.sdk.FIR;
import io.github.anthonyeef.fanfoudaily.model.Date;

/**
 * Created by anthonyeef on 10/13/15.
 */
public class MyApplication extends Application {

    public static final String TAG = Application.class.getSimpleName();

    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;
    private static Date mDate;

    @Override
    public void onCreate() {

        FIR.init(this);
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void setDate(Date date) {
        mDate = date;
    }

    public static Date getDate() {
        return mDate;
    }
}
