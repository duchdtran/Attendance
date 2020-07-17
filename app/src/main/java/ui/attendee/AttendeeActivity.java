package ui.attendee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ubnd.attendance.R;

import ui.base.BaseActivity;
import ui.session.SessionActivity;

public class AttendeeActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AttendeeActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);
    }

    @Override
    protected void setUp() {

    }
}
