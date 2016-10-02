package com.dobrodey.soft.android_senddatawificontrollers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dobrodey.soft.android_senddatawificontrollers.R;
import com.dobrodey.soft.android_senddatawificontrollers.model.AreaEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.ControllerPackets;
import com.dobrodey.soft.android_senddatawificontrollers.model.CurrencyObject;
import com.dobrodey.soft.android_senddatawificontrollers.model.ProjectEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.TextAreaEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alexey on 14.08.2016.
 */
public class FragmentCE extends GeneralFragment{
    protected List<CurrencyObject> currencyObjects;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currencyObjects = new ArrayList<>();
    }

    @Override
    public void updateRV() {
        ProjectEntity currentProject = mDataObject.getCurrentProject();

        for(String label: currentProject.getLabels()) {
            CurrencyObject curObj = new CurrencyObject();
            curObj.setNameCurrency(label);
            currencyObjects.add(curObj);
        }

        for(int i = 0, j = 0; i < mAreas.size(); i+=2, j++) {
            String valuePurchase = ((TextAreaEntity)mAreas.get(i)).getValue();
            String valueSale     = ((TextAreaEntity)mAreas.get(i + 1)).getValue();
            currencyObjects.get(j).setPurchase(valuePurchase);
            currencyObjects.get(j).setSale(valueSale);
        }

        mAdapter = new CEAdapter(currencyObjects);
        mRV.setAdapter(mAdapter);
    }

    @Override
    public void priceChange() {
        int i = 0;
        for(CurrencyObject currencyObject:currencyObjects) {
            String sale = currencyObject.getSale();
            String purchase = currencyObject.getPurchase();
            Log.d("MainActivity", purchase + "; " + sale);

            ((TextAreaEntity)mAreas.get(i)).setValue(purchase);
            ((TextAreaEntity)mAreas.get(i + 1)).setValue(sale);
            i+= 2;
        }

        String hexString = mDataObject.getCurrentProject().getHexStringFromPackets();
        for(AreaEntity area : mAreas) {
            hexString = ((TextAreaEntity) area).display(hexString);
        }

        mDataObject.getCurrentProject().saveHexStringToPackets(hexString);

        Calendar calendar = Calendar.getInstance();
        ControllerPackets.changeTimeLuminance(mDataObject.getSeasons().getSeasonNow(calendar));
        ControllerPackets.changeTime(calendar);

        sendPacketsToController();
    }

    private class CEHolder extends RecyclerView.ViewHolder {
        private EditText mEditPurchase;
        private EditText mEditSale;
        private TextView txtNameCE;
        private int index;

        public CEHolder(View itemView) {
            super(itemView);
            mEditPurchase = (EditText) itemView.findViewById(R.id.id_edit_purchase);
            mEditPurchase.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    currencyObjects.get(index).setPurchase(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            mEditSale = (EditText) itemView.findViewById(R.id.id_edit_sale);
            mEditSale.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    currencyObjects.get(index).setSale(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            txtNameCE = (TextView) itemView.findViewById(R.id.id_txt_name_currency);
        }

        public void initData(CurrencyObject currencyObject, int index) {
            this.index = index;
            txtNameCE.setText(currencyObject.getNameCurrency());
            mEditPurchase.setText(currencyObject.getPurchase());
            mEditSale.setText(currencyObject.getSale());
        }
    }

    private class CEAdapter extends RecyclerView.Adapter<CEHolder> {

        private List<CurrencyObject> mCurrencies;

        public CEAdapter(List<CurrencyObject> currencies) {
            mCurrencies = currencies;
        }

        @Override
        public CEHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_ce, parent, false);
            return new CEHolder(view);
        }

        @Override
        public void onBindViewHolder(CEHolder holder, int position) {
            CurrencyObject currencyObj = mCurrencies.get(position);
            holder.initData(currencyObj, position);
        }

        @Override
        public int getItemCount() {
            return mCurrencies.size();
        }
    }
}
