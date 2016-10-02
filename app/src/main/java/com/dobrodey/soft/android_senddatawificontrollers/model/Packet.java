package com.dobrodey.soft.android_senddatawificontrollers.model;

import java.io.Serializable;

/**
 * Created by User on 11.08.2016.
 */
public class Packet implements Serializable{
    private static final long serialVersionUID = 777L;

    private String mHexString;
    private boolean mChange;

    public Packet(boolean change, String hexString) {
        this.mChange  = change;
        this.mHexString = hexString;
    }

    public String getHexString() {
        return mHexString;
    }

    public void setHexString(String mHexString) {
        this.mHexString = mHexString;
    }

    public boolean isChange() {
        return mChange;
    }

    @Override
    public String toString() {
        return "hex:" + mHexString + ", change" + mChange;
    }
}
