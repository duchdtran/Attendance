
package ui.session.image;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

import data.db.repository.SessionDAO;
import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ultils.Recognize;
import ultils.SingletonDAO;


public class SessionImageInteractor extends BaseInteractor
        implements SessionImageMvpInteractor {

    Context context;
    public SessionImageInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
        this.context=context;
    }


    @Override
    public ArrayList<Bitmap> getAllImage() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AttendanceAdminDto> getAllAttendee() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AttendanceAdminDto> setAttendee() {
        ArrayList<AttendanceAdminDto> attendeeList = new ArrayList<>();
        for(int i = 0; i< Recognize.listBitMap_crop.size(); i++){
            attendeeList.add(new AttendanceAdminDto(Recognize.listBitMap_crop.get(i), Recognize.list1.get(i), "Có mặt", true));
        }
        return attendeeList;
    }
}
