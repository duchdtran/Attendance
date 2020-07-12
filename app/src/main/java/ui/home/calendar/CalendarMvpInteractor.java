

package ui.home.calendar;


import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

import data.model.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface CalendarMvpInteractor extends MvpInteractor {
    ArrayList<SessionDto> getAllSession();
    public void importSessionService(JSONArray jsonArray, final Context context, final Callback volley);


}
