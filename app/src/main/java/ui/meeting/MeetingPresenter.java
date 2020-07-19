
package ui.meeting;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ubnd.attendance.R;

import java.util.List;

import data.model.app.MeetingDto;
import ui.base.BasePresenter;

public class MeetingPresenter<V extends MeetingMvpView, I extends MeetingMvpInteractor>
        extends BasePresenter<V, I> implements MeetingMvpPresenter<V, I> {

    private static final String TAG = "MeetingPresenter";

    public MeetingPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared() {
        List<MeetingDto> meetingList = getInteractor().getAllMeeting();
        getMvpView().initRecycle(meetingList);
    }

    @Override
    public void showDialogMeeting(Context context) {

    }

    @Override
    public void showDialogFilter(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_filter, null);
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void importData(String path, int id, Context context) {

    }
}

