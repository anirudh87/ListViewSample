package com.test.listviewsample.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class Fact extends BaseObservable {

    private String title;
    private String description;
    private String imageHref;


    public Fact(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    @Bindable
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(com.test.listviewsample.BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(com.test.listviewsample.BR.description);

    }

    @Bindable
    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
        notifyPropertyChanged(com.test.listviewsample.BR.imageHref);

    }


}
