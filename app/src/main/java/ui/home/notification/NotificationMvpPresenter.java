
package ui.home.notification;


import ui.base.MvpInteractor;
import ui.base.MvpPresenter;
import ui.home.calendar.CalendarMvpInteractor;
import ui.home.calendar.CalendarMvpView;


public interface NotificationMvpPresenter<V extends NotificationMvpView, I extends NotificationMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {

}


