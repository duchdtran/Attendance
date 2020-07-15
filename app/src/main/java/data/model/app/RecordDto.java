package data.model.app;


import java.io.Serializable;

import data.model.server.RecordServerDto;

public class RecordDto extends RecordServerDto implements Serializable {

    private String statusInApp;


    public RecordDto() {
        super();
    }

    public RecordDto(SessionDto sessionDto, String name, Float length, String path, String status, String statusInApp, String processingInfo, int creUID, String creDate, int modUID, String modDate) {
        super(sessionDto, name, length, path, status, processingInfo, creUID, creDate, modUID, modDate);
        this.statusInApp = statusInApp;
    }

    public RecordDto(int recordId, SessionDto sessionDto, String name, float length, String path, String status, String statusInApp, String processingInfo, int creUID, String creDate, int modUID, String modDate) {
        super(recordId, sessionDto, name, length, path, status, processingInfo, creUID, creDate, modUID, modDate);
        this.statusInApp = statusInApp;
    }

    public void setStatusInApp(String statusInApp) {
        this.statusInApp = statusInApp;
    }

    public String getStatusInApp() {
        return statusInApp;
    }
}
