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

import java.util.List;

import data.model.app.SessionDto;
import ui.base.BasePresenter;

/**
 * Created by janisharali on 25/05/17.
 */

public class CalendarPresenter<V extends CalendarMvpView,
        I extends CalendarMvpInteractor> extends BasePresenter<V, I>
        implements CalendarMvpPresenter<V, I> {

    public CalendarPresenter(I mvpInteractor) {
        super(mvpInteractor);
    }


    @Override
    public void onViewPrepared() {
        List<SessionDto> meetingList = getInteractor().getAllSession();
        getMvpView().initRecycle(meetingList);
    }
}
