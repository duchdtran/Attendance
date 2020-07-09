package ultils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import data.model.MeetingDto;
import data.model.RecordDto;
import data.model.SessionDto;

public class JsonUltil {
    public static List<RecordDto> converTolistRecord(String json){
        Gson gson=  new GsonBuilder().setDateFormat(AppConstants.TIMESTAMP_FORMAT).create();
        Type collectionType = new TypeToken<List<RecordDto>>(){}.getType();
        List<RecordDto> list = gson.fromJson(json, collectionType);
        return  list;
    }
    public static  List<MeetingDto> converToListMeeting(String json) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type collectionType = new TypeToken<List<MeetingDto>>() {
        }.getType();
        List<MeetingDto> list = gson.fromJson(json, collectionType);
        return list;
    }
    public static  List<SessionDto> converToListSession(String json) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type collectionType = new TypeToken<List<SessionDto>>() {
        }.getType();
        List<SessionDto> list = gson.fromJson(json, collectionType);
        return list;
    }
    public  static String getJson(Object o){
        return new Gson().toJson(o);
    }
}