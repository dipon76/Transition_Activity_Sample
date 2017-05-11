package com.example.dipon.activitytransitionsample.data;

import android.support.annotation.ColorRes;

import java.io.Serializable;

/**
 * Created by Dipon on 5/9/2017.
 */

public class SampleData implements Serializable{
    private int color;
    private String itemName;

    public SampleData(@ColorRes int color, String itemName) {
        this.color = color;
        this.itemName = itemName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
