package com.example.lemniscate.igncodefoo7.JSONPojos;

/**
 * Created by user on 3/7/17.
 */
import android.content.Context;
import android.view.View;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ADatum  implements Serializable
{

    @SerializedName("thumbnails")
    @Expose
    private List<AThumbnail> thumbnails = null;
    @SerializedName("metadata")
    @Expose
    private AMetadata metadata;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    private final static long serialVersionUID = 4249716825952283908L;




    public List<AThumbnail> getAThumbnails() {
        return thumbnails;
    }

    public void setAThumbnails(List<AThumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public AMetadata getAMetadata() {
        return metadata;
    }

    public void setAMetadata(AMetadata metadata) {
        this.metadata = metadata;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
