package data.model.app;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import data.model.server.UserServerDto;


public class UserDto extends UserServerDto implements Serializable {

    public UserDto() {
        super();
    }

    public UserDto(Integer userId, String fullName, String email, String phone, String address) {
        super(userId,fullName,email,phone,address);
    }
}



