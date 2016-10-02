package com.dobrodey.soft.android_senddatawificontrollers.activities;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.dialogs.DialogInputPassword;
import com.dobrodey.soft.android_senddatawificontrollers.dialogs.DialogNetworkSettings;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentAZS;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentCE;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.FragmentCENewLogic;
import com.dobrodey.soft.android_senddatawificontrollers.fragments.GeneralFragment;
import com.dobrodey.soft.android_senddatawificontrollers.model.UDPSocket;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    public static final String TAG_DIALOG_INPUT_PASS = "dialog_input_pass";
    public static final String TAG_DIALOG_NETWORK_IP = "dialog_input_ip";


    private GeneralFragment generalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                    }
                    , 1);
        }

        setContentView(R.layout.activity_main);
        UDPSocket.ip = Utils.loadIP(this);

        FragmentManager fm = getSupportFragmentManager();
        generalFragment = (GeneralFragment) fm.findFragmentById(R.id.frame_container);

        if (generalFragment == null) {
            generalFragment = new FragmentCENewLogic();
            fm.beginTransaction()
                    .add(R.id.frame_container, generalFragment)
                    .commit();
        }
    }
}
