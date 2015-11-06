package io.github.anthonyeef.fanfoudaily.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by anthonyeef on 11/7/15.
 */
public class HttpUtils {


    public static boolean isNetworkConnected (Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
