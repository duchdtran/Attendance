
package ui.profile;


import android.graphics.Bitmap;
import android.widget.TextView;


import java.util.List;

import data.model.app.SessionDto;
import data.model.app.UserDto;
import data.network.Callback;
import ui.base.BasePresenter;



public class ProfilePresenter<V extends ProfileMvpView, I extends ProfileMvpInteractor>
        extends BasePresenter<V, I> implements ProfileMvpPresenter<V, I> {
    public ProfilePresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onServerLogoutClick() {
        getInteractor().logout();
        getMvpView().openLoginActivity();
    }

    @Override
    public void setDataProfile(TextView tvName, TextView tvEmail, TextView tvPhone, TextView tvAddress) {
        UserDto user = getInteractor().getDataProfile();
        tvName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        tvAddress.setText(user.getAddress());
        tvPhone.setText(user.getPhone());
    }

    @Override
    public void onViewPrepared() {
        List<Bitmap> listImage = getInteractor().getAllImage();
        getMvpView().initRecycle(listImage);
    }


}

