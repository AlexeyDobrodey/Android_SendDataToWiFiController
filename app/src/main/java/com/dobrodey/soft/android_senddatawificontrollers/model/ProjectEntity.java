package com.dobrodey.soft.android_senddatawificontrollers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 04.08.16.
 */
public class ProjectEntity implements Serializable{
    private static final long serialVersionUID = 777L;

    private String mTypeController;
    private String mColorType;

    private List<AreaEntity> mAreas;

    private List<String> mLabels;

    private Packets mPackets;

    public ProjectEntity(String typeController, String colorType) {
        this.mColorType = colorType;
        this.mTypeController = typeController;

        mAreas = new ArrayList<>();
        mLabels = new ArrayList<>();
        mPackets = new Packets(typeController);
    }


    // label is name AZS FUEL(A95, ДП), CE(USD, EUR,RUB)
    public void addLabelEntity(String label) {
        mLabels.add(label);
    }

    public void addAreaEntity(AreaEntity area) {
        mAreas.add(area);
    }

    public void addPacket(Packet p) {
        mPackets.add(p);
    }

    public List<AreaEntity> getAreas() {
        return mAreas;
    }

    public List<String> getLabels() {
        return mLabels;
    }


    public String getTypeController() {
        return mTypeController;
    }

    public void setTypeController(String mTypeController) {
        this.mTypeController = mTypeController;
    }

    public String getColorType() {
        return mColorType;
    }

    public void setColorType(String mColorType) {
        this.mColorType = mColorType;
    }

    public List<Packet> getPackets() {
        return mPackets.getPackets();
    }

    public String getHexStringFromPackets() {
        return mPackets.getHexStringFromPackets();
    }

    public void  saveHexStringToPackets(String hexStr) {
        mPackets.saveHexStringToPackets(hexStr);
    }

    @Override
    public String toString() {
        return "controllerType=" + mTypeController + ", colorType=" + mColorType;
    }
}
