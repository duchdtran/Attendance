package data.model;

public class Request {
    private  Integer idRequest;
   private RecordDto record;

    public Request(RecordDto record) {
        this.record = record;
        idRequest=null;
    }

    public Request(Integer idRequest, RecordDto record) {
        this.idRequest = idRequest;
        this.record = record;
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
}
