package data.model.app;

import java.io.Serializable;

import data.model.server.RoomServerDto;

public class RoomDto extends RoomServerDto implements Serializable {

    public RoomDto() {
        super();
    }

    public RoomDto(Integer roomId, String roomName, String roomDescription, Integer status) {
       super(roomId,roomName,roomDescription,status);
    }

    public RoomDto(String roomName, String roomDescription, Integer status) {
        super(roomName,roomDescription,status);
    }
}
