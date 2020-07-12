package ui.session;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.util.List;

import data.model.SessionDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.base.FilterDialog;
import ui.session.detail.SessionDetailActivity;

public class SessionActivity extends BaseActivity implements SessionMvpView {

    Toolbar toolbar;
    RecyclerView recyclerView;
    SessionAdapter adapter;
    public static final String TAG = "SessionActivity";
    SessionMvpPresenter<SessionMvpView, SessionMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SessionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        SessionPresenter sessionPresenter = new SessionPresenter<>(new SessionInteractor((new AppPreferencesHelper(getApplicationContext())),new AppApiHelper(getApplicationContext())));
        mPresenter = sessionPresenter;
        mPresenter.onAttach(SessionActivity.this);
        setUp();
    }


    @Override
    protected void setUp() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.onViewPrepared();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.setGroupVisible(R.id.group_meeting_session, true);
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


    @Override
    public void updateSession(List<SessionDto> sessionList) {
        adapter.addItems(sessionList);
    }

    @Override
    public void initRecycle(List<SessionDto> sessionList) {
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new SessionAdapter(sessionList);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter.setOnItemClickListener(new SessionAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int posision) {
                    openSessionDetailActivity(posision);
                }
            });
        }
    }

    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openSessionDetailActivity(int position) {
        Intent intent = SessionDetailActivity.getStartIntent(SessionActivity.this);
        startActivity(intent);
    }
}
