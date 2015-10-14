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
import com.android.volley.toolbox.StringRequest;

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
        fetchData();
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

    public void fetchData() {
        StringRequest request = new StringRequest(Request.Method.GET, weeklyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.v(TAG, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error:", error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request, TAG);
    }

    private void parseData(String source) {
        try {
            Document doc = Jsoup.parse(source);
            Element content = doc.select("div.content").first();
            Element body = content.select("tbody").first();
            Elements fanfouElement = body.select("td.item");

            for (int i = 0; i < fanfouElement.size(); i++) {
                Fanfou fanfou = new Fanfou();

                Element feed = fanfouElement.get(i);
                Element avatar = feed.select("div.avatar a[href]").first();
                Element nameElement = feed.select("div.avatar a[title]").first();
                Element statusElement = feed.select("div.text-outer span.text").first();
                Element imageElement = feed.select("div.text-outer span.photo a img").first();

                fanfou.setScreenName(nameElement.text());
                fanfou.setAvatarUrl(avatar.text());
                fanfou.setStatus(statusElement.text());

                String image;
                if (imageElement.attr("src") != "") {
                    image = imageElement.attr("src");
                } else {
                    image = null;
                }

                fanfou.setImageUrl(image);

                mFanfous.add(fanfou);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mFanfouAdapter.notifyDataSetChanged();
    }
}
