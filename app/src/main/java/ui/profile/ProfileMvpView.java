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


import android.graphics.Bitmap;

import java.util.List;

import data.model.app.ImageUserDto;
import ui.base.MvpView;

/**
 * Created by janisharali on 27/01/17.
 */

public interface ProfileMvpView extends MvpView {
    void openLoginActivity();

    void updateImage(ImageUserDto image);
    void initRecycle(List<ImageUserDto> listImage);
    void updateAdapter();
}
