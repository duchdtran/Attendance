
package ui.login;


import android.content.Context;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import data.db.repository.RoomDAO;
import data.model.app.RoomDto;
import data.model.app.UserDto;
import data.network.Callback;
import ui.base.BasePresenter;
import ultils.SingletonDAO;


public class LoginPresenter<V extends LoginMvpView, I extends LoginMvpInteractor>
        extends BasePresenter<V, I> implements LoginMvpPresenter<V, I> {

    private static final String TAG = "HomePresenter";

    public LoginPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }

    @Override
    public void onServerLoginClick(String username, String password) {
        getMvpView().showLoading();
        final Callback volleyCallback = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                String result;
                JSONObject jsonObject = null;
                if (response instanceof JSONObject) {
                    jsonObject = (JSONObject) response;
                }
                try {
                    if (jsonObject != null) {
                        result = jsonObject.getString("response");
                        if ("success".equals(result)) {
                            JSONObject user = jsonObject.getJSONObject("user");
                            int userId = user.getInt("userId");
                            String fullName = user.getString("fullName");
                            String email = user.getString("email");
                            String phone = user.getString("phone");
                            String address = user.getString("address");
                            String token = jsonObject.getString("token");
                            UserDto userDto = new UserDto(userId, fullName, email, phone, address);
                            getInteractor().getPreferencesHelper().setUser(userDto, token);
                            //updateData();
                            getMvpView().openHomeActivity();
                        } else{
                            getMvpView().showMessage("Đăng nhập không thành công");
                            Log.d("Volley Error",result);
                            getMvpView().hideLoading();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void getError(Object error, int stt) {
                getMvpView().hideLoading();
                Log.d("Volley Error:",error.toString());
                getMvpView().showMessage("Đăng nhập không thành công");
            }
        };
        getInteractor().Login(volleyCallback, username, password);

    }

    @Override
    public void setErrorInput(TextInputLayout edtInput) {
        if(edtInput.getEditText().getText().length()<=0)
            edtInput.setError("Không được bỏ trống!");
        else edtInput.setError(null);
    }

    @Override
    public void updateData() {
        Context context =(Context)getMvpView();

        RoomDto r1 = new RoomDto("Phòng 301","Phòng tầng 3",0);
        RoomDto r2 = new RoomDto("Phòng 402","Phòng tầng 4",0);
        RoomDto r3 = new RoomDto("Phòng 503","Phòng tầng 5",0);
        RoomDAO roomDAO = SingletonDAO.getRoomDAOInstance((Context)getMvpView());
        roomDAO.addRoom(r1);
        roomDAO.addRoom(r2);
        roomDAO.addRoom(r3);
        String creDateRecord,creDateMeeting,creDateSession;
        creDateMeeting = getInteractor().getPreferencesHelper().getCreDateMeeting();
        creDateSession = getInteractor().getPreferencesHelper().getCreDateSession();
        creDateRecord  = getInteractor().getPreferencesHelper().getCreDateRecord();
        getInteractor().updateMeeting(context,creDateMeeting);
        getInteractor().updateSession(context,creDateSession);
        getInteractor().updateRecord(context,creDateRecord);
    }


}

