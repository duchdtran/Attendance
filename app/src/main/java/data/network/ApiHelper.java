
package data.network;


import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import data.model.app.AttendeeDto;
import data.model.app.ImageUserDto;
import data.model.app.MeetingDto;
import data.model.app.RecordDto;
import data.model.app.RoomDto;
import data.model.app.SessionDto;
import data.model.app.SpeakerDto;


public interface ApiHelper {
      //login
      void Login(Callback callback, String username, String passwordLogin);

      //update data
      boolean updateRecord(final Context context, final String creDate);

      boolean updateImageUser(final Context context, final String creDate);

      boolean updateMeeting(final Context context, final String creDate);

      boolean updateSession(final Context context, final String creDate);

      boolean updateSpeaker(final Context context, final String creDate);

      boolean updateAttendee(final Context context, final String creDate);

      boolean updateRoom(final Context context, List<RoomDto> allRoom);
      //upload audio
      int uploadFile(final Context context, RecordDto recordDto, Map<String, String> params);

      int uploadFileImage(final Context context, ImageUserDto imageUserDto, Map<String, String> params, Bitmap bitmap);

      //create and import data
      void createData(JSONObject jsonObject, final Context context, final Callback volley, String url);

      void importData(JSONArray jsonArray, final Context context, final Callback volley, String url);

      void creMeeting(MeetingDto meetingDto, final Context context, final Callback volley);

      void importMeetingService(JSONArray jsonArray, final Context context, final Callback volley);

      void creSession(SessionDto sessionDto, final Context context, final Callback volley);

      void importSessionService(JSONArray jsonArray, final Context context, final Callback volley);

      void creSpeaker(SpeakerDto speakerDto, final Context context, final Callback volley);

      void importSpeakerService(JSONArray jsonArray, final Context context, final Callback volley);

      void creAttendee(AttendeeDto attendeeDto, final Context context, final Callback volley);

      void importAttendeeService(JSONArray jsonArray, final Context context, final Callback volley);
}
