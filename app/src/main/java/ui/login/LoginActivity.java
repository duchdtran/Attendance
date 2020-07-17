package ui.login;

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


public class LoginActivity extends BaseActivity implements LoginMvpView {
    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }

    private TextInputLayout textInputEmail, textInputPassword;
    LoginMvpPresenter<LoginMvpView, LoginMvpInteractor> mPresenter;
    EditText userName;
    EditText passWord;
    Button buttonLogin;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }
    private  void initView(){
        buttonLogin = findViewById(R.id.btn_login);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);

        userName = textInputEmail.getEditText();
        passWord = textInputPassword.getEditText();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginPresenter loginPresenter=new LoginPresenter<>(new LoginInteractor(new AppPreferencesHelper(getApplicationContext()),new AppApiHelper(getApplicationContext())));
        mPresenter = loginPresenter;
        mPresenter.onAttach(LoginActivity.this);
        initView();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mPresenter.setErrorInput(textInputEmail);
                 mPresenter.setErrorInput(textInputPassword);
                if(userName.getText().toString().length()>0 && passWord.getText().toString().length()>0)
                    onServerLoginClick();
            }
        }
        );
    }

    void onServerLoginClick() {
        mPresenter.onServerLoginClick(userName.getText().toString(), passWord.getText().toString());
    }

    @Override
    public void openHomeActivity() {
        Intent intent = HomeActivity.getStartIntent(LoginActivity.this);
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
    }
}
