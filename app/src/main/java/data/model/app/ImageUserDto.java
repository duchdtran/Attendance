package data.model.app;

import android.graphics.Bitmap;

import data.model.server.ImageUserServerDto;

public class ImageUserDto extends ImageUserServerDto {


    public ImageUserDto(Integer imageUserId, Integer userID, String nameUser, String path, String data, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(imageUserId, userID, nameUser, path, data, creUID, creDate, modUID, modDate);
    }

    public ImageUserDto(Integer userID, String nameUser, String path, String data, Integer creUID, String creDate, Integer modUID, String modDate) {
        super(userID, nameUser, path, data, creUID, creDate, modUID, modDate);
    }
}
