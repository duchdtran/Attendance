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
import android.util.Base64;
import android.util.Log;
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


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ubnd.attendance.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.model.ImageDto;
import data.model.app.ImageUserDto;
import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import ui.base.BaseActivity;
import ui.login.LoginActivity;
import ultils.Recognize;
import ultils.SingletonVolley;

import static data.network.ApiEndPoint.ENDPOINT_SERVER_UPLOAD_IMAGE_USER;


public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    private static final int REQUEST_PERMISSION_CODE = 21;
    public final static int TAKE_PHOTO = 276;

    Button btnLogout, btnAddImage;
    TextView tvName, tvEmail, tvAddress, tvPhone;
    GridView gridView;
    ImageAdapter adapter;

    AppPreferencesHelper preferencesHelper;

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

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();

            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userID",1);
                jsonObject.put("nameUser","Lê Anh Dũng");
                jsonObject.put("data",encoded);
                postData(ENDPOINT_SERVER_UPLOAD_IMAGE_USER,jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
        //adapter.addItems(bitmap);
    }

    @Override
    public void initRecycle(List<ImageUserDto> listImage) {
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

    public void postData(String url, JSONObject data){

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("TAg",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                preferencesHelper = new AppPreferencesHelper(ProfileActivity.this);
                String token = preferencesHelper.getToken();
                headers.put("Content-Type", "application/json;charset=UTF-8");
                headers.put("Authorization","Bearer " + token);
                return headers;
            }
        };
        SingletonVolley.getInstance(ProfileActivity.this).getRequestQueue().add(jsonobj);
    }
}
