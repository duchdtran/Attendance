package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.RecordDto;
import data.model.SessionDto;
import ultils.SingletonDAO;


public class RecordDAO {
    private  Context mcontext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    public RecordDAO( Context context){
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
        mDatabase.enableWriteAheadLogging();
    }
    public void close(){
        mDBHelper.close();
    }
    public  boolean addRecording(RecordDto recordDto){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_NAME_RECORD, recordDto.getName());
            contentValues.put(DBHelper.COLUMN_SESSION_ID, recordDto.getSessionDto().getSessionId());
            contentValues.put(DBHelper.COLUMN_LENGT, recordDto.getLength());
            contentValues.put(DBHelper.COLUMN_PATH, recordDto.getPath());
            contentValues.put(DBHelper.COLUMN_STATUS, recordDto.getStatus());
            contentValues.put(DBHelper.COLUMN_STATUS_APP, recordDto.getStatusInApp());
            contentValues.put(DBHelper.COLUMN_PROCESSING_INFOR, recordDto.getProcessingInfo());
            contentValues.put(DBHelper.COLUMN_CRE_UID, recordDto.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, recordDto.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, recordDto.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE, recordDto.getModDate());
            Log.d("content",contentValues.getAsString(DBHelper.COLUMN_SESSION_ID));
            mDatabase.insert(DBHelper.TABLE_RECORD,null,contentValues);
            Log.d("luu thanh con","true");
            return  true;
        }catch (Exception e){
            Log.d("luu thanh con","false");
            e.printStackTrace();
            return  false;
        }

    }
    public ArrayList<RecordDto> getAllItems(){
        ArrayList<RecordDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_RECORD + " ORDER BY "+ DBHelper.COLUMN_MOD_DATE+ " DESC",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int recordId = cursor.getInt(0);
                int sessionId = cursor.getInt(1);
                String name = cursor.getString(2);
                float lengt = cursor.getFloat(3);
                String path =cursor.getString(4);
                String stt =cursor.getString(5);
                String sttApp =cursor.getString(6);
                String process =cursor.getString(7);
                int creUID =cursor.getInt(8);
                String creDate =cursor.getString(9);
                int modUID =cursor.getInt(10);
                String modDate =cursor.getString(11);
                SessionDAO sessionDAO = SingletonDAO.getSessionDAOInstace(mcontext);
                SessionDto sessionDto = sessionDAO.getItemById(sessionId);
                RecordDto recordDto = new RecordDto(recordId, sessionDto,name,lengt,path,stt,sttApp,process,creUID,creDate,modUID,modDate);
                arrayList.add(recordDto);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
    public RecordDto getItemById(int id){
        Cursor cursor =null;
                cursor= mDatabase.rawQuery("select * from " + DBHelper.TABLE_RECORD +" where "+ DBHelper.COLUMN_RECORD_ID+"="+id,null);
        if(cursor!=null&&cursor.moveToFirst()){
            int recordId = cursor.getInt(0);
            int sessionId = cursor.getInt(1);
            String name = cursor.getString(2);
            float lengt = cursor.getFloat(3);
            String path =cursor.getString(4);
            String stt =cursor.getString(5);
            String sttApp =cursor.getString(6);
            String process =cursor.getString(7);
            int creUID =cursor.getInt(8);
            String creDate =cursor.getString(9);
            int modUID =cursor.getInt(10);
            String modDate =cursor.getString(11);
            SessionDAO sessionDAO = SingletonDAO.getSessionDAOInstace(mcontext);
            SessionDto sessionDto = sessionDAO.getItemById(sessionId);
            RecordDto recordDto = new RecordDto(recordId, sessionDto,name,lengt,path,stt,sttApp,process,creUID,creDate,modUID,modDate);
            cursor.close();
            return recordDto;
            }
        else return null;
    }
    public RecordDto getItemByPath(String path){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_RECORD +" where "+ DBHelper.COLUMN_PATH+ "='"+path+"'",null);
        cursor.moveToFirst();
        if(cursor!=null){
            int recordId = cursor.getInt(0);
            int sessionId = cursor.getInt(1);
            String name = cursor.getString(2);
            float lengt = cursor.getFloat(3);
            String path1 =cursor.getString(4);
            String stt =cursor.getString(5);
            String sttApp =cursor.getString(6);
            String process =cursor.getString(7);
            int creUID =cursor.getInt(8);
            String creDate =cursor.getString(9);
            int modUID =cursor.getInt(10);
            String modDate =cursor.getString(11);
            SessionDAO sessionDAO = SingletonDAO.getSessionDAOInstace(mcontext);
            SessionDto sessionDto = sessionDAO.getItemById(sessionId);
            RecordDto recordDto = new RecordDto(recordId, sessionDto,name,lengt,path,stt,sttApp,process,creUID,creDate,modUID,modDate);
            cursor.close();
            return recordDto;
        }
        else return null;
    }
    public int getMaxID(){
        Cursor cursor = mDatabase.rawQuery("select MAX("+ DBHelper.COLUMN_RECORD_ID +") from " + DBHelper.TABLE_RECORD,null);
        int count=0;
        if(cursor!=null){
            cursor.moveToLast();
           count=cursor.getInt(0);
        }
        cursor.close();
       return  count;
    }
    public void updateRecord(RecordDto recordDto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME_RECORD, recordDto.getName());
        contentValues.put(DBHelper.COLUMN_SESSION_ID, recordDto.getSessionDto().getSessionId());
        contentValues.put(DBHelper.COLUMN_LENGT, recordDto.getLength());
        contentValues.put(DBHelper.COLUMN_PATH, recordDto.getPath());
        contentValues.put(DBHelper.COLUMN_STATUS, recordDto.getStatus());
        contentValues.put(DBHelper.COLUMN_STATUS_APP, recordDto.getStatusInApp());
        contentValues.put(DBHelper.COLUMN_PROCESSING_INFOR, recordDto.getProcessingInfo());
        contentValues.put(DBHelper.COLUMN_CRE_UID, recordDto.getCreUID());
        contentValues.put(DBHelper.COLUMN_CRE_DATE, recordDto.getCreDate());
        contentValues.put(DBHelper.COLUMN_MOD_UID, recordDto.getModUID());
        contentValues.put(DBHelper.COLUMN_MOD_DATE, recordDto.getModDate());

       mDatabase.update(DBHelper.TABLE_RECORD, contentValues, DBHelper.COLUMN_RECORD_ID+ " = ?", new String[] { String.valueOf(recordDto.getRecordId()) });
    }
    public int getIdByPath(String path){
        Cursor cursor = mDatabase.rawQuery("select "+ DBHelper.COLUMN_RECORD_ID +" from " + DBHelper.TABLE_RECORD +" where "+ DBHelper.COLUMN_PATH+"='"+path+"'",null);
        int count=0;
        if(cursor!=null&&cursor.moveToFirst()){
            count=cursor.getInt(0);
        }
        cursor.close();
        return  count;
    }
}
