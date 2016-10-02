package com.dobrodey.soft.android_senddatawificontrollers.model;

/**
 * Created by User on 23.08.2016.
 */
public class FuelObject {
    private String mName;
    private String mValue;

    public FuelObject(String nameFuel, String valueFuel) {
        this.mName = nameFuel;
        this.mValue = valueFuel;
    }

    public String getValue() {
        return mValue;
    }

    public String getName() {
        return mName;
    }

    public void setValue(String valueFuel) {
        this.mValue = valueFuel;
    }

    public void setName(String nameFuel) {
        this.mName = nameFuel;
    }
}
