
package ui.home.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseFragment;


public class CalendarFragment extends BaseFragment implements
        CalendarMvpView {

    public static final String TAG = "CalendarFragment";
    CalendarMvpPresenter<CalendarMvpView, CalendarMvpInteractor> mPresenter;

    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarPresenter calendarPresenter=new CalendarPresenter<>(new CalendarInteractor(new AppPreferencesHelper(getContext()),new AppApiHelper(getContext())));
        mPresenter = calendarPresenter;
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        final CollapsibleCalendar collapsibleCalendar = view.findViewById(R.id.calendar_view);
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }

            @Override
            public void onClickListener() {

            }

            @Override
            public void onDayChanged() {

            }
        });
    }

    private void initView(){
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
