package ui.attendee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.util.List;

import data.model.app.AttendeeDto;
import data.model.app.MeetingDto;
import data.model.app.SessionDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;

public class AttendeeActivity extends BaseActivity implements AttendeeMvpView{

    public static final String TAG = "RecordMeetingFragment";

    Toolbar toolbar;
    RecyclerView recyclerView,recyclerViewAttendee;
    AttendeeAdapter adapter;
    AttendeeAdapter attendeeAdapter;
    static MeetingDto meetingDto;
    SessionDto sessionDto;

    AttendeeMvpPresenter<AttendeeMvpView, AttendeeMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AttendeeActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);
        AttendeePresenter AttendeePresenter = new AttendeePresenter<>(new AttendeeInteractor(new AppPreferencesHelper(this), new AppApiHelper(this), this));
        mPresenter = AttendeePresenter;
        mPresenter.onAttach(this);
        sessionDto = (SessionDto) getIntent().getSerializableExtra("session");
        setUp();
    }

    @Override
    protected void setUp() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.onViewPrepared(sessionDto);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public void initRecycle(List<AttendeeDto> attendeeList){
        recyclerViewAttendee = findViewById(R.id.recycle_view);
        attendeeAdapter = new AttendeeAdapter(attendeeList);
        if (attendeeAdapter != null) {
            recyclerViewAttendee.setAdapter(attendeeAdapter);
            recyclerViewAttendee.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewAttendee.setItemAnimator(new DefaultItemAnimator());
        }
    }

}
