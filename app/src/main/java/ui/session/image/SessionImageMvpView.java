
package ui.session.image;

import android.graphics.Bitmap;

import java.util.List;

import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import ui.base.MvpView;

public interface SessionImageMvpView extends MvpView {
    void updateImage(Bitmap bitmap);
    void updateAdapter();
    void initRecycleImage(List<Bitmap> listImage);

    void updateAttendee(List<AttendanceAdminDto> attendeeList);
    void updateAdapterAttendee();
    void initRecycleAttendee(List<AttendanceAdminDto> attendeeList);
}
