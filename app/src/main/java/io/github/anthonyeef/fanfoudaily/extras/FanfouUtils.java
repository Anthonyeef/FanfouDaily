package io.github.anthonyeef.fanfoudaily.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.Utils.UrlUtils;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.volley.Parser;
import io.github.anthonyeef.fanfoudaily.volley.Requestor;

/**
 * Created by anthonyeef on 10/16/15.
 */
public class FanfouUtils {
    public static ArrayList<Fanfou> loadFanfouDailyFeeds(RequestQueue requestQueue, String date) {
        JSONObject response = Requestor.requestJSON(requestQueue, UrlUtils.getRequestUrlHead() + date + UrlUtils.getRequestUrlDailyEnd());
//        Log.v("Getting response from", UrlUtils.getRequestUrlFanfouDaily());
//        Log.v("Loading", response.toString());
        ArrayList<Fanfou> fanfous = Parser.parseFanfouJSON(response);

        return fanfous;
    }

    public static ArrayList<Fanfou> loadFanfouWeeklyFeeds(RequestQueue requestQueue, String date) {

        JSONObject response = Requestor.requestJSON(requestQueue, UrlUtils.getRequestUrlHead() + date + UrlUtils.getRequestUrlWeeklyEnd());
//        Log.v("Getting response from", UrlUtils.getRequestUrlFanfouWeekly());
//        Log.v("Loading", response.toString());
        ArrayList<Fanfou> fanfous = Parser.parseFanfouJSON(response);

        return fanfous;
    }
}
