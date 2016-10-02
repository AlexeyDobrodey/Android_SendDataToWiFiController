package com.dobrodey.soft.android_senddatawificontrollers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dobrodey.soft.android_senddatawificontrollers.R;

import com.dobrodey.soft.android_senddatawificontrollers.model.FuelObject;
import com.dobrodey.soft.android_senddatawificontrollers.model.ProjectEntity;
import com.dobrodey.soft.android_senddatawificontrollers.model.TextAreaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.08.2016.
 */
public class FragmentAZS extends GeneralFragment {
    public  List<FuelObject> mFuelObjects;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFuelObjects = new ArrayList<>();
    }

    @Override
    public void updateRV() {
        ProjectEntity currentProject = mDataObject.getCurrentProject();

        for(int i = 0; i < currentProject.getLabels().size(); i++) {
            String fuelName = currentProject.getLabels().get(i);
            String valueFuel = ((TextAreaEntity)mAreas.get(i)).getValue();
            mFuelObjects.add(new FuelObject(fuelName, valueFuel));
        }

        mAdapter = new AZSAdapter();
        mRV.setAdapter(mAdapter);
    }

    @Override
    public void priceChange() {
        String hexString = mDataObject.getCurrentProject().getHexStringFromPackets();
        for(int i = 0; i < mAreas.size(); i++) {
            TextAreaEntity txtAreaEntity = (TextAreaEntity) mAreas.get(i);
            txtAreaEntity.setValue(mFuelObjects.get(i).getValue());
            hexString = txtAreaEntity.display(hexString);
        }

        mDataObject.getCurrentProject().saveHexStringToPackets(hexString);
        sendPacketsToController();
    }

    private class AZSHolder extends RecyclerView.ViewHolder {
        private EditText mEditValue;
        private TextView mTxtNameFuel;
        private int index;

        public AZSHolder(View itemView) {
            super(itemView);
            mEditValue = (EditText) itemView.findViewById(R.id.id_edit_fuels);
            mEditValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mFuelObjects.get(index).setValue(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mTxtNameFuel = (TextView) itemView.findViewById(R.id.id_txt_name_fuels);
        }

        public void initData(FuelObject fuelObject, int index) {
            this.index = index;
            mTxtNameFuel.setText(fuelObject.getName());
            mEditValue.setText(fuelObject.getValue());
        }
    }

    private class AZSAdapter extends RecyclerView.Adapter<AZSHolder> {

        @Override
        public AZSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_azs, parent, false);
            return new AZSHolder(view);
        }

        @Override
        public void onBindViewHolder(AZSHolder holder, int position) {
            FuelObject fuelObject = mFuelObjects.get(position);
            holder.initData(fuelObject, position);
        }

        @Override
        public int getItemCount() {
            return mFuelObjects.size();
        }
    }
}
