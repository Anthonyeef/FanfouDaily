package io.github.anthonyeef.fanfoudaily.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.FanfouAdapter;
import io.github.anthonyeef.fanfoudaily.controller.AppController;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/14/15.
 */
public class FanfouListFragment extends Fragment {
    private static final String TAG = FanfouListFragment.class.getSimpleName();
    private static final String weeklyUrl = "https://www.v2ex.com";

    private FanfouAdapter mFanfouAdapter;
    private ArrayList<Fanfou> mFanfous = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        new Thread(runnable).start();

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_item_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

//    private void show() {
//
//    }

//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            org.jsoup.Connection connection = Jsoup.connect(weeklyUrl);
//            connection.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
//            Document doc = null;
//
//            try {
//                doc = connection.get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Elements fanfouElement = doc.select("tbody tr");
//            Element nameElement = fanfouElement.select("a").first();
//            Element statusElement = fanfouElement.select("span").first();
//
//            String name = nameElement.attr("title");
//        }
//    };
//
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            show();
//        }
//    };


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext(), mFanfous);
        recyclerView.setAdapter(mFanfouAdapter);
    }

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, weeklyUrl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d(TAG, response.toString());
            try {
                JSONArray content = response.getJSONArray("msgs");
                for (int i = 0; i < content.length(); i++) {
                    Fanfou fanfou = new Fanfou();

                    JSONObject fan = (JSONObject) content.get(i);
                    String name = fan.getString("realname");
                    String avatar = fan.getString("avatar");
                    String time = fan.getString("time");
                    String status = fan.getString("msg");
                    JSONObject imageObj = fan.getJSONObject("img");
                    String img = imageObj.getString("preview");

                    fanfou.setScreenName(name);
                    fanfou.setImageUrl(avatar);
                    fan
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });
}
