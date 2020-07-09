
package ui.session;


import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

import data.db.repository.SessionDAO;
import data.model.SessionDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ultils.SingletonDAO;


public class SessionInteractor extends BaseInteractor
        implements SessionMvpInteractor {

    SessionDAO sessionDAO;
    Context context;
    public SessionInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
        this.context=context;
        this.sessionDAO= SingletonDAO.getSessionDAOInstace(context);
    }

    @Override
    public ArrayList<SessionDto> getAllSession() {
        return sessionDAO.getAllItems();
    }

    @Override
    public void importSessionService(JSONArray jsonArray, Context context, Callback volley) {
        getApiHelper().importMeetingService(jsonArray,context,volley);
    }

}
