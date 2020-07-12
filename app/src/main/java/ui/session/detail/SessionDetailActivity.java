package ui.session.detail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import service.NetworkChangeReceiver;
import ui.base.BaseActivity;
import ui.base.FilterDialog;
import ui.home.HomePage.HomePageFragment;
import ui.home.calendar.CalendarFragment;
import ui.home.notification.NotificationFragment;
import ui.home.setting.SettingFragment;
import ui.meeting.detail.MeetingDetailActivity;
import ui.meeting.detail.MeetingDetailInteractor;
import ui.meeting.detail.MeetingDetailMvpInteractor;
import ui.meeting.detail.MeetingDetailMvpPresenter;
import ui.meeting.detail.MeetingDetailMvpView;
import ui.meeting.detail.MeetingDetailPresenter;

public class SessionDetailActivity extends BaseActivity implements SessionDetailMvpView {
    Toolbar toolbar;

    public static final String TAG = "MeetingActivity";
    SessionDetailMvpPresenter<SessionDetailMvpView, SessionDetailMvpInteractor> mPresenter;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SessionDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_detail);
        SessionDetailPresenter sessionDetailPresenter = new SessionDetailPresenter<>(new SessionDetailInteractor((new AppPreferencesHelper(getApplicationContext())),new AppApiHelper(getApplicationContext())));
        mPresenter = sessionDetailPresenter;
        mPresenter.onAttach(SessionDetailActivity.this);
        setUp();
    }


    @Override
    protected void setUp() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.setGroupVisible(R.id.group_detail, true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.option_filter:
                FilterDialog filterDialog = new FilterDialog();
                filterDialog.show(getSupportFragmentManager(), "filter");
                break;
            case R.id.option_search:

                break;
        }
        return true;
    }

}
