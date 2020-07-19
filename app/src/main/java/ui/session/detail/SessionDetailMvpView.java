
package ui.session.detail;

import android.graphics.Bitmap;

import java.util.List;

import ui.base.MvpView;

public interface SessionDetailMvpView extends MvpView {
    void OpenQRCodeActivity();
    void OpenAttendeeActivity();
    void OpenSessionImageActivity();
    void updateAdapter();
    void initRecycle(List<Bitmap> listImage);
}
