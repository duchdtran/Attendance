package ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.home.HomeActivity;
import ui.login.LoginActivity;


public class SplashActivity extends BaseActivity implements SplashMvpView {

    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);

        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SplashPresenter loginPresenter=new SplashPresenter<>(new SplashInteractor(new AppPreferencesHelper(getApplicationContext()),new AppApiHelper(getApplicationContext())));
        mPresenter = loginPresenter;
        mPresenter.onAttach(SplashActivity.this);

        setUp();
    }

    void onServerLogin() {
        //mPresenter.onServerLogin();
        openLoginActivity();
    }

    @Override
    public void openHomeActivity() {
        Intent intent = HomeActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        hideLoading();
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        hideLoading();
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        onServerLogin();
    }
}
