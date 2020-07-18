package ui.meeting.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.util.List;

import data.model.app.MeetingDto;
import data.model.app.SessionDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.base.FilterDialog;
import ui.session.SessionAdapter;

public class MeetingDetailActivity extends BaseActivity implements MeetingDetailMvpView {

    Toolbar toolbar;
    RecyclerView recyclerView;
    SessionAdapter adapter;
    TextView tvName, tvAddress, tvTimeStart, tvTimeEnd;
    MeetingDto meetingDto;

    public static final String TAG = "MeetingActivity";
    MeetingDetailMvpPresenter<MeetingDetailMvpView, MeetingDetailMvpInteractor> mPresenter;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MeetingDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);
        MeetingDetailPresenter meetingDetailPresenter = new MeetingDetailPresenter<>(new MeetingDetailInteractor((new AppPreferencesHelper(getApplicationContext())),new AppApiHelper(getApplicationContext())));
        mPresenter = meetingDetailPresenter;
        mPresenter.onAttach(MeetingDetailActivity.this);

        meetingDto = (MeetingDto) getIntent().getSerializableExtra("meeting");
        initView();
        setUp();
        mPresenter.onViewPrepared(meetingDto);
    }

    private void initView() {
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        tvTimeEnd = findViewById(R.id.tv_time_end);
        tvTimeStart = findViewById(R.id.tv_time_start);
    }


    @Override
    protected void setUp() {
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set detail meeting
        tvName.setText(meetingDto.getName());
        tvAddress.setText(meetingDto.getAddress());
        tvTimeStart.setText(meetingDto.getTimeStart());
        tvTimeEnd.setText(meetingDto.getTimeEnd());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.setGroupVisible(R.id.group_detail_meeting, true);
        final SearchView searchView = (SearchView) menu.findItem(R.id.option_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText.trim());
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.option_filter:
                FilterDialog filterDialog = new FilterDialog();
                filterDialog.show(getSupportFragmentManager(), "filter");
                break;
        }
        return true;
    }

    @Override
    public void initRecycle(List<SessionDto> sessionList) {
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new SessionAdapter(sessionList);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void updateSession(List<SessionDto> sessionList) {
        adapter.addItems(sessionList);
    }
}
