package io.github.anthonyeef.fanfoudaily.extras;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.Utils.UrlUtils;
import io.github.anthonyeef.fanfoudaily.json.Parser;
import io.github.anthonyeef.fanfoudaily.json.Requestor;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/16/15.
 */
public class FanfouUtils {
    public static ArrayList<Fanfou> loadFanfouDailyFeeds(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestJSON(requestQueue, UrlUtils.getRequestUrlFanfouDaily());
        ArrayList<Fanfou> fanfous = Parser.parseFanfouJSON(response);

        return fanfous;
    }

    public static ArrayList<Fanfou> loadFanfouWeeklyFeeds(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestJSON(requestQueue, UrlUtils.getRequestUrlFanfouWeekly());
        ArrayList<Fanfou> fanfous = Parser.parseFanfouJSON(response);

        return fanfous;
    }
}
