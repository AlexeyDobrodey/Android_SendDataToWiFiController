package com.dobrodey.soft.android_senddatawificontrollers.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 04.08.2016.
 */
public abstract class AreaEntity implements Serializable{
    private static final long serialVersionUID = 777L;

    private int mWidth = 0;
    private int mHeight = 0;
    private List<Integer> mPosInitBytes = null;
    private String mOrientation;


    public AreaEntity(int width, int height,String orientation, List<Integer>posInitBytes) {
        this.mWidth = width;
        this.mHeight = height;
        this.mPosInitBytes = posInitBytes;
        this.mOrientation = orientation;
    }

    public boolean isVerticalOrientation() {
        return mOrientation.equals("vertical");
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public List<Integer> getPositionInitBytes() {
        return mPosInitBytes;
    }
    public void setPositionInitBytes(List<Integer> positionInitBytes) {mPosInitBytes = positionInitBytes; }

    public abstract String display(String hexStr);

    @Override
    public String toString() {
        return "width=" + mWidth + ", height=" + mHeight +", orientation=" + mOrientation +", initBytes" + mPosInitBytes.toString();
    }
}
