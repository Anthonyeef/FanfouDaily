package io.github.anthonyeef.fanfoudaily.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;
import io.github.anthonyeef.fanfoudaily.network.VolleySingleton;

/**
 * Created by anthonyeef on 10/13/15.
 */
public class FanfouAdapter extends RecyclerView.Adapter<FanfouAdapter.FanfouViewHolder>{

    private ArrayList<Fanfou> mFanfouList = new ArrayList<>();
    private VolleySingleton mVolleySingleton;
    private LayoutInflater mInflater;
    private Context mContext;

    public FanfouAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mContext = context;
    }

    public void setFanfous(ArrayList<Fanfou> listFanfous) {
        this.mFanfouList = listFanfous;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFanfouList.size();
    }


    class FanfouViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.avatar) CircleImageView vAvatar;
        @Bind(R.id.name) TextView vScreenName;
        @Bind(R.id.timestamp) TextView vTimeStamp;
        @Bind(R.id.status) TextView vStatus;
        @Bind(R.id.imageflag) ImageView vImageflag;

        Fanfou mFanfou;
        View feed;

        public FanfouViewHolder(View itemView) {
            super(itemView);
            feed = itemView;
            ButterKnife.bind(this, itemView);
        }

   }
    @Override
    public void onBindViewHolder(final FanfouViewHolder fanfouViewHolder, int location){
        Fanfou current = mFanfouList.get(location);

        Uri avatar_uri = Uri.parse(current.getAvatarUrl());
        Context context = fanfouViewHolder.vAvatar.getContext();
        Picasso.with(context).load(avatar_uri)
                .into(fanfouViewHolder.vAvatar);

        fanfouViewHolder.vScreenName.setText(current.getScreenName());
        fanfouViewHolder.vTimeStamp.setText(current.getTimeStamp());
        fanfouViewHolder.vStatus.setText(current.getStatus());
        fanfouViewHolder.mFanfou = current;
        fanfouViewHolder.vImageflag.setImageResource(R.drawable.ic_photo_24dp);

        if (current.getImageUrl().equals("")) {
            fanfouViewHolder.vImageflag.setImageDrawable(null);
        }
    }

    @Override
    public FanfouViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.card_list_item, viewGroup, false);
        final FanfouViewHolder holder = new FanfouViewHolder(itemView);
        return holder;
    }


}
