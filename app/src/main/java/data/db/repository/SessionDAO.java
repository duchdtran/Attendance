package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.MeetingDto;
import data.model.RoomDto;
import data.model.SessionDto;
import ultils.SingletonDAO;

public class SessionDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    public SessionDAO(Context context){
        mDBHelper = new DBHelper(context);
        this.mcontext = context;
        try{
            open();
        }
        catch (SQLException e){
            Log.e("SQL",e.getMessage());
            e.printStackTrace();
        }

    }
    public void open(){
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close(){
        mDBHelper.close();
    }
    public  boolean addSession(SessionDto sessionDto){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_MEETING_ID, sessionDto.getMeetingDto().getMeetingId());
            contentValues.put(DBHelper.COLUMN_SESSION_NAME, sessionDto.getName());
            contentValues.put(DBHelper.COLUMN_ROOM_ID, sessionDto.getRoomDto().getRoomId());
            contentValues.put(DBHelper.COLUMN_TIME_START, sessionDto.getTimeStart());
            contentValues.put(DBHelper.COLUMN_TIME_END, sessionDto.getTimeEnd());
            contentValues.put(DBHelper.COLUMN_DESCRIPTION, sessionDto.getDescription());
            contentValues.put(DBHelper.COLUMN_STATUS, sessionDto.getStatus());
            contentValues.put(DBHelper.COLUMN_CRE_UID, sessionDto.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, sessionDto.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, sessionDto.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE, sessionDto.getModDate());
            mDatabase.insert(DBHelper.TABLE_SESSION,null,contentValues);
            return  true;
        }catch (Exception e){

            e.printStackTrace();
            return  false;
        }

    }
    public ArrayList<SessionDto> getAllItems(){
        ArrayList<SessionDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SESSION +" where "+DBHelper.COLUMN_STATUS+" !=-1",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int sessionId = cursor.getInt(0);
                int meetingId = cursor.getInt(1);
                String name = cursor.getString(2);
                int roomId = cursor.getInt(3);
                String timeStart =cursor.getString(4);
                String timeEnd =cursor.getString(5);
                String des =cursor.getString(6);
                int stt =cursor.getInt(7);
                int creUID =cursor.getInt(8);
                String creDate =cursor.getString(9);
                int modUID =cursor.getInt(10);
                String modDate =cursor.getString(11);
                SessionDto m = new SessionDto();
                MeetingDAO meetingDAO = SingletonDAO.getMeetingDAOInstance(mcontext);
                RoomDAO roomDAO = SingletonDAO.getRoomDAOInstance(mcontext);
                MeetingDto meetingDto = meetingDAO.getById(meetingId);
                RoomDto roomDto = roomDAO.getById(roomId);
                m.setMeetingDto(meetingDto);
                m.setMeetingDto(meetingDto);
                m.setName(name);
                m.setSessionId(sessionId);
                m.setRoomDto(roomDto);
                m.setTimeStart(timeStart);
                m.setTimeEnd(timeEnd);
                m.setDescription(des);
                m.setStatus(stt);
                m.setCreUID(creUID);
                m.setCreDate(creDate);
                m.setModUID(modUID);
                m.setModDate(modDate);

                arrayList.add(m);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
    public SessionDto getItemById(int id){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SESSION +" where Session_id="+id,null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.moveToFirst()){
            int sessionId = cursor.getInt(0);
            int meetingId = cursor.getInt(1);
            String name = cursor.getString(2);
            int roomId = cursor.getInt(3);
            String timeStart =cursor.getString(4);
            String timeEnd =cursor.getString(5);
            String des =cursor.getString(6);
            int stt =cursor.getInt(7);
            int creUID =cursor.getInt(8);
            String creDate =cursor.getString(9);
            int modUID =cursor.getInt(10);
            String modDate =cursor.getString(11);
            SessionDto m = new SessionDto();
            MeetingDAO meetingDAO = SingletonDAO.getMeetingDAOInstance(mcontext);
            RoomDAO roomDAO = SingletonDAO.getRoomDAOInstance(mcontext);
            MeetingDto meetingDto = meetingDAO.getById(meetingId);
            RoomDto roomDto = roomDAO.getById(roomId);
            m.setMeetingDto(meetingDto);
            m.setName(name);
            m.setSessionId(sessionId);
            m.setRoomDto(roomDto);
            m.setTimeStart(timeStart);
            m.setTimeEnd(timeEnd);
            m.setDescription(des);
            m.setStatus(stt);
            m.setCreUID(creUID);
            m.setCreDate(creDate);
            m.setModUID(modUID);
            m.setModDate(modDate);
            cursor.close();
            return  m;
        }
        else return null;
    }
    public ArrayList<SessionDto> getSessionByIdMeeting(int id){
        ArrayList<SessionDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SESSION+" where " +DBHelper.COLUMN_MEETING_ID +"="+id+" and "+DBHelper.COLUMN_STATUS+" !=-1",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int sessionId = cursor.getInt(0);
                int meetingId = cursor.getInt(1);
                String name = cursor.getString(2);
                int roomId = cursor.getInt(3);
                String timeStart =cursor.getString(4);
                String timeEnd =cursor.getString(5);
                String des =cursor.getString(6);
                int stt =cursor.getInt(7);
                int creUID =cursor.getInt(8);
                String creDate =cursor.getString(9);
                int modUID =cursor.getInt(10);
                String modDate =cursor.getString(11);
                SessionDto m = new SessionDto();
                MeetingDAO meetingDAO = SingletonDAO.getMeetingDAOInstance(mcontext);
                RoomDAO roomDAO = SingletonDAO.getRoomDAOInstance(mcontext);
                MeetingDto meetingDto = meetingDAO.getById(meetingId);
                RoomDto roomDto = roomDAO.getById(roomId);
                m.setMeetingDto(meetingDto);
                m.setMeetingDto(meetingDto);
                m.setName(name);
                m.setSessionId(sessionId);
                m.setRoomDto(roomDto);
                m.setTimeStart(timeStart);
                m.setTimeEnd(timeEnd);
                m.setDescription(des);
                m.setStatus(stt);
                m.setCreUID(creUID);
                m.setCreDate(creDate);
                m.setModUID(modUID);
                m.setModDate(modDate);

                arrayList.add(m);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
}

