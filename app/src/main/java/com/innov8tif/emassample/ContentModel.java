package com.innov8tif.emassample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kosh20111 on 5/13/2015. CopyRights @ Innov8tif
 */
public class ContentModel {
    private String label;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ContentModel{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
