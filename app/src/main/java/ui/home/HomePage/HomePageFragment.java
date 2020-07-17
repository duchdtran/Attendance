
package ui.home.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ubnd.attendance.R;

import data.network.AppApiHelper;
import data.prefs.AppPreferencesHelper;
import de.hdodenhof.circleimageview.CircleImageView;
import ui.base.BaseFragment;
import ui.meeting.MeetingActivity;
import ui.profile.ProfileActivity;
import ui.session.SessionActivity;


public class HomePageFragment extends BaseFragment implements
        HomePageMvpView, View.OnClickListener {
    public static final String TAG = "HomePageFragment";

    CircleImageView imvAvatar;
    TextView tvName, tvRoles;
    CardView cvMeeting, cvSession, cvAttendance, cvStatistic;

    HomePageMvpPresenter<HomePageMvpView, HomePageMvpInteractor> mPresenter;

    public static HomePageFragment newInstance() {
        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        HomePagePresenter homePresenter=new HomePagePresenter<>(new HomePageInteractor(new AppPreferencesHelper(getContext()),new AppApiHelper(getContext())));
        mPresenter =homePresenter;
        mPresenter.onAttach(this);
        initView();
        return view;
    }

    @Override
    protected void setUp(View view) {
        imvAvatar.setOnClickListener(this);
        cvMeeting.setOnClickListener(this);
        cvSession.setOnClickListener(this);
        cvAttendance.setOnClickListener(this);
        cvStatistic.setOnClickListener(this);

        mPresenter.setData(tvName, tvRoles, imvAvatar);
    }

    private void initView(){
        imvAvatar = view.findViewById(R.id.imv_avatar);
        tvName = view.findViewById(R.id.tv_name);
        tvRoles = view.findViewById(R.id.tv_roles);
        cvMeeting = view.findViewById(R.id.cv_meeting);
        cvSession = view.findViewById(R.id.cv_session);
        cvAttendance = view.findViewById(R.id.cv_attendance);
        cvStatistic = view.findViewById(R.id.cv_statistic);
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.imv_avatar:
                intent = ProfileActivity.getStartIntent(getContext());
                break;
            case R.id.cv_meeting:
                intent = MeetingActivity.getStartIntent(getContext());
                break;
            case R.id.cv_session:
                intent = SessionActivity.getStartIntent(getContext());
                break;
            case R.id.cv_attendance:
                //intent = ProfileActivity.getStartIntent(getContext());
                break;
            case R.id.cv_statistic:
                //intent = ProfileActivity.getStartIntent(getContext());
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
