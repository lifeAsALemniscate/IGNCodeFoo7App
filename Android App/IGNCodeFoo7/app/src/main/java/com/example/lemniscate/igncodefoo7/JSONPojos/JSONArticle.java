package com.example.lemniscate.igncodefoo7.JSONPojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 3/7/17.
 */



public class JSONArticle implements Serializable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("data")
    @Expose
    private List<ADatum> data = null;
    private final static long serialVersionUID = -1212763390552414149L;

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

    public List<ADatum> getData() {
        return data;
    }

    public void setData(List<ADatum> data) {
        this.data = data;
    }

}




