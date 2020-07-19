package ui.qrcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ui.base.BaseActivity;

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
        Intent intent = new Intent();
        intent.putExtra("qrcode", rawResult.getText());
        setResult(Activity.RESULT_OK, intent);
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
