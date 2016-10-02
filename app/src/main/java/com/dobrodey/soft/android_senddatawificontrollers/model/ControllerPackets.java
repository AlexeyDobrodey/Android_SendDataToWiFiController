package com.dobrodey.soft.android_senddatawificontrollers.model;

import android.util.Log;

import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alex on 03.09.2016.
 */
public class ControllerPackets {
    public static  void changeTimeLuminance(TimeLuminance timeLuminance) {
        StringBuilder sbTimeLum = new StringBuilder(sTimeLuminancePackets[1]);

        List<String> times = timeLuminance.getTimes();
        List<Integer> luminances = timeLuminance.getLuminances();

        for(int i = 0 ; i < luminances.size(); i++) {
            Utils.writeSampleToHexPackage(24 + i * 2, Utils.correctHexString(Integer.toHexString(luminances.get(i))), sbTimeLum);
        }

        for(int i = 0; i < times.size(); i++) {
            String time[] = times.get(i).split(":");
            Utils.writeSampleToHexPackage(32 + i * 8, getHexOfLuminanceChangeTime(Integer.parseInt(time[0]), Integer.parseInt(time[1])), sbTimeLum);
        }

        sTimeLuminancePackets[1] = sbTimeLum.toString();
    }

    public static  void changeTime(Calendar calendar) {

    }

    private  static String getHexOfLuminanceChangeTime(int hour, int minute) {
        int seconds = Utils.toSeconds(hour, minute);
        String hexTime = Integer.toHexString(seconds);
        return encryptHex(hexTime);
    }

    private static String encryptHex(String hexStr) {
        hexStr = Utils.correctHexString(hexStr);

        StringBuilder result = new StringBuilder();
        for (int i = hexStr.length() - 1; i > 0; i -= 2) {
            result.append(hexStr.charAt(i - 1));
            result.append(hexStr.charAt(i));
        }

        switch(result.length()) {
            case 2: {result.append("000000"); break;}
            case 4: { result.append("0000"); break;}
            case 6: { result.append("00");   break;}
        }
        return result.toString();
    }

}
