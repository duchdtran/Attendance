package data.network;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

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

import data.model.MeetingDto;
import data.model.RecordDto;
import data.model.SessionDto;
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
        Log.d("AAA", ApiEndPoint.ENDPOINT_SERVER_LOGIN);
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
    public void updateData(final Callback volley, final Context context, String creDate, String s) {
        String url = "";
        if (s.equals("record")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATERECORD;
        else if (s.equals("meeting")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATEMEETING;
        else if (s.equals("session")) url = ApiEndPoint.ENDPOINT_SERVER_UPDATESESSION;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<RecordDto> dtoList = JsonUltil.converTolistRecord(String.valueOf(response));
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        RecordDto recordDto = dtoList.get(i);
                        if (recordDto.getCreDate().compareTo(maxDate) > 0)
                            maxDate = recordDto.getCreDate();
                        recordDto.setRecordId(null);
                        recordDto.setStatusInApp("Đã upload");
                        SingletonDAO.getRecordDAOInstance(context).addRecording(recordDto);
                    }
                    preferencesHelper.setCreDateRecord(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }
        };
        updateData(volley, context, creDate, "record");
    }

    @Override
    public void updateMeeting(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<MeetingDto> dtoList = JsonUltil.converToListMeeting(String.valueOf(response));
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        MeetingDto meetingDto = dtoList.get(i);
                        if (meetingDto.getCreDate().compareTo(maxDate) > 0)
                            maxDate = meetingDto.getCreDate();
                        meetingDto.setMeetingId(null);
                        SingletonDAO.getMeetingDAOInstance(context).addMeeting(meetingDto);
                    }
                    preferencesHelper.setCreDateMeeting(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }
        };
        updateData(volley, context, creDate, "meeting");
    }

    @Override
    public void updateSession(final Context context, final String creDate) {
        Callback volley = new Callback() {
            @Override
            public void getRespone(Object response, int stt) {
                List<SessionDto> dtoList = JsonUltil.converToListSession(String.valueOf(response));
                if (dtoList != null) {
                    String maxDate = creDate;
                    for (int i = 0; i < dtoList.size(); i++) {
                        SessionDto sessionDto = dtoList.get(i);
                        if (sessionDto.getCreDate().compareTo(maxDate) > 0)
                            maxDate = sessionDto.getCreDate();
                        boolean a = SingletonDAO.getSessionDAOInstace(context).addSession(sessionDto);
                    }
                    preferencesHelper.setCreDateSession(maxDate);
                }
            }

            @Override
            public void getError(Object error, int stt) {

            }

        };
        updateData(volley, context, creDate, "session");
    }

    @SuppressLint("LongLogTag")
    @Override
    public int uploadFile(Context context, RecordDto recordDto, Map<String, String> params) {
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
                    Log.d("uploadFile",
                            serverResponseMessage + ": " + serverResponseCode);
                    if (serverResponseCode == 200) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        Log.d("upload", response.toString());
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
    public void creMeeting(MeetingDto meetingDto, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_CREATEMEETING;
        final int[] statusCode = {0};
        final RequestQueue requestQueue = SingletonVolley.getInstance(context).getRequestQueue();
        String jsonMeeting = new Gson().toJson(meetingDto);
        JSONObject json = null;
        try {
            json = new JSONObject(jsonMeeting);
            Log.d("json", json.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
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
                    SharedPreferences sharedPreferences = context.getSharedPreferences("session_user", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", " ");
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importMeetingService(JSONArray jsonArray, final Context context, final Callback volley) {
        String url = ApiEndPoint.ENDPOINT_SERVER_IMPORT_MEETING;
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
                Log.d("AAA", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("session_user", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", " ");
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
}

