package com.dobrodey.soft.android_senddatawificontrollers.model;

import com.dobrodey.soft.android_senddatawificontrollers.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 30.08.2016.
 */
public class Seasons implements Serializable{
    private static final long serialVersionUID = 777L;

    private Map<String, TimeLuminance> mSeasons;

    public Seasons() {
        mSeasons = new HashMap<>();
    }

    public void addTimeLuminance(TimeLuminance timeLuminance, String season) {
        mSeasons.put(season, timeLuminance);
    }

    public  TimeLuminance getSeasonNow(Calendar c) {
        return mSeasons.get(Utils.SEASONS[c.get(Calendar.MONTH)]);
    }
}
