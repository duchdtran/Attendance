package data.model.server;

import java.io.Serializable;

public class DutyServerDto implements Serializable {
    private Integer dutyId;

    public DutyServerDto() {
    }

    public DutyServerDto(Integer dutyId, String dutyName, String dutyDescription) {
        this.dutyId = dutyId;
        this.dutyName = dutyName;
        this.dutyDescription = dutyDescription;
    }

    private String dutyName;

    public DutyServerDto(String dutyName, String dutyDescription) {
        this.dutyName = dutyName;
        this.dutyDescription = dutyDescription;
    }

    private String dutyDescription;

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyDescription() {
        return dutyDescription;
    }

    public void setDutyDescription(String dutyDescription) {
        this.dutyDescription = dutyDescription;
    }
}
