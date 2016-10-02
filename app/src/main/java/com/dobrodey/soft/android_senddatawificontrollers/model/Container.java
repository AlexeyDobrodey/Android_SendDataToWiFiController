package com.dobrodey.soft.android_senddatawificontrollers.model;

import android.util.Log;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.09.2016.
 */
public class Container {
    private List<TextAreaEntity> mAreas;
    private int mWidth;
    private int mHeight;

    public Container(int height) {
        mAreas = new ArrayList<>();
        mWidth = 0;
        mHeight = height;
    }

    public void addArea(TextAreaEntity area) {
        mAreas.add(area);
        mWidth += area.getWidth();
    }

    public String display(String hexStr) {
        List<String> hexResult = new ArrayList<>();

        for(TextAreaEntity areaEntity : mAreas) {
            hexResult.add(areaEntity.getEncryptionDate());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <=mHeight; i++) {
            for(String hex: hexResult) {
                sb.append(hex.substring((hex.length() / mHeight) * (i - 1), (hex.length() / mHeight) * i));
            }
        }

        return Utils.writeDataToPacket(mAreas.get(0).getPositionInitBytes(), sb.toString(), hexStr);
    }

    public void clear() {
        mAreas.clear();
    }
}
