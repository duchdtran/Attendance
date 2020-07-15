package data.model.app;

import java.io.Serializable;

import data.model.server.SessionServerDto;

public class SessionDto extends SessionServerDto implements Serializable {
    public SessionDto() {
        super();
    }

    public SessionDto(MeetingDto meetingDto, String name, RoomDto roomDto, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(meetingDto, name, roomDto, timeStart, timeEnd, description, status, creUID, creDate, modUID, modDate);
    }

    public SessionDto(Integer sessionId, MeetingDto meetingDto, String name, RoomDto roomDto, String timeStart, String timeEnd, String description, int status, int creUID, String creDate, int modUID, String modDate) {
        super(sessionId, meetingDto, name, roomDto, timeStart, timeEnd,description, status, creUID, creDate, modUID, modDate);
    }
}
