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

package data.network;


import android.content.Context;

import org.json.JSONArray;

import java.util.Map;

import data.model.MeetingDto;
import data.model.RecordDto;

/**
 * Created by janisharali on 27/01/17.
 */

public interface ApiHelper {

      void Login(Callback callback, String username, String passwordLogin);
      void  updateRecord(final Context context, final String creDate);
      void  updateMeeting(final Context context, final String creDate);
      void  updateSession(final Context context, final String creDate);
      int uploadFile(final Context context, RecordDto recordDto, Map<String, String> params);
      void creMeeting(MeetingDto meetingDto, final Context context, final Callback volley);
      void importMeetingService(JSONArray jsonArray, final Context context, final Callback volley);
}
