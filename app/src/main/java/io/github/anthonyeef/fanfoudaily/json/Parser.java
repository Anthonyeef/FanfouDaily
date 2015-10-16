package io.github.anthonyeef.fanfoudaily.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.extras.Constants;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_AVATAR;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_IMG;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_MESSAGE;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_PREVIEW;
import static io.github.anthonyeef.fanfoudaily.extras.Keys.ComeoutSomeKeys.KEY_REALNAME;

/**
 * Created by anthonyeef on 10/16/15.
 */
public class Parser {
    public static ArrayList<Fanfou> parseFanfouJSON(JSONObject response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Fanfou> fanfous = new ArrayList<Fanfou>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray message = response.getJSONArray("msgs");
                for (int i = 0; i < message.length(); i++) {
                    String name = Constants.NA;
                    String avatar = Constants.NA;
                    String msg = Constants.NA;
                    String img = Constants.NA;

                    JSONObject fanfou = message.getJSONObject(i);

                    if (Utils.contains(fanfou, KEY_REALNAME)) {
                        name = fanfou.getString(KEY_REALNAME);
                    }
                    if (Utils.contains(fanfou, KEY_AVATAR)) {
                        avatar = fanfou.getString(KEY_AVATAR);
                    }

                    if (Utils.contains(fanfou, KEY_MESSAGE)) {
                        msg = fanfou.getString(KEY_MESSAGE);
                    }
                    if (Utils.contains(fanfou.getJSONObject(KEY_IMG), KEY_PREVIEW)) {
                        img = fanfou.getJSONObject(KEY_IMG).getString(KEY_PREVIEW);
                    }

                    Fanfou fan = new Fanfou();

                    fan.setScreenName(name);
                    fan.setAvatarUrl(avatar);
                    fan.setStatus(msg);
                    fan.setImageUrl(img);

                    fanfous.add(fan);
                }
            } catch (JSONException e) {

            }
        }
        return fanfous;
    }
}
