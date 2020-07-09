package data.model;


public class RecordDto {
    private Integer recordId;
    private SessionDto sessionDto;
    private String name;
    private float length;
    private String path;
    private String status;
    private String statusInApp;
    private String processingInfo;
    private Integer creUID;
    private String creDate;
    private Integer modUID;
    private String modDate;

    public RecordDto(SessionDto sessionDto, String name, Float length, String path, String status, String statusInApp, String processingInfo, int creUID, String creDate, int modUID, String modDate) {
        this.sessionDto = sessionDto;
        this.name = name;
        this.length = length;
        this.path = path;
        this.status = status;
        this.statusInApp = statusInApp;
        this.processingInfo = processingInfo;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }

    public String getName() {
        return name;
    }

    public RecordDto(int recordId, SessionDto sessionDto, String name, float length, String path, String status, String statusInApp, String processingInfo, int creUID, String creDate, int modUID, String modDate) {
        this.recordId = recordId;
        this.sessionDto = sessionDto;
        this.name = name;
        this.length = length;
        this.path = path;
        this.status = status;
        this.statusInApp = statusInApp;
        this.processingInfo = processingInfo;
        this.creUID = creUID;
        this.creDate = creDate;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusInApp() {
        return statusInApp;
    }

    public void setStatusInApp(String statusInApp) {
        this.statusInApp = statusInApp;
    }

    public String getProcessingInfo() {
        return processingInfo;
    }

    public void setProcessingInfo(String processingInfo) {
        this.processingInfo = processingInfo;
    }

    public int getCreUID() {
        return creUID;
    }

    public void setCreUID(int creUID) {
        this.creUID = creUID;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public int getModUID() {
        return modUID;
    }

    public void setModUID(int modUID) {
        this.modUID = modUID;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public RecordDto() {
    }

}
