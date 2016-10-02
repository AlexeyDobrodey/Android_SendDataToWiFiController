package com.dobrodey.soft.android_senddatawificontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentLuminanceSetting;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentNetworkSettings;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentTimeSetting;

/**
 * Created by Sergey on 26.08.2016.
 */
public class SettingsActivity extends AppCompatActivity {

    private static final String EXTRA_DATA_SETTINGS = "extra_data_settings";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        int idResourceItemSettings = getIntent().getExtras().getInt(EXTRA_DATA_SETTINGS);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame_container_settings);

        if(fragment == null) {
            fragment = getFragmentSettings(idResourceItemSettings);
            fm.beginTransaction()
                    .add(R.id.frame_container_settings, fragment)
                    .commit();
        }
    }

    private Fragment getFragmentSettings(int idResourceItemSettings) {
        switch(idResourceItemSettings) {
            case R.id.menu_item_time_luminance: {
                return new FragmentLuminanceSetting();
            }
            case R.id.menu_item_network: {
                return new FragmentNetworkSettings();
            }
            case R.id.menu_item_adjust_time: {
                return new FragmentTimeSetting();
            }
        }
        return null;
    }

    public static Intent newInstance(Context ctx, int idResItemSettings) {
        Intent intent = new Intent(ctx, SettingsActivity.class);
        intent.putExtra(EXTRA_DATA_SETTINGS, idResItemSettings);
        return intent;
    }
}
