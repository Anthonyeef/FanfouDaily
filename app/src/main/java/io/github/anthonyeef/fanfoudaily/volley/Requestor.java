package io.github.anthonyeef.fanfoudaily.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.github.anthonyeef.fanfoudaily.Utils.LogUtils;

/**
 * Created by anthonyeef on 10/16/15.
 */

public class Requestor {
    public static JSONObject requestJSON(RequestQueue requestQueue, String url) {
        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                (String)null,
                requestFuture, requestFuture);

        requestQueue.add(request);
        try {
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LogUtils.m(e + "");
        } catch (ExecutionException e) {
            LogUtils.m(e + "");
        } catch (TimeoutException e) {
            LogUtils.m(e + "");
        }
        return response;
    }
}
