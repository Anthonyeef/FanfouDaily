package io.github.anthonyeef.fanfoudaily.logging;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by anthonyeef on 10/17/15.
 */
public class LogUtils {
    public static void m(String message) {
        Log.d("FanfouDaily :", "" + message);
    }

    public static void t(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }

    public static void T(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
    }
}
