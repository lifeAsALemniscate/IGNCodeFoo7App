package com.example.lemniscate.igncodefoo7.JSONPojos;

/**
 * Created by user on 3/7/17.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VDatum implements Serializable
{

    @SerializedName("thumbnails")
    @Expose
    private List<VThumbnail> thumbnails = null;
    @SerializedName("metadata")
    @Expose
    private VMetadata metadata;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    private final static long serialVersionUID = 4249716825952283908L;

    public List<VThumbnail> getVThumbnails() {
        return thumbnails;
    }

    public void setVThumbnails(List<VThumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public VMetadata getVMetadata() {
        return metadata;
    }

    public void setVMetadata(VMetadata vmetadata) {
        this.metadata = vmetadata;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}