
package ui.home;


import ui.base.BasePresenter;

public class HomePresenter<V extends HomeMvpView, I extends HomeMvpInteractor>
        extends BasePresenter<V, I> implements HomeMvpPresenter<V, I> {

    private static final String TAG = "HomePresenter";

    public HomePresenter(I mvpInteractor) {
        super(mvpInteractor);
    }



}

