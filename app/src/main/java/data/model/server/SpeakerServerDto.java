package data.model.server;

import java.io.Serializable;

public class SpeakerServerDto implements Serializable {

    private Integer speakerId;
    private String fullName;
    private String otherName;
    private Integer gender;
    private String email;
    private String phone;
    private String birthday;
    private String regency;
    private Integer status;
    private Integer creUID;
    private String creDate;
    private Integer modUID;
    private String modDate;



    public SpeakerServerDto(String fullName, String otherName, Integer gender, String email, String phone, String birthday, String regency, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.fullName = fullName;
        this.otherName = otherName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.regency = regency;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public SpeakerServerDto(Integer speakerId, String fullName, String otherName, Integer gender, String email, String phone, String birthday, String regency, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.speakerId = speakerId;
        this.fullName = fullName;
        this.otherName = otherName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.regency = regency;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegency() {
        return regency;
    }

    public void setRegency(String regency) {
        this.regency = regency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
