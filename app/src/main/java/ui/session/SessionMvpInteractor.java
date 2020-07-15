
package ui.session;


import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

import data.model.app.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface SessionMvpInteractor extends MvpInteractor {

    ArrayList<SessionDto> getAllSession();
    public void importSessionService(JSONArray jsonArray, final Context context, final Callback volley);
}
