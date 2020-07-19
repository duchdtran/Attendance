package ui.session.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;

import ui.session.detail.ImageSessionAdapter;
import ultils.Recognize;


public class SessionImageActivity extends BaseActivity implements SessionImageMvpView{
    private static final int TAKE_PHOTO = 246;
    private String currentPhotoPath;

    Toolbar toolbar;
    SessionDto sessionDto;
    RecyclerView recyclerView;
    ImageSessionAdapter adapter;

    RecyclerView recyclerViewAttendee;
    SessionAttendeeAdapter adapterAttendee;

    TextView tvNameSession, tvNameMeeting;
    Button btnTakePhoto, btnTraning;
    public static final String TAG = "SessionActivity";
    SessionImageMvpPresenter<SessionImageMvpView, SessionImageMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SessionImageActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_image);
        SessionImagePresenter sessionImagePresenter = new SessionImagePresenter<>(new SessionImageInteractor((new AppPreferencesHelper(getApplicationContext())),new AppApiHelper(getApplicationContext())));
        mPresenter = sessionImagePresenter;
        mPresenter.onAttach(SessionImageActivity.this);

        sessionDto = (SessionDto) getIntent().getSerializableExtra("session");
        setUp();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_PHOTO && resultCode == RESULT_OK){
            //Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            new Recognize.detectTask().execute(bitmap);
            updateImage(bitmap);
        }
    }
    @Override
    protected void setUp() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.onViewPrepared();

        tvNameMeeting = findViewById(R.id.tv_name_meeting);
        tvNameMeeting.setText(sessionDto.getMeetingDto().getName());

        tvNameSession = findViewById(R.id.tv_name_session);
        tvNameSession.setText(sessionDto.getName());

        btnTakePhoto = findViewById(R.id.btn_take_photo);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO);
            }
        });

        btnTraning = findViewById(R.id.btn_traning);
        btnTraning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.traning();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return  true;
    }

    public void updateImage(Bitmap bitmap) {
        adapter.addItems(bitmap);
    }
    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initRecycleImage(List<Bitmap> listImage) {
        recyclerView = findViewById(R.id.recycle_view_image);
        adapter = new ImageSessionAdapter(listImage);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void updateAttendee(List<AttendanceAdminDto> attendeeList) {
        adapterAttendee.addItems(attendeeList);
        updateAdapterAttendee();
    }

    @Override
    public void updateAdapterAttendee() {
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initRecycleAttendee(List<AttendanceAdminDto> attendeeList) {
        recyclerViewAttendee = findViewById(R.id.recycle_view_attendee);
        adapterAttendee = new SessionAttendeeAdapter(attendeeList);
        if (adapterAttendee != null) {
            recyclerViewAttendee.setAdapter(adapterAttendee);
            recyclerViewAttendee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerViewAttendee.setItemAnimator(new DefaultItemAnimator());
        }
    }
}
