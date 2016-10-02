package com.dobrodey.soft.android_senddatawificontrollers.model;

import android.content.Context;
import android.util.Log;

import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;
import com.dobrodey.soft.android_senddatawificontrollers.utils.XmlParser;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.XMLFormatter;

/**
 * Created by Alexey on 11.08.2016.
 */
public class DataObject implements Serializable{
    private static final long serialVersionUID = 777L;

    public static DataObject sInstance = null;

    private List<ProjectEntity> mProjectEntities = null;
    private ProjectEntity mCurrenProject = null;
    private Seasons mSeasons = null;

    private DataObject(){

    }

    public static DataObject getInstance(Context ctx) throws IOException, ClassNotFoundException {
        if(sInstance == null) {
            if(!Utils.isFileDataExists(ctx)) {
                sInstance = new DataObject();
                sInstance.mProjectEntities = XmlParser.loadProjectFromXML(ctx);
                sInstance.mSeasons = XmlParser.loadSeasonsFromXML(ctx);
                Log.d(MainActivity.TAG, "Season init");

                sInstance.mCurrenProject = sInstance.mProjectEntities.get(0);
                sInstance.save(ctx);
            }
            else {
                sInstance = Utils.deserializationDataObject(ctx);
            }
        }
        return sInstance;
    }

    public void save(Context ctx) throws IOException {
        Utils.serializationDataObject(this, ctx);
    }

    public ProjectEntity getCurrentProject() {
        return mCurrenProject;
    }

    public Seasons getSeasons() {
        return mSeasons;
    }

}
