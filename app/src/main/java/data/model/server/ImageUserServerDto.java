package data.model.server;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ImageUserServerDto implements Serializable {

    private Integer imageUserId;
    private Integer userID;
    private String nameUser;
    private String path;
    private String data;
    private Integer creUID;
    private String creDate;
    private Integer modUID;
    private String modDate;

    public ImageUserServerDto(Integer imageUserId, Integer userID, String nameUser, String path, String data, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.imageUserId = imageUserId;
        this.userID = userID;
        this.nameUser = nameUser;
        this.path = path;
        this.data = data;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public ImageUserServerDto(Integer userID, String nameUser, String path, String data, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.userID = userID;
        this.nameUser = nameUser;
        this.path = path;
        this.data = data;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public Integer getImageUserId() {
        return imageUserId;
    }

    public void setImageUserId(Integer imageUserId) {
        this.imageUserId = imageUserId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCreUID() {
        return creUID;
    }

    public void setCreUID(Integer creUID) {
        this.creUID = creUID;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public Integer getModUID() {
        return modUID;
    }

    public void setModUID(Integer modUID) {
        this.modUID = modUID;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
