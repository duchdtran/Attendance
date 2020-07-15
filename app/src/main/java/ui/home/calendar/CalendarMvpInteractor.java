

package ui.home.calendar;


import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

import data.model.app.SessionDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface CalendarMvpInteractor extends MvpInteractor {
    ArrayList<SessionDto> getSessionByDate(int day, int month, int year);
    public void importSessionService(JSONArray jsonArray, final Context context, final Callback volley);


}
