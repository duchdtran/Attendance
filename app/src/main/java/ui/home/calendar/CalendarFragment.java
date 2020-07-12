
package ui.home.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.ubnd.attendance.R;

import java.util.List;

import data.model.SessionDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseFragment;


public class CalendarFragment extends BaseFragment implements
        CalendarMvpView {

    TextView tvListSesstion;
    RecyclerView recyclerView;
    CalendarAdapter adapter;

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
        initView();
        return view;
    }

    @Override
    protected void setUp(View view) {

        final CollapsibleCalendar collapsibleCalendar = view.findViewById(R.id.calendar_view);
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                tvListSesstion.setText(String.format("Lịch họp ngày %d tháng %d năm %d",day.getDay(), day.getMonth()+1, day.getYear()));
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
                mPresenter.onViewPrepared();
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
        Day day = collapsibleCalendar.getSelectedDay();
        tvListSesstion.setText(String.format("Lịch họp ngày %d tháng %d năm %d",day.getDay(), day.getMonth(), day.getYear()));
        mPresenter.onViewPrepared();
    }

    private void initView(){
        tvListSesstion = view.findViewById(R.id.tv_list_session);
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void updateSession(List<SessionDto> sessionList) {
        adapter.addItems(sessionList);
    }

    @Override
    public void initRecycle(List<SessionDto> sessionList) {
        recyclerView = view.findViewById(R.id.recycle_view);
        adapter = new CalendarAdapter(sessionList);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
