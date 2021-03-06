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

package ui.home.HomePage;


import data.model.app.UserDto;
import data.network.ApiHelper;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;

public class HomePageInteractor extends BaseInteractor
        implements HomePageMvpInteractor {


    public HomePageInteractor(PreferencesHelper preferencesHelper,
                              ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
    }


    @Override
    public UserDto getDataUser() {
        return getPreferencesHelper().getUser();
    }

    @Override
    public String getRoles() {
        return getPreferencesHelper().getRoles();
    }
}
