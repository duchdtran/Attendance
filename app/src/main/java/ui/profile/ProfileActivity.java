package ui.profile;

import android.Manifest;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;


import com.ubnd.attendance.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import data.model.ImageDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.login.LoginActivity;
import ultils.Recognize;


public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    private static final int REQUEST_PERMISSION_CODE = 21;
    public final static int TAKE_PHOTO = 276;

    Button btnLogout, btnAddImage;
    TextView tvName, tvEmail, tvAddress, tvPhone;
    GridView gridView;
    ImageAdapter adapter;

    private String currentPhotoPath;

    ProfileMvpPresenter<ProfileMvpView, ProfileMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);

        return intent;
    }
    private  void initView(){
        btnLogout = findViewById(R.id.btn_logout);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
        btnAddImage = findViewById(R.id.btn_add_image);
        gridView = findViewById(R.id.gv_photo_album);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ProfilePresenter profilePresenter =new ProfilePresenter<>(new ProfileInteractor(new AppPreferencesHelper(getApplicationContext()),new AppApiHelper(getApplicationContext())));
        mPresenter = profilePresenter;
        mPresenter.onAttach(ProfileActivity.this);
        initView();
        setUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mPresenter.setDataProfile(tvName,tvEmail,tvPhone,tvAddress);
        mPresenter.onViewPrepared();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onServerLogoutClick();
            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_PHOTO && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            new Recognize.AddPersonToGroup().execute(new ImageDto(tvName.getText().toString(), bitmap));
            updateImage(bitmap);
        }
    }
    void requestPermission() {
        if (!(hasPermission(Manifest.permission.CAMERA))) {
            requestPermissionsSafely(new String[]{
                    Manifest.permission.CAMERA

            }, REQUEST_PERMISSION_CODE);
        }
    }
    void onServerLogoutClick(){
        mPresenter.onServerLogoutClick();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(ProfileActivity.this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void updateImage(Bitmap bitmap) {
        adapter.addItems(bitmap);
    }

    @Override
    public void initRecycle(List<Bitmap> listImage) {
        adapter = new ImageAdapter(this, listImage);
        if (adapter != null) {
            gridView.setAdapter(adapter);

        }
    }

    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
        gridView.setAdapter(adapter);
    }
}
