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


import ultils.AppConstants;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_SERVER_LOGIN = AppConstants.BASE_URL
            + "/login-service";
    public static final String ENDPOINT_SERVER_UPDATERECORD = AppConstants.BASE_URL
            + "/update-record";
    public static final String ENDPOINT_SERVER_UPDATEMEETING = AppConstants.BASE_URL
            + "/update-meeting";
    public static final String ENDPOINT_SERVER_UPDATESESSION = AppConstants.BASE_URL
            + "/update-session";
    public static final String ENDPOINT_SERVER_UPLOAD = AppConstants.BASE_URL
            + "/upload";
    public static final String ENDPOINT_SERVER_CREATEMEETING = AppConstants.BASE_URL
            + "/create-meeting";
    public static final String ENDPOINT_SERVER_IMPORT_MEETING = AppConstants.BASE_URL
            + "/import-meeting";
    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
