package data.model.app;

import java.io.Serializable;

import data.model.server.DutyServerDto;

public class DutyDto extends DutyServerDto implements Serializable {
    public DutyDto(){
        super();
    }
    public DutyDto(int dutyID, String dutyName, String description) {
        super(dutyID,dutyName,description);
    }

    public DutyDto(String dutyName, String description) {
        super(dutyName,description);
    }
}
