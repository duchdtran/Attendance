
package ui.meeting;


import android.content.Context;

import java.util.List;

import data.model.MeetingDto;
import ui.base.BasePresenter;

public class MeetingPresenter<V extends MeetingMvpView, I extends MeetingMvpInteractor>
        extends BasePresenter<V, I> implements MeetingMvpPresenter<V, I> {

    private static final String TAG = "MeetingPresenter";

    public MeetingPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared() {
        List<MeetingDto> meetingList = getInteractor().getAllMeeting();
        getMvpView().initRecycle(meetingList);
    }

    @Override
    public void showDialogMeeting(Context context) {

    }

    @Override
    public void importData(String path, int id, Context context) {

    }
}

