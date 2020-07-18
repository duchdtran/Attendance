package ui.session.detail;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.model.app.SessionDto;
import ui.attendee.AttendeeActivity;
import ui.profile.ProfileActivity;
import ui.qrcode.ScanQRCodeActivity;
import ui.session.SessionAdapter;

import static android.app.Activity.RESULT_OK;

public class SessionDetailDialog extends DialogFragment{
    private static final int TAKE_PHOTO = 246;
    Toolbar toolbar;
    TextView tvNameSession, tvNameMeeting, tvRoom, tvTimeStart, tvTimeEnd;
    Button btnWifi, btnTakePhoto;
    CheckBox cbxWifi, cbxQRCode;
    View view;
    SessionDto sessionDto;

    private String currentPhotoPath;


    RecyclerView recyclerView;
    ImageSessionAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.activity_session_detail, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        Bundle bundle = getArguments();
        sessionDto = (SessionDto)bundle.getSerializable("session");
        initView();
        setUp();
        return builder.create();
    }
    void initView(){
        toolbar = view.findViewById(R.id.toolbar);
        btnWifi = view.findViewById(R.id.btn_wifi);
        btnTakePhoto = view.findViewById(R.id.btn_take_photo);
        cbxWifi = view.findViewById(R.id.cbx_wifi);
        cbxQRCode = view.findViewById(R.id.cbx_qrcode);

        tvNameMeeting = view.findViewById(R.id.tv_name_meeting);
        tvNameSession = view.findViewById(R.id.tv_name_session);
        tvRoom = view.findViewById(R.id.tv_address);
        tvTimeStart = view.findViewById(R.id.tv_time_start);
        tvTimeEnd = view.findViewById(R.id.tv_time_end);
    }
    void setUp(){
        if(sessionDto.getMeetingDto() != null)
            tvNameMeeting.setText(sessionDto.getMeetingDto().getName());
        tvNameSession.setText(sessionDto.getName());
        if(sessionDto.getRoomDto() != null)
            tvRoom.setText(sessionDto.getRoomDto().getRoomName());
        tvTimeStart.setText(sessionDto.getTimeStart());
        tvTimeEnd.setText(sessionDto.getTimeEnd());

        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.option_scan){
                            OpenQRCodeActivity();
                        }
                        else if(item.getItemId() == R.id.option_attendee_session){
                            OpenAttendeeActivity();
                        }
                        return true;
                    }
                });

        toolbar.inflateMenu(R.menu.option_menu);
        toolbar.getMenu().setGroupVisible(R.id.group_detail_session, true);

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbxWifi.setChecked(true);
            }
        });
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "photo";
                File storageDirectory = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                try {
                    File imageFile = File.createTempFile(filename, ".png", storageDirectory);

                    currentPhotoPath = imageFile.getAbsolutePath();

                    Uri imageUri = FileProvider.getUriForFile(getContext(), "com.ubnd.attendance.fileprovider", imageFile);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        setRoles(true);
    }

    void setRoles(boolean isSecretary){

        if(isSecretary){
            toolbar.getMenu().findItem(R.id.option_scan).setVisible(false);
            view.findViewById(R.id.container_secretary).setVisibility(View.VISIBLE);
            view.findViewById(R.id.container_attendee).setVisibility(View.GONE);
            initRecycle(null);
        }else{
            toolbar.getMenu().findItem(R.id.option_attendee_session).setVisible(false);
            view.findViewById(R.id.container_secretary).setVisibility(View.GONE);
            view.findViewById(R.id.container_attendee).setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_PHOTO && resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

            updateImage(bitmap);
        }
    }

    void updateImage(Bitmap bitmap) {
        adapter.addItems(bitmap);
    }

    void initRecycle(List<Bitmap> listImage) {
        recyclerView = view.findViewById(R.id.recycle_view);
        List<Bitmap> bitmapList = new ArrayList<>();
        adapter = new ImageSessionAdapter(bitmapList);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    void updateAdapter() {
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    void OpenQRCodeActivity(){
        Intent intent = ScanQRCodeActivity.getStartIntent(getContext());
        startActivity(intent);
    }

    void OpenAttendeeActivity(){
        Intent intent = AttendeeActivity.getStartIntent(getContext());
        intent.putExtra("session", sessionDto);
        startActivity(intent);
    }

}
