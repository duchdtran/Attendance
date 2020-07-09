package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.RoomDto;

public class RoomDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    public RoomDAO(Context context){
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

    public  boolean addRoom(RoomDto roomDto){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_ROOM_NAME, roomDto.getRoomName());
            contentValues.put(DBHelper.COLUMN_DESCRIPTION, roomDto.getRoomDescription());
            contentValues.put(DBHelper.COLUMN_STATUS, roomDto.getStatus());

            mDatabase.insert(DBHelper.TABLE_ROOM,null,contentValues);
            return  true;
        }catch (Exception e){

            e.printStackTrace();
            return  false;
        }

    }
    public ArrayList<RoomDto> getAllItems(){
        ArrayList<RoomDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ROOM,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int roomId = cursor.getInt(0);
                String name = cursor.getString(1);
                String des = cursor.getString(2);
                int stt =cursor.getInt(3);
                RoomDto m = new RoomDto();
                m.setRoomId(roomId);
                m.setRoomName(name);
                m.setRoomDescription(des);
                m.setStatus(stt);
                arrayList.add(m);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
    public RoomDto getById(int id){

        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ROOM +" where "+ DBHelper.COLUMN_ROOM_ID+"="+id,null);
        if(cursor!=null){
            cursor.moveToFirst();
            int roomId = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int stt =cursor.getInt(3);
            RoomDto m = new RoomDto();
            m.setRoomId(roomId);
            m.setRoomName(name);
            m.setRoomDescription(des);
            m.setStatus(stt);

            cursor.close();
            return  m;


        }
        else return null;
    }
    public ArrayList<String> getAllNameRooms(){
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ROOM,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                arrayList.add(name);
            }
            return  arrayList;
        }
        else return null;
    }
    public int getIdByName(String name){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ROOM+" where " +DBHelper.COLUMN_ROOM_NAME +"='"+name+"'",null);

        if(cursor!=null){
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        else return -1;
    }
}
