
package ui.meeting;


import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import data.model.app.MeetingDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface MeetingMvpInteractor extends MvpInteractor {

    List<MeetingDto> getAllMeeting();
    public void importMeetingService(JSONArray jsonArray, final Context context, final Callback volley);
}
