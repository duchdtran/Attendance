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
    //login
    public static final String ENDPOINT_SERVER_LOGIN = AppConstants.BASE_URL
            + "/login-service";

    //update data
    public static final String ENDPOINT_SERVER_UPDATE_IMAGE_USER = AppConstants.BASE_URL
            + "/update-image-user";
    public static final String ENDPOINT_SERVER_UPDATE_RECORD = AppConstants.BASE_URL
            + "/update-record";
    public static final String ENDPOINT_SERVER_UPDATE_MEETING = AppConstants.BASE_URL
            + "/update-meeting";
    public static final String ENDPOINT_SERVER_UPDATE_SESSION = AppConstants.BASE_URL
            + "/update-session";
    public static final String ENDPOINT_SERVER_UPDATE_SPEAKER = AppConstants.BASE_URL
            + "/update-speaker";
    public static final String ENDPOINT_SERVER_UPDATE_ATTENDEE= AppConstants.BASE_URL
            + "/update-attendee";
    public static final String ENDPOINT_SERVER_UPDATE_ROOM= AppConstants.BASE_URL
            + "/update-room";
    //upload audio
    public static final String ENDPOINT_SERVER_UPLOAD = AppConstants.BASE_URL
            + "/upload";
    //upload image user
    public static final String ENDPOINT_SERVER_UPLOAD_IMAGE_USER = AppConstants.BASE_URL
            + "/upload/user/fileupload";

    //create data
    public static final String ENDPOINT_SERVER_CREATE_MEETING = AppConstants.BASE_URL
            + "/create-meeting";
    public static final String ENDPOINT_SERVER_IMPORT_MEETING = AppConstants.BASE_URL
            + "/import-meeting";
    public static final String ENDPOINT_SERVER_CREATE_SESSION = AppConstants.BASE_URL
            + "/create-session";
    public static final String ENDPOINT_SERVER_IMPORT_SESSION = AppConstants.BASE_URL
            + "/import-session";
    public static final String ENDPOINT_SERVER_CREATE_SPEAKER = AppConstants.BASE_URL
            + "/create-speaker";
    public static final String ENDPOINT_SERVER_IMPORT_SPEAKER = AppConstants.BASE_URL
            + "/import-speaker";
    public static final String ENDPOINT_SERVER_CREATE_ATTENDEE= AppConstants.BASE_URL
            + "/create-attendee";
    public static final String ENDPOINT_SERVER_IMPORT_ATTENDEE = AppConstants.BASE_URL
            + "/import-attendee";
    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
