package data.model.app;

import java.io.Serializable;

import data.model.server.SpeakerServerDto;

public class SpeakerDto extends SpeakerServerDto implements Serializable {


    public SpeakerDto(String fullName, String otherName, Integer gender, String email, String phone, String birthday, String regency, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(fullName,otherName,gender,email,phone,birthday,regency,status,creUID,creDate,modUID,modDate);
    }

    public SpeakerDto(Integer speakerId, String fullName, String otherName, Integer gender, String email, String phone, String birthday, String regency, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(speakerId,fullName,otherName,gender,email,phone,birthday,regency,status,creUID,creDate,modUID,modDate);
    }
}
