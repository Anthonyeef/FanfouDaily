package io.github.anthonyeef.fanfoudaily.callbacks;

import java.util.ArrayList;

import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/17/15.
 */
public interface FanfouLoadedListener {
    public void onFanfouLoaded(ArrayList<Fanfou> fanfous);
}
