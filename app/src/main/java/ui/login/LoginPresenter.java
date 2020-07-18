
package ui.login;


import android.content.Context;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import data.db.repository.DutyDAO;
import data.db.repository.RoomDAO;
import data.model.app.DutyDto;
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
                            String roles = "Thư kí";
                            String fullName = user.getString("fullName");
                            String email = user.getString("email");
                            String phone = user.getString("phone");
                            String address = user.getString("address");
                            String token = jsonObject.getString("token");
                            UserDto userDto = new UserDto(userId, fullName, email, phone, address);
                            getInteractor().getPreferencesHelper().setUser(userDto, token, roles);
                            updateData();
                            getMvpView().openHomeActivity();
                        } else{
                            getMvpView().showMessage("Đăng nhập không thành công");
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
        List<DutyDto> list = SingletonDAO.getDutyDAOInstace(context).getAll();
        if(list.size()!=3){
            DutyDto d1 = new DutyDto("chuToa"	,"Chủ Tọa");
            DutyDto d2 = new DutyDto("thuKy"	,"Thư Ký");
            DutyDto d3 = new DutyDto("thanhVien"	,"Thành Viên");
            DutyDAO dutyDAO = SingletonDAO.getDutyDAOInstace(context);
            dutyDAO.addDuty(d1);
            dutyDAO.addDuty(d2);
            dutyDAO.addDuty(d3);
            getInteractor().updateData(context);
        }
        else{
            getInteractor().updateData(context);
        }
    }


}

