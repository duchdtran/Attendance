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

package ui.profile;


import data.model.app.UserDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ui.login.LoginMvpInteractor;


/**
 * Created by janisharali on 20/07/17.
 */

public class ProfileInteractor extends BaseInteractor
        implements ProfileMvpInteractor {

    public ProfileInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }


    @Override
    public void logout() {
        getPreferencesHelper().logout();
    }

    @Override
    public UserDto getDataProfile() {
        UserDto user = getPreferencesHelper().getUser();
        return user;
    }

}
