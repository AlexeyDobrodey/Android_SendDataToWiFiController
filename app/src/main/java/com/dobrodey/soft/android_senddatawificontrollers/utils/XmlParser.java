package com.dobrodey.soft.android_senddatawificontrollers.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.model.AreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.DataObject;
import com.dobrodey.soft.android_senddatawificontrollers.model.Packet;
import com.dobrodey.soft.android_senddatawificontrollers.model.ProjectEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.Seasons;
import com.dobrodey.soft.android_senddatawificontrollers.model.TextAreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.TimeLuminance;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 04.08.16.
 */
public class XmlParser {

    public static final String TAG = XmlParser.class.getName();

    public static List<ProjectEntity> loadProjectFromXML(Context ctx) {
        XmlPullParser xmlParser = ctx.getResources().getXml(R.xml.main_oleg_2);

        List<ProjectEntity> projects = new ArrayList<>();
        ProjectEntity projectEntity = null;
        AreaEntity area = null;
        List<Integer> positionsBytes = null;

        try {
            while(xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xmlParser.getEventType()) {
                    case XmlPullParser.START_TAG : {
                        switch (xmlParser.getName()) {
                            case "project": {
                                projectEntity = new ProjectEntity(xmlParser.getAttributeValue(0), xmlParser.getAttributeValue(1));
                                break;
                            }
                            case "area":{
                                area = new TextAreaEntity(Integer.valueOf(xmlParser.getAttributeValue(0)),Integer.valueOf(xmlParser.getAttributeValue(1)),
                                        xmlParser.getAttributeValue(2), xmlParser.getAttributeValue(3), xmlParser.getAttributeValue(4),
                                        Integer.valueOf(xmlParser.getAttributeValue(5)),Integer.valueOf(xmlParser.getAttributeValue(6)),
                                        Integer.valueOf(xmlParser.getAttributeValue(7)), projectEntity.getColorType());
                                break;
                            }
                            case "positionsOfInitialBytes":{
                                positionsBytes = new ArrayList<>();
                                break;
                            }
                            case "positionInitialBytes": {
                                positionsBytes.add(Integer.valueOf(xmlParser.nextText()));
                                break;
                            }
                            case "label": {
                                projectEntity.addLabelEntity(xmlParser.nextText());
                                break;
                            }
                            case "packet": {
                                Packet p = new Packet(Boolean.valueOf(xmlParser.getAttributeValue(0)), xmlParser.nextText());
                                projectEntity.addPacket(p);
                                break;
                            }

                        }
                        break;
                    }
                    case XmlPullParser.END_TAG : {
                        switch (xmlParser.getName()) {
                            case "project": {
                                projects.add(projectEntity);
                                break;
                            }
                            case "area":{
                                projectEntity.addAreaEntity(area);
                                break;
                            }
                            case "positionsOfInitialBytes":{
                                area.setPositionInitBytes(positionsBytes);
                                break;
                            }
                        }
                        break;
                    }
                }
                xmlParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return projects;
    }

    public static Seasons loadSeasonsFromXML(Context ctx){
        XmlPullParser xmlParser = ctx.getResources().getXml(R.xml.time_luminance);
        Seasons seasons = null;
        String nameSeason = "";
        TimeLuminance timeLuminance = null;
        try {
            while(xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xmlParser.getEventType()) {
                    case XmlPullParser.START_TAG : {
                        switch (xmlParser.getName()) {
                            case "Seasons": {
                                seasons = new Seasons();
                                break;
                            }
                            case "Season": {
                                nameSeason = xmlParser.getAttributeValue(0);
                                timeLuminance = new TimeLuminance();
                                break;
                            }
                            case "time": {
                                timeLuminance.addTime(xmlParser.nextText());
                                break;
                            }
                            case "luminance": {
                                timeLuminance.addLuminance(Float.valueOf(xmlParser.nextText()));
                                break;
                            }
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG : {
                        switch (xmlParser.getName()) {
                            case "Season": {
                                Log.d(MainActivity.TAG, nameSeason);
                                seasons.addTimeLuminance(timeLuminance, nameSeason);
                                break;
                            }
                        }
                        break;
                    }
                }
                xmlParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return seasons;
    }
}
