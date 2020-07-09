
package ui.home.setting;


import ui.base.MvpInteractor;
import ui.base.MvpPresenter;
import ui.home.calendar.CalendarMvpInteractor;
import ui.home.calendar.CalendarMvpView;


public interface SettingMvpPresenter<V extends SettingMvpView, I extends SettingMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {

}


