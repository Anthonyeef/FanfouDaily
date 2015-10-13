package io.github.anthonyeef.fanfoudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/13/15.
 */
public class FanfouAdapter extends RecyclerView.Adapter<FanfouAdapter.FanfouViewHolder>{

    private List<Fanfou> mFanfouList;
    private Context mContext;

    public FanfouAdapter(Context context, List<Fanfou> mFanfouList) {
        this.mFanfouList = mFanfouList;
        this.mContext = context;
    }

    public static class FanfouViewHolder extends RecyclerView.ViewHolder {

        protected TextView vScreenName;
        protected TextView vStatus;
        protected TextView vTimeStamp;
        protected TextView vFavourite;

        protected ImageView vImage;
        protected CircleImageView vAvatar;


    }
}
