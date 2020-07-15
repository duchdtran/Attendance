
package ui.meeting.detail;


import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import data.model.app.RoomDto;
import data.model.app.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface MeetingDetailMvpInteractor extends MvpInteractor {

    List<SessionDto> getSessionByIdMeeting(int id);
    SessionDto getDefaultSession();
    List<RoomDto> getAllRoom();
    boolean isSessionExists(String name);
    RoomDto getRoomByName(String name);
    void importSessionService(JSONArray jsonArray, final Context context, final Callback volley);
}
