package com.dobrodey.soft.android_senddatawificontrollers.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

/**
 * Created by User on 23.08.2016.
 */
public class DialogInputPassword extends DialogFragment{
    private EditText mEditInputPass;

    public interface OnClickDialogResult {
        public void onClickResultDialog(boolean result, int resource);
    }

    OnClickDialogResult mListener;

    private int mResourceItemMenuSelect;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input_pass, null);
        mEditInputPass = (EditText) view.findViewById(R.id.edit_input_password);

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.title_dialog_input_pass)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(Utils.PASSWORD_SETTINGS.equals(mEditInputPass.getText().toString())) {
                            mListener.onClickResultDialog(true, mResourceItemMenuSelect);
                        }
                        Utils.hideKeyboard(getActivity());
                    }
                })
                .create();
    }

    public void setIDResourceItemMenuSelect(int resourceItemMenuSelect) {
        mResourceItemMenuSelect = resourceItemMenuSelect;
    }
    public void setOnClickListener(OnClickDialogResult listener) {
        mListener = listener;
    }
}
