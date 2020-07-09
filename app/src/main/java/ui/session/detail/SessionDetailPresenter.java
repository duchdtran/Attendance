
package ui.session.detail;


import ui.base.BasePresenter;

public class SessionDetailPresenter<V extends SessionDetailMvpView, I extends SessionDetailMvpInteractor>
        extends BasePresenter<V, I> implements SessionDetailMvpPresenter<V, I> {

    private static final String TAG = "HomePresenter";

    public SessionDetailPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }



}

