package com.dobrodey.soft.android_senddatawificontrollers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.model.UDPSocket;

/**
 * Created by User on 25.08.2016.
 */
public class FragmentNetworkSettings extends Fragment {

    private EditText mInputIPControoler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network_settings, container, false);
        mInputIPControoler = (EditText) view.findViewById(R.id.id_edit_ip_controller);
        mInputIPControoler.setText(UDPSocket.ip);

        return view;
    }
}
