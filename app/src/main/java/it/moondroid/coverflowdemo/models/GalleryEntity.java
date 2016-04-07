package it.moondroid.coverflowdemo.models;

/**
 * Created by Nadzer on 13/03/2016.
 */
public class GalleryEntity {
    public int imageResId;
    public int titleResId;

    public GalleryEntity(int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }
}
