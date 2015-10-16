package io.github.anthonyeef.fanfoudaily.json;

import org.json.JSONObject;

/**
 * Created by anthonyeef on 10/16/15.
 */
public class Utils {
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && jsonObject.isNull(key) ? true : false;
    }
}
