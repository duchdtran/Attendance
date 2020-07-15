
package ui.session;


import android.content.Context;

import java.util.List;

import data.model.app.SessionDto;
import ui.base.BasePresenter;

public class SessionPresenter<V extends SessionMvpView, I extends SessionMvpInteractor>
        extends BasePresenter<V, I> implements SessionMvpPresenter<V, I> {

    private static final String TAG = "MeetingPresenter";

    public SessionPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared() {
        List<SessionDto> meetingList = getInteractor().getAllSession();
        getMvpView().initRecycle(meetingList);
    }

    @Override
    public void showDialogSession(Context context) {

    }

    @Override
    public void importData(String path, int id, Context context) {

    }

}

