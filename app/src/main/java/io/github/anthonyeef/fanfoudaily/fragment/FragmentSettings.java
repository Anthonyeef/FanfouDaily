package io.github.anthonyeef.fanfoudaily.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import io.github.anthonyeef.fanfoudaily.R;

/**
 * Created by anthonyeef on 11/11/15.
 */
public class FragmentSettings extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference pref_homepage;

    public FragmentSettings() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_prefs);

        pref_homepage = findPreference("fanfoudaily");
        pref_homepage.setOnPreferenceClickListener(this);

        getPreferenceScreen().setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                return false;
            }
        });
    }



    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (key == "fanfoudaily"){
            openWebsite(getString(R.string.project_address));
            return true;
        }
        return false;
    }

    private void openWebsite(String url) {
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(uri);
        startActivity(i);
    }
}
