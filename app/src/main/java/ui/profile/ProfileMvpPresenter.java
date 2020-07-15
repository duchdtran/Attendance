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


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;

import ui.base.MvpPresenter;
import ui.login.LoginMvpInteractor;
import ui.login.LoginMvpView;

/**
 * Created by janisharali on 27/01/17.
 */


public interface ProfileMvpPresenter<V extends ProfileMvpView,
        I extends ProfileMvpInteractor> extends MvpPresenter<V, I> {
    public  void onServerLogoutClick();
    public  void setDataProfile(TextView tvName, TextView tvEmail, TextView tvPhone, TextView tvAddress) ;
}
