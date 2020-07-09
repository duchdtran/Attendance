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


import data.network.ApiHelper;
import data.prefs.PreferencesHelper;


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
       getPreferencesHelper().setUser(null,null);
    }


}
