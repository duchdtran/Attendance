package ui.qrcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ui.base.BaseActivity;
import ui.session.detail.SessionDetailActivity;
import ui.session.detail.SessionDetailInteractor;
import ui.session.detail.SessionDetailMvpInteractor;
import ui.session.detail.SessionDetailMvpPresenter;
import ui.session.detail.SessionDetailMvpView;
import ui.session.detail.SessionDetailPresenter;

public class ScanQRCodeActivity extends BaseActivity implements ScanQRCodeMvpView, ZXingScannerView.ResultHandler {
    public static final String TAG = "MeetingActivity";

    ZXingScannerView scannerView;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ScanQRCodeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        setUp();
    }
    @Override
    protected void setUp() {

    }

    @Override
    public void handleResult(Result rawResult) {
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
