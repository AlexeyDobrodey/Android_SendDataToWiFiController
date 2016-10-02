package com.dobrodey.soft.android_senddatawificontrollers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;
import com.dobrodey.soft.android_senddatawificontrollers.model.Container;
import com.dobrodey.soft.android_senddatawificontrollers.model.ControllerPackets;
import com.dobrodey.soft.android_senddatawificontrollers.model.CurrencyObject;
import com.dobrodey.soft.android_senddatawificontrollers.model.TextAreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.TimeLuminance;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 31.08.2016.
 */
public class FragmentCENewLogic  extends FragmentCE{
    private TextAreaEntity mTextAreaNameValute;
    private Container mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextAreaNameValute = new TextAreaEntity(32, 16, "CE 32x16", "red", "horizontal", 2, 0, 2, "single");
        mContainer = new Container(mTextAreaNameValute.getHeight());
    }

    @Override
    public void priceChange() {
        List<String> labels = mDataObject.getCurrentProject().getLabels();

        int i = 0;
        for(CurrencyObject currencyObject:currencyObjects) {
            String sale = currencyObject.getSale();
            String purchase = currencyObject.getPurchase();

            ((TextAreaEntity)mAreas.get(i)).setValue(purchase);
            ((TextAreaEntity)mAreas.get(i + 1)).setValue(sale);
            i+= 2;
        }

        String hexString = mDataObject.getCurrentProject().getHexStringFromPackets();
        for(int j = 0; j < labels.size(); j++) {
            String nameValute = labels.get(j);
            TextAreaEntity purchaseArea = ((TextAreaEntity)mAreas.get(j * 2));
            TextAreaEntity saleArea = ((TextAreaEntity)mAreas.get(j * 2 + 1));

            if(nameValute.equals("USD") || nameValute.equals("EUR") ||nameValute.equals("RUB")) {
                hexString = purchaseArea.display(hexString);
                hexString = saleArea.display(hexString);
            }
            else {
                mContainer.clear();
                mTextAreaNameValute.setValue(nameValute);

                mContainer.addArea(purchaseArea);
                mContainer.addArea(mTextAreaNameValute);
                mContainer.addArea(saleArea);
                hexString = mContainer.display(hexString);
            }
        }
        mDataObject.getCurrentProject().saveHexStringToPackets(hexString);

        Calendar calendar = Calendar.getInstance();
        ControllerPackets.changeTimeLuminance(mDataObject.getSeasons().getSeasonNow(calendar));
        ControllerPackets.changeTime(calendar);

        sendPacketsToController();
    }
}
