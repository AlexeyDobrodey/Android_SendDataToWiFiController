package com.dobrodey.soft.android_senddatawificontrollers.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 11.08.2016.
 */
public class Packets implements Serializable{
    private static final long serialVersionUID = 777L;

    private List<Packet> mPackets;
    private int mEndPositionBytes;
    private int mStartPositionBytes;

    public Packets(String typeController) {
        mPackets = new ArrayList<>();
        initPositionChangeHexData(typeController);
    }

    public void add(Packet packet) {
        mPackets.add(packet);
    }

    public List<Packet> getPackets() {
        return mPackets;
    }

    //get data string which can change
    public String getHexStringFromPackets() {
        String hexStr = "";
        for(Packet p: mPackets) {
            if(p.isChange()) {
                hexStr += p.getHexString().substring(mStartPositionBytes,p.getHexString().length() - mEndPositionBytes);
            }
        }
        return hexStr;
    }

    public void saveHexStringToPackets(String hexStr) {
        for(Packet p: mPackets) {
            if(p.isChange()) {
                String hex_old = p.getHexString().substring(mStartPositionBytes,p.getHexString().length() - mEndPositionBytes);
                String hex_new = hexStr.substring(0, hex_old.length());

                p.setHexString(p.getHexString().replace(hex_old, hex_new));
                hexStr = hexStr.substring(hex_old.length(), hexStr.length());
            }
        }
    }

    // init start and end hexString for change data
    private void initPositionChangeHexData(String typeController) {
        if(typeController.equals("BX5") || typeController.equals("CX5"))
            mEndPositionBytes = 4;
        else
            mEndPositionBytes = 6;
        mStartPositionBytes = 48;
    }

    @Override
    public String toString() {
        return mPackets.toString();
    }
}
