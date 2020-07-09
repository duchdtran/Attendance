package data.model;

import java.sql.Timestamp;
import java.util.List;


public class UserDto {

    private Integer userId;

    private String userName;

    private String fullName;

    private Integer gender;

    private String passWord;

    private String email;

    private String phone;

    private String address;

    private String avaPath;

    private Integer status;

    private Integer creUID;

    private Timestamp creDate;

    private Integer modUID;

    private Timestamp modDate;

    public UserDto() {
    }

    public UserDto(Integer userId, String fullName, String email, String phone, String address) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    private List<UserModuleDto> listUserModule;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setListUserModule(List<UserModuleDto> listUserModule) {
        this.listUserModule = listUserModule;
    }

    public String getAvaPath() {
        return avaPath;
    }

    public void setAvaPath(String avaPath) {
        this.avaPath = avaPath;
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

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }

    public Integer getModUID() {
        return modUID;
    }

    public void setModUID(Integer modUID) {
        this.modUID = modUID;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

}



