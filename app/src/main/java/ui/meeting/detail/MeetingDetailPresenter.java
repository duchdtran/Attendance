
package ui.meeting.detail;


import ui.base.BasePresenter;

public class MeetingDetailPresenter<V extends MeetingDetailMvpView, I extends MeetingDetailMvpInteractor>
        extends BasePresenter<V, I> implements MeetingDetailMvpPresenter<V, I> {

    private static final String TAG = "HomePresenter";

    public MeetingDetailPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }



}

