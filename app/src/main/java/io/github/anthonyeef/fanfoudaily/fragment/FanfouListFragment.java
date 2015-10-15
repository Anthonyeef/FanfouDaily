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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private static final String weeklyUrl = "http://blog.fanfou.com/digest/json/2015-10-14.daily.json";

    private FanfouAdapter mFanfouAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fetchData(weeklyUrl);

        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_item_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        mFanfouAdapter = new FanfouAdapter(getContext(), mFanfous);
        recyclerView.setAdapter(mFanfouAdapter);
    }


    private void fetchData(String url) {
         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    JSONArray message = response.getJSONArray("msgs");
                    ArrayList<Fanfou> mFanfous = new ArrayList<Fanfou>(message.length());
                    for (int i = 0; i < message.length(); i++) {
                        Fanfou fan = new Fanfou();
                        JSONObject fanfou = (JSONObject)message.get(i);

                        String name = fanfou.getString("realname");
                        String avatar = fanfou.getString("avatar");
                        String msg = fanfou.getString("msg");

                        JSONObject image = fanfou.getJSONObject("img");
                        String img = "";
                        img = image.getString("preview");

                        fan.setScreenName(name);
                        fan.setAvatarUrl(avatar);
                        fan.setStatus(msg);
                        fan.setImageUrl(img);

                        mFanfous.add(fan);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error :" + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }
}
