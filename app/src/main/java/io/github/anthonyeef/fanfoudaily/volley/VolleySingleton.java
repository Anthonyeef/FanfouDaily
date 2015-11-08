package io.github.anthonyeef.fanfoudaily.volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import io.github.anthonyeef.fanfoudaily.MyApplication;

/**
 * Created by anthonyeef on 10/17/15.
 */
public class VolleySingleton {
    private static VolleySingleton instance = null;
    private RequestQueue mRequestQueue;
    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }
    public static VolleySingleton getInstance() {
        if (instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
