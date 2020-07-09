
package ui.profile;


import android.widget.TextView;


import data.model.UserDto;
import data.network.Callback;
import data.prefs.AppPreferencesHelper;
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
}

