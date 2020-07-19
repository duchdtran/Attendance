package ui.attendee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
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

    TextView tvAttendee;
    SwitchCompat switchAttendee;
    Toolbar toolbar;
    RecyclerView recyclerViewAttendee;
    AttendeeAdapter attendeeAdapter;
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

        tvAttendee = findViewById(R.id.tv_attendee);
        switchAttendee = findViewById(R.id.switch_attendee);
        switchAttendee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                attendeeAdapter.filter(isChecked);
                if(isChecked){
                    tvAttendee.setText("Tham dự");
                }
                else {
                    tvAttendee.setText("Không tham dự");
                }
            }
        });

        mPresenter.onViewPrepared(sessionDto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.setGroupVisible(R.id.group_meeting_session, true);
        menu.findItem(R.id.option_filter).setVisible(false);

        final SearchView searchView = (SearchView) menu.findItem(R.id.option_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                attendeeAdapter.filter(newText.trim());
                return false;
            }
        });
        return true;
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
            attendeeAdapter.filter(true);
        }
    }

}
