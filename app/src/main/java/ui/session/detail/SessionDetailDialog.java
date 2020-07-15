package ui.session.detail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.ubnd.attendance.R;

import ui.qrcode.ScanQRCodeActivity;

public class SessionDetailDialog extends DialogFragment {
    Toolbar toolbar;
    Button btn;
    CheckBox cbxWifi, cbxQRCode;
    View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.activity_session_detail, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        initView();
        setUp();
        return builder.create();
    }
    void initView(){
        toolbar = view.findViewById(R.id.toolbar);
        btn = view.findViewById(R.id.btn);
        cbxWifi = view.findViewById(R.id.cbx_wifi);
        cbxQRCode = view.findViewById(R.id.cbx_qrcode);
    }
    void setUp(){
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.option_scan){
                            Intent intent = ScanQRCodeActivity.getStartIntent(getContext());
                            startActivity(intent);
                            cbxQRCode.setChecked(true);
                        }
                        return true;
                    }
                });

        toolbar.inflateMenu(R.menu.option_menu);
        toolbar.getMenu().setGroupVisible(R.id.group_detail, true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbxWifi.setChecked(true);
            }
        });
    }
}
