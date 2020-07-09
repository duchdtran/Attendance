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

public class MeetingDAO  {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] mAllColums={DBHelper.COLUMN_NAME_RECORD,DBHelper.COLUMN_ADDRESS,DBHelper.COLUMN_TIME_START,DBHelper.COLUMN_TIME_END,DBHelper.COLUMN_DESCRIPTION,
            DBHelper.COLUMN_STATUS,DBHelper.COLUMN_CRE_UID,DBHelper.COLUMN_CRE_DATE,DBHelper.COLUMN_MOD_UID,DBHelper.COLUMN_MOD_DATE};
    public MeetingDAO(Context context){
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

    public  boolean addMeeting(MeetingDto m){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_NAME_MEETING, m.getName());
            contentValues.put(DBHelper.COLUMN_ADDRESS,m.getAddress());
            contentValues.put(DBHelper.COLUMN_TIME_START, m.getTimeStart());
            contentValues.put(DBHelper.COLUMN_TIME_END, m.getTimeEnd());
            contentValues.put(DBHelper.COLUMN_DESCRIPTION, m.getDescription());
            contentValues.put(DBHelper.COLUMN_STATUS, m.getStatus());
            contentValues.put(DBHelper.COLUMN_CRE_UID, m.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, m.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, m.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE,m.getModDate());
            mDatabase.insert(DBHelper.TABLE_MEETING,null,contentValues);
            return  true;
        }catch (Exception e){

            e.printStackTrace();
            return  false;
        }

    }
    public ArrayList<MeetingDto> getAllItems(){
        ArrayList<MeetingDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_MEETING,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int meetingId = cursor.getInt(0);
                String name = cursor.getString(1);
                String addr = cursor.getString(2);
                String timeStart =cursor.getString(3);
                String timeEnd =cursor.getString(4);
                String des =cursor.getString(5);
                int stt =cursor.getInt(6);
                int creUID =cursor.getInt(7);
                String creDate =cursor.getString(8);
                int modUID =cursor.getInt(9);
                String modDate =cursor.getString(10);
                MeetingDto m = new MeetingDto();
                m.setMeetingId(meetingId);
                m.setName(name);
                m.setAddress(addr);
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
    public MeetingDto getById(int id){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_MEETING+" where "+ DBHelper.COLUMN_MEETING_ID +"="+id,null);
        if(cursor!=null&&cursor.moveToFirst()){
            int meetingId = cursor.getInt(0);
            String name = cursor.getString(1);
            String addr = cursor.getString(2);
            String timeStart =cursor.getString(3);
            String timeEnd =cursor.getString(4);
            String des =cursor.getString(5);
            int stt =cursor.getInt(6);
            int creUID =cursor.getInt(7);
            String creDate =cursor.getString(8);
            int modUID =cursor.getInt(9);
            String modDate =cursor.getString(10);
            MeetingDto m = new MeetingDto();
            m.setMeetingId(meetingId);
            m.setName(name);
            m.setAddress(addr);
            m.setTimeStart(timeStart);
            m.setTimeEnd(timeEnd);
            m.setDescription(des);
            m.setStatus(stt);
            m.setCreUID(creUID);
            m.setCreDate(creDate);
            m.setModUID(modUID);
            m.setModDate(modDate);
            cursor.close();
            return m;
        }
        else return null;
    }
    public ArrayList<String> getAllNameMeetings(){
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_MEETING,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                arrayList.add(name);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
    public int getIdByName(String name){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_MEETING+" where " +DBHelper.COLUMN_NAME_MEETING +"='"+name+"'",null);

        if(cursor!=null){
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        else return -1;
    }
}
