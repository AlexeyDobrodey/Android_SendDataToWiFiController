package com.dobrodey.soft.android_senddatawificontrollers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 30.08.2016.
 */
public class TimeLuminance implements Serializable {
    private static final long serialVersionUID = 777L;

    private List<Integer> mLuminances;
    private List<String> mTimes;

    public TimeLuminance() {
        mLuminances = new ArrayList<>();
        mTimes = new ArrayList<>();
    }

    public void addTime(String time) {
        mTimes.add(time);
    }

    public void addLuminance(float luminance) {
        mLuminances.add(((int) (0.16f * luminance)));
    }

    public List<String> getTimes() {
        return mTimes;
    }

    public List<Integer> getLuminances() {
        return mLuminances;
    }

    @Override
    public String toString() {
        String result = "";

        for(int i = 0 ; i < mLuminances.size(); i++) {
            result += mTimes.get(i) + "---" + mLuminances.get(i) + "\n";
        }

        return result;
    }
}
