
package ui.meeting.detail;


import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import data.db.repository.RoomDAO;
import data.db.repository.SessionDAO;
import data.model.app.RoomDto;
import data.model.app.SessionDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ultils.SingletonDAO;


public class MeetingDetailInteractor extends BaseInteractor
        implements MeetingDetailMvpInteractor {

    SessionDAO sessionDAO;
    RoomDAO roomDAO;
    Context context;

    public MeetingDetailInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);

        this.context = context;
        this.sessionDAO = SingletonDAO.getSessionDAOInstace(context);
        this.roomDAO = SingletonDAO.getRoomDAOInstance(context);
    }

    @Override
    public List<SessionDto> getSessionByIdMeeting(int id) {
        return sessionDAO.getSessionByIdMeeting(id);
    }

    @Override
    public SessionDto getDefaultSession() {
        return sessionDAO.getItemById(1);
    }

    @Override
    public List<RoomDto> getAllRoom() {
        return roomDAO.getAllItems();
    }

    @Override
    public boolean isSessionExists(String name) {
        return sessionDAO.isExits(name);
    }

    @Override
    public RoomDto getRoomByName(String name) {
        return roomDAO.getRoomByName(name);
    }

    @Override
    public void importSessionService(JSONArray jsonArray, Context context, Callback volley) {
        getApiHelper().importSessionService(jsonArray, context, volley);
    }
}
