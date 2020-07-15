
package ui.meeting.detail;


import android.content.Context;

import data.model.app.MeetingDto;
import ui.base.BasePresenter;

public class MeetingDetailPresenter<V extends MeetingDetailMvpView, I extends MeetingDetailMvpInteractor>
        extends BasePresenter<V, I> implements MeetingDetailMvpPresenter<V, I> {

    private static final String TAG = "HomePresenter";

    public MeetingDetailPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared(MeetingDto meetingDto) {
        getMvpView().initRecycle(getInteractor().getSessionByIdMeeting(meetingDto.getMeetingId()));
    }

    @Override
    public void showAddSession(Context context, MeetingDto meetingDto) {

    }

    @Override
    public void importData(String path, int id, Context context, MeetingDto meetingDto) {

    }
}

