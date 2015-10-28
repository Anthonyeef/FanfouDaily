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
    }

    public void setFanfous(ArrayList<Fanfou> listFanfous) {
        this.mFanfouList = listFanfous;
        notifyDataSetChanged();
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
        View itemView = mInflater.inflate(R.layout.fanfou_item, viewGroup, false);
        return new FanfouViewHolder(itemView);
    }

    static class FanfouViewHolder extends RecyclerView.ViewHolder {

        CircleImageView vAvatar;
        TextView vScreenName;
        TextView vTimeStamp;
        TextView vStatus;
        ImageView vImage;
//        @Bind(R.id.avatar) CircleImageView vAvatar;
//        @Bind(R.id.name) TextView vScreenName;
//        @Bind(R.id.timestamp) TextView vTimeStamp;
//        @Bind(R.id.status) TextView vStatus;
//        @Bind(R.id.feedImage) ImageView vImage;

        public FanfouViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, view);
            vAvatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            vScreenName = (TextView) itemView.findViewById(R.id.name);
            vTimeStamp = (TextView) itemView.findViewById(R.id.timestamp);
            vStatus = (TextView) itemView.findViewById(R.id.status);
            vImage = (ImageView) itemView.findViewById(R.id.feedImage);
        }

   }
}
