package io.github.anthonyeef.fanfoudaily.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.json.Requestor;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/16/15.
 */
public class FanfouUtils {
    public static ArrayList<Fanfou> loadFanfouFeeds(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestJSON(requestQueue, UrlEndpoints.)
    }
}
