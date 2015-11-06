package io.github.anthonyeef.fanfoudaily.volley;

import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.model.Fanfou;

import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_AVATAR;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_IMG;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_MESSAGE;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_PREVIEW;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_REALNAME;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_TIME;
/**
 * Created by anthonyeef on 10/16/15.
 */
public class Parser {
    public static ArrayList<Fanfou> parseFanfouJSON(JSONObject response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Fanfou> fanfous = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray message = response.getJSONArray("msgs");
                for (int i = 0; i < message.length(); i++) {
                    String name;
                    String avatar;
                    String msg;
                    String img;
                    String time;

                    JSONObject fanfou = message.getJSONObject(i);
                    name = fanfou.getString(KEY_REALNAME);
                    avatar = fanfou.getString(KEY_AVATAR);
                    time = fanfou.getString(KEY_TIME);
                    msg = fanfou.getString(KEY_MESSAGE);

                    img = fanfou.getJSONObject(KEY_IMG).getString(KEY_PREVIEW);
                    if (!img.equals("")) {
                        img = img.replaceFirst("m0", "n0");
                    }

                    Fanfou fan = new Fanfou();

                    fan.setScreenName(name);
                    fan.setAvatarUrl(avatar);
                    fan.setStatus(Html.fromHtml(msg).toString());
                    fan.setImageUrl(img);
                    fan.setTimeStamp(time);

                    if (!name.equals(null)) {
                        fanfous.add(fan);
                    }
                }
            } catch (JSONException e) {

            }
        }
        return fanfous;
    }
}
