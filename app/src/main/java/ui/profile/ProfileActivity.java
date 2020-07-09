package ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.login.LoginActivity;


public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    Button btnLogout;
    TextView tvName, tvEmail, tvAddress, tvPhone;
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
}
