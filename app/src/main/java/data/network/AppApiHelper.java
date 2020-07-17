package data.network;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.model.app.AttendeeDto;
import data.model.app.MeetingDto;
import data.model.app.RecordDto;
import data.model.app.RoomDto;
import data.model.app.SessionDto;
import data.model.app.SpeakerDto;
import data.prefs.AppPreferencesHelper;
import ultils.JsonUltil;
import ultils.NetworkUtils;
import ultils.SingletonDAO;
import ultils.SingletonVolley;


public class AppApiHelper implements ApiHelper {
    Context context;
    AppPreferencesHelper preferencesHelper;
    String root = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    public AppApiHelper(Context context) {
        this.context = context;
        preferencesHelper = new AppPreferencesHelper(context);
    }

    //dich vu login
    @Override
    public void Login(final Callback callback, String username, String passwordLogin) {
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        JSONObject object = new JSONObject();
        try {
            final int stt = 0;
            object.put("username", username);
            object.put("password", passwordLogin);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.ENDPOINT_SERVER_LOGIN, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.getRespone(response, stt);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.getError(error, stt);
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> param = new HashMap<>();
                    param.put("Content-Type", "application/json");
                    return param;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // dich vu cap nhat database
    public boolean updateData(final Callback volley, final Context context, String creDate, final String s) {
        String url = "";
        if (s.equals("record")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_RECORD;
        else if (s.equals("meeting")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_MEETING;
        else if (s.equals("session")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_SESSION;
        else if (s.equals("speaker")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_SPEAKER;
        else if (s.equals("attendee")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_ATTENDEE;
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        final JSONArray jsonArray = new JSONArray();
        try {
            final int stt = 0;
            jsonArray.put(0, creDate);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    volley.getRespone(response, stt);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    String token = preferencesHelper.getToken();
                    Map<String, String> param = new HashMap<>();
                    param.put("Authorization", "Bearer " + token);
                    param.put("Content-Type", "application/json");
                    return param;
                }
            };

            requestQueue.add(jsonArrayRequest);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRecord(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<RecordDto> dtoList = JsonUltil.converToArr(response.toString(), RecordDto[].class);
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        RecordDto recordDto = dtoList.get(i);
                        recordDto.setRecordId(null);
                        recordDto.getSessionDto().setSessionId(SingletonDAO.getSessionDAOInstace(context).getSessionByName(recordDto.getSessionDto().getName()).getSessionId());
                        recordDto.setStatusInApp("Đã upload");
                        boolean tg = false;
                        if (recordDto.getCreDate().equals(recordDto.getModDate())) {
                            tg = SingletonDAO.getRecordDAOInstance(context).addRecording(recordDto);
                            if (recordDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = recordDto.getModDate();

                        } else {
                            RecordDto recordDto1 = SingletonDAO.getRecordDAOInstance(context).getItemByPath(recordDto.getPath());
                            if(recordDto1!=null)
                                SingletonDAO.getRecordDAOInstance(context).updateRecord(recordDto);
                            else
                                tg = SingletonDAO.getRecordDAOInstance(context).addRecording(recordDto);
                            if (recordDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = recordDto.getModDate();
                        }
                    }
                    preferencesHelper.setCreDateRecord(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }
        };
        return updateData(volley, context, creDate, "record");
    }

    @Override
    public boolean updateMeeting(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                Log.d("AAAM",response.toString());
                List<MeetingDto> dtoList = JsonUltil.converToArr(response.toString(), MeetingDto[].class);
                Log.d("sizemeeting",dtoList.size()+"");
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        MeetingDto meetingDto = dtoList.get(i);

                        meetingDto.setMeetingId(null);
                        boolean tg = false;
                        if (meetingDto.getCreDate().equals(meetingDto.getModDate())) {
                            tg = SingletonDAO.getMeetingDAOInstance(context).addMeeting(meetingDto);
                            if (meetingDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = meetingDto.getModDate();

                        } else {
                            MeetingDto meetingDto1 = SingletonDAO.getMeetingDAOInstance(context).getMeetingByName(meetingDto.getName());
                            if(meetingDto1!=null)
                                SingletonDAO.getMeetingDAOInstance(context).updateMeeting(meetingDto);
                            else
                                tg = SingletonDAO.getMeetingDAOInstance(context).addMeeting(meetingDto);
                            if (meetingDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = meetingDto.getModDate();
                        }
                    }
                    preferencesHelper.setCreDateMeeting(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }
        };
        return updateData(volley, context, creDate, "meeting");
    }

    @Override
    public boolean updateSession(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                Log.d("AAA",response.toString());
                List<SessionDto> dtoList = JsonUltil.converToArr(response.toString(), SessionDto[].class);
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        SessionDto sessionDto = dtoList.get(i);
                        boolean tg = false;
                        if (sessionDto.getCreDate().equals(sessionDto.getModDate())) {
                            sessionDto.setSessionId(null);
                            Log.d("namemeeting",sessionDto.getMeetingDto().getName());
                            MeetingDto meetingDto = SingletonDAO.getMeetingDAOInstance(context).getMeetingByName(sessionDto.getMeetingDto().getName());
                            if(meetingDto!=null)
                                sessionDto.getMeetingDto().setMeetingId(meetingDto.getMeetingId());
                            tg = SingletonDAO.getSessionDAOInstace(context).addSession(sessionDto);
                            if (sessionDto.getModDate().compareTo(maxDate) > 0) {
                                maxDate = sessionDto.getModDate();
                            }

                        } else {
                            SessionDto sessionDto1 = SingletonDAO.getSessionDAOInstace(context).getSessionByName(sessionDto.getName());
                            if(sessionDto1!=null)
                                SingletonDAO.getSessionDAOInstace(context).updateSession(sessionDto);
                            else
                                tg = SingletonDAO.getSessionDAOInstace(context).addSession(sessionDto);
                            if (sessionDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = sessionDto.getModDate();
                        }
                    }
                    preferencesHelper.setCreDateSession(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }

        };
        return updateData(volley, context, creDate, "session");
    }

    @Override
    public boolean updateSpeaker(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<SpeakerDto> dtoList = JsonUltil.converToArr(response.toString(), SpeakerDto[].class);
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        SpeakerDto speakerDto = dtoList.get(i);

                        if (speakerDto.getCreDate().equals(speakerDto.getModDate())) {
                            SingletonDAO.getSpeakerDAOInstace(context).addSpeaker(speakerDto);
                            if (speakerDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = speakerDto.getModDate();
                        } else {
                            SpeakerDto speakerDto1 = SingletonDAO.getSpeakerDAOInstace(context).getSpeaker(speakerDto);
                            if(speakerDto1!=null)
                                SingletonDAO.getSpeakerDAOInstace(context).updateSpeaker(speakerDto);
                            else
                                SingletonDAO.getSpeakerDAOInstace(context).addSpeaker(speakerDto);
                            if (speakerDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = speakerDto.getModDate();
                        }
                    }
                    preferencesHelper.setCreDateSpeaker(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }

        };
        return updateData(volley, context, creDate, "speaker");
    }

    @Override
    public boolean updateAttendee(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<AttendeeDto> dtoList = JsonUltil.converToArr(response.toString(), AttendeeDto[].class);
                if (dtoList != null) {
                    String maxDate = creDate;

                    for (int i = 0; i < dtoList.size(); i++) {
                        AttendeeDto attendeeDto = dtoList.get(i);
                        attendeeDto.setAttendeesId(null);
                        SpeakerDto speakerDto =SingletonDAO.getSpeakerDAOInstace(context).getSpeaker(attendeeDto.getSpeakerDto());
                        if(speakerDto!=null)
                            attendeeDto.getSpeakerDto().setSpeakerId(speakerDto.getSpeakerId());
                        Log.d("attendee",attendeeDto.getSessionDto().getName());
                        SessionDto sessionDto = SingletonDAO.getSessionDAOInstace(context).getSessionByName(attendeeDto.getSessionDto().getName());
                        if(sessionDto!=null)
                            attendeeDto.getSessionDto().setSessionId(sessionDto.getSessionId());
                        attendeeDto.getDutyDto().setDutyId(SingletonDAO.getDutyDAOInstace(context).getDutyByName(attendeeDto.getDutyDto().getDutyName()).getDutyId());
                        if (attendeeDto.getCreDate().equals(attendeeDto.getModDate())) {
                            boolean tg = SingletonDAO.getAttendeeDAOInstace(context).addAttendee(attendeeDto);
                            if (attendeeDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = attendeeDto.getModDate();
                        } else {
                            AttendeeDto attendeeDto1 = SingletonDAO.getAttendeeDAOInstace(context).getAttendee(attendeeDto);
                            if(attendeeDto1!=null)
                                SingletonDAO.getAttendeeDAOInstace(context).updateAttendee(attendeeDto);
                            else
                                SingletonDAO.getAttendeeDAOInstace(context).addAttendee(attendeeDto);
                            if (attendeeDto.getModDate().compareTo(maxDate) > 0)
                                maxDate = attendeeDto.getModDate();
                        }
                    }
                    preferencesHelper.setCreDateAttendee(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }

        };
        return updateData(volley, context, creDate, "attendee");
    }

    @Override
    public boolean updateRoom(final Context context, List<RoomDto> allRoom) {
        String url = ApiEndPoint.ENDPOINT_SERVER_UPDATE_ROOM;
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        String json = JsonUltil.getJson(allRoom);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("room",response.toString());
                List<RoomDto> list = JsonUltil.converToArr(response.toString(),RoomDto[].class);
                Log.d("room",list.size()+"");
                for (RoomDto item:list) {
                    if(!SingletonDAO.getRoomDAOInstance(context).isExits(item.getRoomName())) SingletonDAO.getRoomDAOInstance(context).addRoom(item);
                    else SingletonDAO.getRoomDAOInstance(context).updateRoom(item);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferencesHelper.getToken();
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", "Bearer " + token);
                param.put("Content-Type", "application/json");
                return param;
            }
        };

        requestQueue.add(jsonArrayRequest);
        return true;
    }

    @SuppressLint("LongLogTag")
    @Override
    public int uploadFile(Context context, RecordDto recordDto, Map<String, String> params) {
        Log.d("params",params.get("record"));
        if (NetworkUtils.isNetworkConnected(context)) {
            String fileName;
            String token = preferencesHelper.getToken();
            String upLoadServerUri = ApiEndPoint.ENDPOINT_SERVER_UPLOAD;
            HttpURLConnection conn = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            int serverResponseCode = 0;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            String path = root + recordDto.getPath();
            File sourceFile = new File(path);
            if (!sourceFile.isFile()) {
                //Toast.makeText(context, "This is not exist!!!", Toast.LENGTH_SHORT).show();
                return 0;

            } else {
                try {
                    fileName = sourceFile.getName();
                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(upLoadServerUri);

                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("Authorization", "Bearer " + token);
                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    if (dos == null) Log.d("loi", "null");
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"upload_file\";filename=" + fileName + ";" + lineEnd);
                    dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }
                    // send multipart form data necesssary after file data...
                    for (String name : params.keySet()) {
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(params.get(name));
                    }
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();
                    Log.d("response1",serverResponseCode+" "+serverResponseMessage);
                    if (serverResponseCode == 200) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                    }
                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                }
                return serverResponseCode;
            }// End else block
        }
        return 0;
    }

    @Override
    public void createData(JSONObject jsonObject, Context context, final Callback volley, String url) {
        final int[] statusCode = {0};
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volley.getRespone(response, statusCode[0]);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferencesHelper.getToken();
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", "Bearer " + token);
                param.put("Content-Type", "application/json");
                return param;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                statusCode[0] = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void importData(JSONArray jsonArray, Context context, final Callback volley, String url) {
        final int[] statusCode = {0};
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                volley.getRespone(response, statusCode[0]);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferencesHelper.getToken();
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", "Bearer " + token);
                param.put("Content-Type", "application/json");
                return param;
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                statusCode[0] = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(jsonRequest);
    }


    @Override
    public void creMeeting(MeetingDto meetingDto, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_CREATE_MEETING;
        try {
            createData(new JSONObject(JsonUltil.getJson(meetingDto)), context, volley, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importMeetingService(JSONArray jsonArray, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_IMPORT_MEETING;
        importData(jsonArray, context, volley, url);
    }

    @Override
    public void creSession(SessionDto sessionDto, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_CREATE_SESSION;
        try {
            createData(new JSONObject(JsonUltil.getJson(sessionDto)), context, volley, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importSessionService(JSONArray jsonArray, Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_IMPORT_SESSION;
        importData(jsonArray, context, volley, url);
    }

    @Override
    public void creSpeaker(SpeakerDto speakerDto, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_CREATE_SPEAKER;
        try {
            createData(new JSONObject(JsonUltil.getJson(speakerDto)), context, volley, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importSpeakerService(JSONArray jsonArray, Context context, Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_IMPORT_SPEAKER;
        importData(jsonArray, context, volley, url);
    }

    @Override
    public void creAttendee(AttendeeDto attendeeDto, Context context, Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_CREATE_ATTENDEE;
        try {
            createData(new JSONObject(JsonUltil.getJson(attendeeDto)), context, volley, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importAttendeeService(JSONArray jsonArray, Context context, Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_IMPORT_ATTENDEE;
        importData(jsonArray, context, volley, url);
    }
}

