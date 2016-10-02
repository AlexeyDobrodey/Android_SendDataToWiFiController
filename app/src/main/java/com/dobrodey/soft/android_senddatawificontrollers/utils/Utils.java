package com.dobrodey.soft.android_senddatawificontrollers.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.model.DataObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Sergey on 14.08.2016.
 */
public class Utils {
    public static final String FILE_NAME_OBJECT_DATA = "data.bin";
    public static final String PASSWORD_SETTINGS = "888";
    private static final String SAVED = "saved";

    public static final String[] SEASONS = {"Winter", "Winter", "Spring",
                                             "Spring", "Spring", "Summer",
                                             "Summer", "Summer", "Autumn",
                                             "Autumn", "Autumn", "Winter"};

    public static boolean isFileDataExists(Context ctx) {
        File f = ctx.getFileStreamPath(FILE_NAME_OBJECT_DATA);
        if(!f.exists())
            return false;
        return true;
    }

    public static void serializationDataObject(DataObject dataObject, Context ctx) throws IOException {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(ctx.openFileOutput(FILE_NAME_OBJECT_DATA,
                    Context.MODE_PRIVATE)));
            oos.writeObject(dataObject);
            oos.close();
    }

    public static  DataObject deserializationDataObject(Context ctx) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(ctx.openFileInput(FILE_NAME_OBJECT_DATA)));
        Object obj = ois.readObject();
        ois.close();
        return (DataObject) obj;
    }

    public static void hideKeyboard(Activity activity) {
        if(activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            Log.d(MainActivity.TAG, "YES!!! KeyBoard is HIDE!!!");
        }
    }

    public static String loadIP(Activity activity) {
        SharedPreferences pref = activity.getPreferences(Activity.MODE_PRIVATE);
        String ipString = pref.getString(SAVED, "");

        if(ipString.equals(""))
            ipString = activity.getResources().getString(R.string.IP_DEFAULT);

        return ipString;
    }

    public static void saveIP(Activity activity, String newIP) {
        SharedPreferences pref = activity.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SAVED, newIP);
        editor.commit();
    }

    public static String writeDataToPacket(List<Integer> position, String sampleHex, String hexStr) {
        for(int pos : position) {
            hexStr = hexStr.substring(0, pos) + sampleHex + hexStr.substring(sampleHex.length() + pos, hexStr.length());
        }
        return hexStr;
    }

    public static int toSeconds(int hour, int minute) {
        return hour * 3600 + minute * 60;
    }

    public static String correctHexString(String hexStr) {
        if(hexStr.length() % 2 != 0) {
            hexStr = "0" + hexStr;
        }
        return hexStr;
    }

    public  static void writeSampleToHexPackage(int startByte, String sample, StringBuilder hexPackage) {
        for(int i = startByte, j = 0; i < startByte + sample.length(); i++, j++) {
            hexPackage.setCharAt(i, sample.charAt(j));
        }
    }
}
