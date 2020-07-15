
package ui.home.calendar;


import ui.base.MvpInteractor;
import ui.base.MvpPresenter;


public interface CalendarMvpPresenter<V extends CalendarMvpView, I extends CalendarMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {
    void onViewPrepared(int day, int month, int year);
}


