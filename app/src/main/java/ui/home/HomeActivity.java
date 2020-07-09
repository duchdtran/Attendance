package ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ubnd.attendance.R;

import service.NetworkChangeReceiver;
import ui.base.BaseActivity;

import ui.home.HomePage.HomePageFragment;
import ui.home.calendar.CalendarFragment;
import ui.home.notification.NotificationFragment;
import ui.home.setting.SettingFragment;

public class HomeActivity extends BaseActivity implements HomeMvpView{

    FragmentManager fragmentManager;
    BroadcastReceiver br;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUp();
    }


    @Override
    protected void setUp() {
//        // hien thi navigation botton
        intDrawerBottom();


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        Fragment fragment= HomePageFragment.newInstance();
        String tag = HomePageFragment.TAG;
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
        br = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();
    }


    private void intDrawerBottom() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(mSelect);
    }
    BottomNavigationView.OnNavigationItemSelectedListener mSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment=null;
            String tag = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = HomePageFragment.newInstance();
                    tag= HomePageFragment.TAG;
                    break;
                case R.id.navigation_calendar:
                    fragment = CalendarFragment.newInstance();
                    tag= CalendarFragment.TAG;
                    break;
                case R.id.navigation_notification:
                    fragment = NotificationFragment.newInstance();
                    tag= NotificationFragment.TAG;
                    break;
                case R.id.navigation_setting:
                    fragment = SettingFragment.newInstance();
                    tag= SettingFragment.TAG;
                    break;
            }
            if(fragment!=null){
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.commit();
            }
            return true;
        }
    };

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(br, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(br, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(br);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}
