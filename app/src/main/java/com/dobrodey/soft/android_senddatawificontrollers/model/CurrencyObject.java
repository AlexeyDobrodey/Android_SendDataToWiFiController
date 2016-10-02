package com.dobrodey.soft.android_senddatawificontrollers.model;

/**
 * Created by Sergey on 14.08.2016.
 */
public class CurrencyObject {
    private String mPurchase;
    private String mSale;
    private String mNameCurrency;

    public CurrencyObject() {
        this.mSale = "";
        this.mPurchase = "";
        this.mNameCurrency = "";
    }

    public CurrencyObject(String nameCurrency, String purchase, String sale) {
        this.mNameCurrency = nameCurrency;
        this.mPurchase = purchase;
        this.mSale = sale;
    }

    public void setSale(String sale) {
        this.mSale = sale;
    }

    public void setPurchase(String purchase) {
        this.mPurchase = purchase;
    }

    public void setNameCurrency(String nameCurrency) {
        this.mNameCurrency = nameCurrency;
    }

    public String getPurchase() {
        return mPurchase;
    }

    public String getSale() {
        return mSale;
    }

    public String getNameCurrency() {
        return mNameCurrency;
    }
}
