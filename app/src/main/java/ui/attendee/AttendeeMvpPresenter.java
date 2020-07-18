
package ui.attendee;


import android.content.Context;

import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;
import ui.base.MvpPresenter;


public interface AttendeeMvpPresenter<V extends AttendeeMvpView, I extends AttendeeMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {
    void onViewPrepared(SessionDto sessionDto);
    void creAttendee(AttendeeDto attendeeDto, final Context context, Callback callback);
}


