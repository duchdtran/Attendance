
package ui.session.image;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.List;

import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import ui.base.BasePresenter;

public class SessionImagePresenter<V extends SessionImageMvpView, I extends SessionImageMvpInteractor>
        extends BasePresenter<V, I> implements SessionImageMvpPresenter<V, I> {

    private static final String TAG = "MeetingPresenter";

    public SessionImagePresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared() {
        List<Bitmap> bitmapList = getInteractor().getAllImage();
        getMvpView().initRecycleImage(bitmapList);

        List<AttendanceAdminDto> attendeeList = getInteractor().getAllAttendee();
        getMvpView().initRecycleAttendee(attendeeList);
    }

    @Override
    public void traning() {
        List<AttendanceAdminDto> adminDtoList =  getInteractor().setAttendee();
        getMvpView().updateAttendee(adminDtoList);
    }
}

