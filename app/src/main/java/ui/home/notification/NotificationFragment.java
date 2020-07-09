
package ui.home.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseFragment;
import ui.home.calendar.CalendarInteractor;
import ui.home.calendar.CalendarMvpInteractor;
import ui.home.calendar.CalendarMvpPresenter;
import ui.home.calendar.CalendarMvpView;
import ui.home.calendar.CalendarPresenter;


public class NotificationFragment extends BaseFragment implements
        NotificationMvpView {

    public static final String TAG = "NotificationFragment";
    NotificationMvpPresenter<NotificationMvpView, NotificationMvpInteractor> mPresenter;

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        NotificationPresenter notificationPresenter=new NotificationPresenter<>(new NotificationInteractor(new AppPreferencesHelper(getContext()),new AppApiHelper(getContext())));
        mPresenter = notificationPresenter;
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
    }

    private void initView(){
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
