package data.model.app;

import java.io.Serializable;

import data.model.server.UserServerDto;


public class UserDto extends UserServerDto implements Serializable {

        public UserDto() {
            super();
        }

        public UserDto(Integer userId, String fullName, String email, String phone, String address) {
           super(userId,fullName,email,phone,address);
        }
    }



