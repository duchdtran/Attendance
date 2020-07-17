
package ui.home.HomePage;


import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import ui.base.MvpInteractor;
import ui.base.MvpPresenter;


public interface HomePageMvpPresenter<V extends HomePageMvpView, I extends HomePageMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {
    void setData(TextView name, TextView roles, CircleImageView avatarPath);
}


