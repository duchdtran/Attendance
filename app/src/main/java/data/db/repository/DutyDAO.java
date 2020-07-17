package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import data.db.DBHelper;
import data.model.app.DutyDto;

public class DutyDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;

    public DutyDAO(Context context) {
        mDBHelper = new DBHelper(context);
        this.mcontext = context;
        try {
            open();
        } catch (SQLException e) {
            Log.e("SQL", e.getMessage());
            e.printStackTrace();
        }

    }

    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        mDBHelper.close();
    }
    public boolean addDuty(DutyDto dutyDto) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.DUTY_NAME, dutyDto.getDutyName());
            contentValues.put(DBHelper.COLUMN_DESCRIPTION, dutyDto.getDutyDescription());
            mDatabase.insert(DBHelper.TABLE_DUTY, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<DutyDto> getAll() {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_DUTY , null);
        List<DutyDto> dutyDtos = new ArrayList<>();
        if (cursor != null ) {
            while (cursor.moveToNext()){
                int dutyID = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                DutyDto dutyDto = new DutyDto();
                dutyDto.setDutyId(dutyID);
                dutyDto.setDutyName(name);
                dutyDto.setDutyDescription(description);
                dutyDtos.add(dutyDto);
            }
            cursor.close();
            return dutyDtos;
        } else return dutyDtos;
    }
    public DutyDto getItemById(int id) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_DUTY + " where "+DBHelper.DUTY_ID+" =" + id, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            int dutyID = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            DutyDto dutyDto = new DutyDto();
            dutyDto.setDutyId(dutyID);
            dutyDto.setDutyName(name);
            dutyDto.setDutyDescription(description);
            cursor.close();
            return dutyDto;
        } else return null;
    }
    public DutyDto getDutyByName(String name){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_DUTY+" where " +DBHelper.DUTY_NAME +"='"+name+"'",null);

        if(cursor!=null&&cursor.moveToFirst()){
            int dutyID = cursor.getInt(0);
            String dutyName = cursor.getString(1);
            String description = cursor.getString(2);
            DutyDto dutyDto = new DutyDto(dutyID,dutyName,description);
            cursor.close();
            return dutyDto;
        }
        else return null;
    }
    public DutyDto getDutyByDescription(String name){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_DUTY+" where " +DBHelper.COLUMN_DESCRIPTION +"='"+name+"'",null);

        if(cursor!=null&&cursor.moveToFirst()){
            int dutyID = cursor.getInt(0);
            String dutyName = cursor.getString(1);
            String description = cursor.getString(2);
            DutyDto dutyDto = new DutyDto(dutyID,dutyName,description);
            cursor.close();
            return dutyDto;
        }
        else return null;
    }

}
