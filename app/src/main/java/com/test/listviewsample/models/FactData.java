package com.test.listviewsample.models;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class FactData extends BaseObservable {

    @SerializedName("title")
    private String toptitle;
    private ArrayList<Fact> rows;


    public FactData(String toptitle, ArrayList<Fact> rows) {
        this.toptitle = toptitle;
        this.rows = rows;
    }



    @Bindable
    public String getToptitle() {
        return toptitle;
    }

    public void setToptitle(String title) {
        this.toptitle = title;
        notifyPropertyChanged(com.test.listviewsample.BR.toptitle);
    }

    @Bindable
    public ArrayList<Fact> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Fact> rows) {
        this.rows = rows;
        notifyPropertyChanged(com.test.listviewsample.BR.rows);
    }

}

