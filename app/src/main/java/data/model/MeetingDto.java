package data.model;


public class MeetingDto {


    private Integer meetingId;


    private String name;

    private String address;

    private String timeStart;

    private String timeEnd;

    private String description;

    private Integer status;

    private Integer creUID;

    private String creDate;
    private Integer modUID;

    private String modDate;

    public MeetingDto() {
    }

    public MeetingDto(Integer meetingId, String name, String address, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.meetingId = meetingId;
        this.name = name;
        this.address = address;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.description = description;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }
    public MeetingDto( String name, String address, String timeStart, String timeEnd, String description, Integer status, Integer creUID, String creDate, Integer modUID, String modDate) {
        this.name = name;
        this.address = address;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.description = description;
        this.status = status;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }
    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
