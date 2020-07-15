
package ui.meeting;


import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import data.db.repository.MeetingDAO;
import data.model.app.MeetingDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ultils.SingletonDAO;


public class MeetingInteractor extends BaseInteractor
        implements MeetingMvpInteractor {

    MeetingDAO meetingDAO;
    Context context;
    public MeetingInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
        this.context=context;
        this.meetingDAO= SingletonDAO.getMeetingDAOInstance(context);
    }

    @Override
    public List<MeetingDto> getAllMeeting() {
        return meetingDAO.getAllItems();
    }

    @Override
    public void importMeetingService(JSONArray jsonArray, Context context, Callback volley) {
        getApiHelper().importMeetingService(jsonArray,context,volley);
    }
}
