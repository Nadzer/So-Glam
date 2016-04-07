package it.moondroid.coverflowdemo.models;

/**
 * Created by Nadzer on 13/03/2016.
 */
public class ImageEntity {
    private Integer image;
    private String title;

    public ImageEntity(Integer image, String title) {
        this.image = image;
        this.title = title;
    }

    public Integer getImage() { return image; }

    public String getTitle() { return title; }

    public void setImage(Integer image) { this.image = image; }

    public void setTitle(String title) {
        this.title = title;
    }
}
