package io.github.anthonyeef.fanfoudaily.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.github.anthonyeef.fanfoudaily.R;
import io.github.anthonyeef.fanfoudaily.adapter.PagerAdapter;
import io.github.anthonyeef.fanfoudaily.fragment.FragmentDaily;
import io.github.anthonyeef.fanfoudaily.fragment.FragmentWeekly;
import io.github.anthonyeef.fanfoudaily.model.Date;

public class UIHome extends AppCompatActivity {

//    @Bind(R.id.viewPager) ViewPager viewPager;
    public static ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;

    public static boolean isFABinCurrentTabVisible = true;
    FragmentDaily fragmentDaily = new FragmentDaily();
    FragmentWeekly fragmentWeekly = new FragmentWeekly();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.setCatchUncaughtExceptions(false);

        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tabLayout.setupWithViewPager(viewPager);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });


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
            Intent i = new Intent(this, UIPreference.class);
            startActivity(i);
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
    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new FragmentDatePicker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void setupViewPager(final ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentDaily, "每日精选");
        adapter.addFragment(fragmentWeekly, "每周精选");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        isFABinCurrentTabVisible = true;
                        mFloatingActionButton.show();
                        break;
                    default:
//                        isFABinCurrentTabVisible = false;
                        mFloatingActionButton.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static class FragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
//            Toast.makeText(getContext(), "You just select" + year + month + day, Toast.LENGTH_SHORT).show();
            Calendar pickDate = Calendar.getInstance();
            pickDate.set(year, month, day);

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String fdate = format1.format(pickDate.getTime());

            Date date = new Date(fdate);
            EventBus.getDefault().post(date);
        }


    }
}
