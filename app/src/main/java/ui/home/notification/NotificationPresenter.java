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

package ui.home.notification;

import ui.base.BasePresenter;
import ui.home.calendar.CalendarMvpInteractor;
import ui.home.calendar.CalendarMvpPresenter;
import ui.home.calendar.CalendarMvpView;

/**
 * Created by janisharali on 25/05/17.
 */

public class NotificationPresenter<V extends NotificationMvpView,
        I extends NotificationMvpInteractor> extends BasePresenter<V, I>
        implements NotificationMvpPresenter<V, I> {

    public NotificationPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


}
