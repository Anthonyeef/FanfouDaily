package io.github.anthonyeef.fanfoudaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.model.Fanfou;

/**
 * Created by anthonyeef on 10/31/15.
 */
public class UIStatus extends AppCompatActivity{

    @Bind(R.id.statustoolbar) Toolbar mToolbar;
    @Bind(R.id.avatar1) CircleImageView avatar1;
    @Bind(R.id.name1) TextView name1;
    @Bind(R.id.timestamp1) TextView timestamp1;
    @Bind(R.id.status1) TextView status1;
    @Bind(R.id.image1) ImageView image1;
    @Bind(R.id.imageprogress) ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();


        setContentView(R.layout.activity_status);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
//        mProgressBar.setVisibility(View.VISIBLE);

        Fanfou feed = (Fanfou) intent.getParcelableExtra("feed");

        name1.setText(feed.getScreenName());
        timestamp1.setText(feed.getTimeStamp());
        status1.setText(feed.getStatus());

        Picasso.with(this).load(feed.getAvatarUrl())
                .into(avatar1);
        if (!feed.getImageUrl().equals("")) {
            Picasso.with(this).load(feed.getImageUrl())
//                    .fit().centerInside()
                    .into(image1, new ImageLoadedCallback(mProgressBar) {
                @Override
                public void onSuccess() {
                    if (this.mProgressBar != null) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar mProgressBar;

        public ImageLoadedCallback(ProgressBar progressBar) {
            mProgressBar = progressBar;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}
