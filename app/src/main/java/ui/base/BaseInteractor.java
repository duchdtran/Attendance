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

package ui.base;


import android.content.Context;
import android.os.CountDownTimer;

import data.db.repository.RoomDAO;
import data.network.ApiHelper;
import data.prefs.PreferencesHelper;
import ultils.SingletonDAO;


/**
 * Created by janisharali on 20/07/17.
 */

public class BaseInteractor implements MvpInteractor {

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    public BaseInteractor(PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    @Override
    public void setAccessToken(String accessToken) {
        getPreferencesHelper().setToken(accessToken);
    }


    @Override
    public void setUserAsLoggedOut() {
        getPreferencesHelper().setUser(null,null, null);
    }

    @Override
    public void updateData(final Context context){
        getApiHelper().updateRoom(context, SingletonDAO.getRoomDAOInstance(context).getAllItems());
        getApiHelper().updateSpeaker(context,getPreferencesHelper().getCreDateSpeaker());
        getApiHelper().updateMeeting(context,getPreferencesHelper().getCreDateMeeting());
        getApiHelper().updateImageUser(context,getPreferencesHelper().getCreDateImageUser());
        new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getApiHelper().updateSession(context,getPreferencesHelper().getCreDateSession());
            }
        }.start();
        new CountDownTimer(2000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getApiHelper().updateRecord(context,getPreferencesHelper().getCreDateRecord());
            }
        }.start();
        new CountDownTimer(3000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getApiHelper().updateAttendee(context,getPreferencesHelper().getCreDateAttendee());
            }
        }.start();
    }

}
