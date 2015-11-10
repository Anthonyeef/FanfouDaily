package io.github.anthonyeef.fanfoudaily.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.anthonyeef.fanfoudaily.R;


/**
 * Created by anthonyeef on 11/10/15.
 */
public class FragmentPreference extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_prefs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_pref, container,
                false);
    }
}
