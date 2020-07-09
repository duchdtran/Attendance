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

package ui.splash;


import android.content.Context;

import data.model.UserDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;


/**
 * Created by janisharali on 20/07/17.
 */

public class SplashInteractor extends BaseInteractor
        implements SplashMvpInteractor {

    public SplashInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }

    public UserDto getUser(){
        return getPreferencesHelper().getUser();
    }

    @Override
    public void Login(Callback callback, String username, String passwordLogin) {
        getApiHelper().Login(callback,username,passwordLogin);
    }

    @Override
    public void updateMeeting(Context context, String creDate) {
        getApiHelper().updateMeeting(context,creDate);
    }

    @Override
    public void updateSession(Context context, String creDate) {
        getApiHelper().updateSession(context,creDate);
    }

    @Override
    public void updateRecord(Context context, String creDate) {
        getApiHelper().updateRecord(context,creDate);
    }


}
