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

/**
 * Created by anthonyeef on 10/13/15.
 */
public class FanfouAdapter extends RecyclerView.Adapter<FanfouAdapter.FanfouViewHolder>{

    private ArrayList<Fanfou> mFanfouList;
    private Context mContext;

    public FanfouAdapter(Context context, ArrayList<Fanfou> mFanfouList) {
        this.mFanfouList = mFanfouList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mFanfouList.size();
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

        if (current.getImageUrl() == null) {
            fanfouViewHolder.vImage.setVisibility(View.GONE);
        } else {
            Uri image_uri = Uri.parse(current.getImageUrl());
            Context context1 = fanfouViewHolder.vImage.getContext();

            Picasso.with(context1).load(image_uri)
                    .into(fanfouViewHolder.vImage);
        }
    }

    @Override
    public FanfouViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fanfou_item, viewGroup, false);
        return new FanfouViewHolder(itemView);
    }

    public static class FanfouViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.avatar) CircleImageView vAvatar;
        @Bind(R.id.name) TextView vScreenName;
        @Bind(R.id.timestamp) TextView vTimeStamp;
        @Bind(R.id.status) TextView vStatus;
        @Bind(R.id.feedImage) ImageView vImage;

        public FanfouViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
