package com.example.lemniscate.igncodefoo7.JSONPojos;

/**
 * Created by user on 3/7/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class JSONVideo implements Serializable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("data")
    @Expose
    private List<VDatum> data = null;
    private final static long serialVersionUID = 117611323062803758L;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<VDatum> getData() {
        return data;
    }

    public void setData(List<VDatum> data) {
        this.data = data;
    }

}
