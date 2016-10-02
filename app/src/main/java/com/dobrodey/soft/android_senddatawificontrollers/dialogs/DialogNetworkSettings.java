package com.dobrodey.soft.android_senddatawificontrollers.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.model.UDPSocket;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

/**
 * Created by User on 29.08.2016.
 */
public class DialogNetworkSettings extends DialogFragment {
    private EditText mInputIP;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_network_settings, null);
        mInputIP = (EditText) view.findViewById(R.id.id_edit_ip_controller);
        mInputIP.setText(UDPSocket.ip);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Настройки IP")
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!mInputIP.getText().equals(UDPSocket.ip)) {
                            UDPSocket.ip = mInputIP.getText().toString();
                            Utils.saveIP(getActivity(), UDPSocket.ip);
                        }
                    }
                })
                .create();
    }
}
