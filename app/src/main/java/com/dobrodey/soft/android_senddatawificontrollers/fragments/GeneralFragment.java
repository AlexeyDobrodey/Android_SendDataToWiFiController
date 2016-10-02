package com.dobrodey.soft.android_senddatawificontrollers.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.model.AreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.ControllerPackets;
import com.dobrodey.soft.android_senddatawificontrollers.model.DataObject;
import com.dobrodey.soft.android_senddatawificontrollers.model.Packet;
import com.dobrodey.soft.android_senddatawificontrollers.model.UDPSocket;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sergey on 14.08.2016.
 */
public abstract class GeneralFragment extends Fragment {
    public RecyclerView mRV;
    public RecyclerView.Adapter mAdapter;

    public UDPSocket udpSocket;
    protected DataObject mDataObject;
    protected List<AreaEntity> mAreas;

    public Button mBtnSend;

    public GeneralFragment() {
        udpSocket = new UDPSocket();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mDataObject = DataObject.getInstance(getContext());
            mAreas = mDataObject.getCurrentProject().getAreas();
        } catch (IOException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ce_azs, container, false);

        mRV = (RecyclerView) view.findViewById(R.id.id_rv);
        mRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBtnSend = (Button) view.findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceChange();
            }
        });

        updateRV();
        return view;
    }


    protected void sendPacketsToController() {
        List<Packet> packets = mDataObject.getCurrentProject().getPackets();
        MyTask task = new MyTask(getContext());

        task.execute(packets.toArray(new Packet[packets.size()]));

        try {
            mDataObject.save(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void updateRV();

    public abstract void priceChange();

    class MyTask extends AsyncTask<Packet, String, Boolean> {
        private ProgressDialog mProgressDialog;

        public MyTask(Context ctx) {

            mProgressDialog = new ProgressDialog(ctx);
            mProgressDialog.setTitle("Ожидайте");
            mProgressDialog.setMessage("Идет отправка данных...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
            mBtnSend.setEnabled(false);
        }

        @Override
        protected Boolean doInBackground(Packet ...packets) {
            boolean mResult = false;
            try {
                udpSocket.openUDPSocket();
                boolean result = udpSocket.sendUDPSocket(packets[0].getHexString());
                if (result) {
                    for (int i = 1; i < packets.length; i++) {
                        udpSocket.sendUDPSocket(packets[i].getHexString());
                    }

                    publishProgress("Установка яркости...");

                    for(String packet : ControllerPackets.sTimeLuminancePackets) {
                        udpSocket.sendUDPSocket(packet);
                    }
                    publishProgress("Установка времени...");

                    for(String packet : ControllerPackets.sTimeFixed) {
                        udpSocket.sendUDPSocket(packet);
                    }

                    mResult = true;
                }
                else {
                    Log.d(MainActivity.TAG, "нет ответа от контроллера!!!!!!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                udpSocket.closeUDPSocket();
            }
            return mResult;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mProgressDialog.setMessage(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mBtnSend.setEnabled(true);

            if(result) {
                Toast.makeText(getContext(), "Успех", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getContext(), "Ошибка отправки данных", Toast.LENGTH_LONG).show();
            }
        }
    }
}
