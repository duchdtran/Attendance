package data.model;

public class RoomDto {
    private Integer roomId;
    private String roomName;
    private String roomDescription;
    private Integer status;

    public RoomDto() {
    }

    public RoomDto(Integer roomId, String roomName, String roomDescription, Integer status) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.status = status;
    }

    public RoomDto(String roomName, String roomDescription, Integer status) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.status = status;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
