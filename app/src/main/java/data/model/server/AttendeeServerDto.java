package data.model.server;

import java.io.Serializable;

import data.model.app.DutyDto;
import data.model.app.SessionDto;
import data.model.app.SpeakerDto;

public class AttendeeServerDto implements Serializable {

    private Integer attendeesId;
    private SessionDto sessionDto;
    private SpeakerDto speakerDto;
    private DutyDto dutyDto;
    private Integer status;
    private Integer creUID;
    private String creDate;
    private Integer modUID;
    private String modDate;

    public Integer getAttendeesId() {
        return attendeesId;
    }

    public void setAttendeesId(Integer attendeesId) {
        this.attendeesId = attendeesId;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }

    public SpeakerDto getSpeakerDto() {
        return speakerDto;
    }

    public void setSpeakerDto(SpeakerDto speakerDto) {
        this.speakerDto = speakerDto;
    }

    public DutyDto getDutyDto() {
        return dutyDto;
    }

    public void setDutyDto(DutyDto dutyDto) {
        this.dutyDto = dutyDto;
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
