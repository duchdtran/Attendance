package data.model.app;

import java.io.Serializable;

public class Request implements Serializable {
    private  Integer idRequest;
    private RecordDto record;
    private ImageUserDto imageUserDto;

    public Request(RecordDto record) {
        this.record = record;
        idRequest=null;
    }

    public Request(Integer idRequest, RecordDto record) {
        this.idRequest = idRequest;
        this.record = record;
    }

    public Request(ImageUserDto imageUserDto) {
        this.imageUserDto = imageUserDto;
    }

    public Request(Integer idRequest, ImageUserDto imageUserDto) {
        this.idRequest = idRequest;
        this.imageUserDto = imageUserDto;
    }

    public Request() {
    }

    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public RecordDto getRecord() {
        return record;
    }

    public void setRecord(RecordDto record) {
        this.record = record;
    }

    public ImageUserDto getImageUserDto() {
        return imageUserDto;
    }

    public void setImageUserDto(ImageUserDto imageUserDto) {
        this.imageUserDto = imageUserDto;
    }
}
