/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package ui.home.calendar;


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

public class CalendarInteractor extends BaseInteractor
        implements CalendarMvpInteractor {

    SessionDAO sessionDAO;
    Context context;
    public CalendarInteractor(PreferencesHelper preferencesHelper,
                              ApiHelper apiHelper) {

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
