package io.github.anthonyeef.fanfoudaily.model;

/**
 * Created by anthonyeef on 10/13/15.
 */
public class Fanfou {
    private String screenName;
    private String status;
    private String avatarUrl;
    private String imageUrl;
    private String timeStamp;
    private int favourite;

    public Fanfou() {

    }

    public Fanfou(String screenName, String status, String avatarUrl, String imageUrl, String timeStamp, int favourite) {
        super();
        this.screenName = screenName;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
        this.favourite = favourite;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
