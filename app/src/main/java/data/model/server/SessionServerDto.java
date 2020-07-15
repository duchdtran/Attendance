package data.model.server;

import java.io.Serializable;

import data.model.app.MeetingDto;
import data.model.app.RoomDto;

public class SessionServerDto implements Serializable {
    private Integer sessionId;
    private MeetingDto meetingDto;
    private String name;
    private RoomDto roomDto;
    private String timeStart;
    private String timeEnd;
    private String description;
    private Integer status;
    private Integer creUID;
    private String creDate;
    private Integer modUID;
    private String modDate;

    public SessionServerDto() {
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }

    public SessionServerDto(MeetingDto meetingDto, String name, RoomDto roomDto, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.meetingDto = meetingDto;
        this.name = name;
        this.roomDto = roomDto;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.description = description;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public SessionServerDto(Integer sessionId, MeetingDto meetingDto, String name, RoomDto roomDto, String timeStart, String timeEnd, String description, int status, int creUID, String creDate, int modUID, String modDate) {
        this.sessionId = sessionId;
        this.meetingDto = meetingDto;
        this.name = name;
        this.roomDto = roomDto;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.description = description;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public MeetingDto getMeetingDto() {
        return meetingDto;
    }

    public void setMeetingDto(MeetingDto meetingDto) {
        this.meetingDto = meetingDto;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
