package io.github.anthonyeef.fanfoudaily.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.PagerAdapter;
import io.github.anthonyeef.fanfoudaily.fragment.FragmentDaily;
import io.github.anthonyeef.fanfoudaily.fragment.FragmentWeekly;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tabLayout.setupWithViewPager(viewPager);

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

    public void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentDaily(), "每日精选");
        adapter.addFragment(new FragmentWeekly(), "每周精选");
        viewPager.setAdapter(adapter);
    }
}
