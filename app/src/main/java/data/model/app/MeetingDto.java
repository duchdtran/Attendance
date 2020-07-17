package data.model.app;


import java.io.Serializable;

import data.model.server.MeetingServerDto;

public class MeetingDto extends MeetingServerDto implements Serializable {



    public MeetingDto() {
        super();
    }

    public MeetingDto(Integer meetingId, String name, String address, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(meetingId,name,address,timeStart,timeEnd,description,status,creUID,creDate,modUID,modDate);
    }
    public MeetingDto( String name, String address, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(name,address,timeStart,timeEnd,description,status,creUID,creDate,modUID,modDate);
    }

}
