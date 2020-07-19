
package ui.session.image;


import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONArray;

import java.util.ArrayList;

import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface SessionImageMvpInteractor extends MvpInteractor {
    ArrayList<Bitmap> getAllImage();
    ArrayList<AttendanceAdminDto> getAllAttendee();
    ArrayList<AttendanceAdminDto> setAttendee();
}
