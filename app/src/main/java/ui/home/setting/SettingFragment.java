
package ui.home.setting;

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


public class SettingFragment extends BaseFragment implements
        SettingMvpView {

    public static final String TAG = "SettingFragment";
    SettingMvpPresenter<SettingMvpView, SettingMvpInteractor> mPresenter;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        SettingPresenter settingPresenter=new SettingPresenter<>(new SettingInteractor(new AppPreferencesHelper(getContext()),new AppApiHelper(getContext())));
        mPresenter = settingPresenter;
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
