package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.app.SpeakerDto;


public class SpeakerDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;

    public SpeakerDAO(Context context) {
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

    public boolean addSpeaker(SpeakerDto speakerDto) {
        if (!isExits(speakerDto)){
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COLUMN_SPEAKER_FULL_NAME,speakerDto.getFullName());
                contentValues.put(DBHelper.COLUMN_SPEAKER_OTHER_NAME, speakerDto.getOtherName());
                contentValues.put(DBHelper.COLUMN_SPEAKER_GENDER,speakerDto.getGender());
                contentValues.put(DBHelper.COLUMN_SPEAKER_EMAIL, speakerDto.getEmail());
                contentValues.put(DBHelper.COLUMN_SPEAKER_PHONE, speakerDto.getPhone());
                contentValues.put(DBHelper.COLUMN_SPEAKER_REGENCY, speakerDto.getRegency());
                contentValues.put(DBHelper.COLUMN_SPEAKER_BIRTHDAY,speakerDto.getBirthday());
                contentValues.put(DBHelper.COLUMN_STATUS, speakerDto.getStatus());
                contentValues.put(DBHelper.COLUMN_CRE_UID, speakerDto.getCreUID());
                contentValues.put(DBHelper.COLUMN_CRE_DATE, speakerDto.getCreDate());
                contentValues.put(DBHelper.COLUMN_MOD_UID, speakerDto.getModUID());
                contentValues.put(DBHelper.COLUMN_MOD_DATE, speakerDto.getModDate());
                mDatabase.insert(DBHelper.TABLE_SPEAKER, null, contentValues);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ArrayList<SpeakerDto> getAllItems() {
        ArrayList<SpeakerDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SPEAKER + " where " + DBHelper.COLUMN_STATUS + " !=-1", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Integer speakerId=cursor.getInt(0);
                String fullName=cursor.getString(1);
                String otherName=cursor.getString(2);
                Integer gender=cursor.getInt(3);
                String email=cursor.getString(4);
                String phone=cursor.getString(5);
                String birthday=cursor.getString(6);
                String regency=cursor.getString(7);
                Integer status=cursor.getInt(8);
                Integer creUID=cursor.getInt(9);
                String creDate=cursor.getString(10);
                Integer modUID=cursor.getInt(11);
                String modDate=cursor.getString(12);
                SpeakerDto speakerDto = new SpeakerDto(speakerId,fullName,otherName,gender,email,phone,birthday,regency,status,creUID,creDate,modUID,modDate);
                arrayList.add(speakerDto);
            }
            cursor.close();
            return arrayList;
        } else return arrayList;
    }

    public SpeakerDto getItemById(int id) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SPEAKER + " where "+DBHelper.COLUMN_SPEAKER_ID+ " =" + id, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            Integer speakerId=cursor.getInt(0);
            String fullName=cursor.getString(1);
            String otherName=cursor.getString(2);
            Integer gender=cursor.getInt(3);
            String email=cursor.getString(4);
            String phone=cursor.getString(5);
            String birthday=cursor.getString(6);
            String regency=cursor.getString(7);
            Integer status=cursor.getInt(8);
            Integer creUID=cursor.getInt(9);
            String creDate=cursor.getString(10);
            Integer modUID=cursor.getInt(11);
            String modDate=cursor.getString(12);
            SpeakerDto speakerDto = new SpeakerDto(speakerId,fullName,otherName,gender,email,phone,birthday,regency,status,creUID,creDate,modUID,modDate);
            cursor.close();
            return speakerDto;
        } else return null;
    }


    public void updateSpeaker(SpeakerDto speakerDto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_SPEAKER_FULL_NAME,speakerDto.getFullName());
        contentValues.put(DBHelper.COLUMN_SPEAKER_OTHER_NAME, speakerDto.getOtherName());
        contentValues.put(DBHelper.COLUMN_SPEAKER_GENDER,speakerDto.getGender());
        contentValues.put(DBHelper.COLUMN_SPEAKER_EMAIL, speakerDto.getEmail());
        contentValues.put(DBHelper.COLUMN_SPEAKER_PHONE, speakerDto.getPhone());
        contentValues.put(DBHelper.COLUMN_SPEAKER_REGENCY, speakerDto.getRegency());
        contentValues.put(DBHelper.COLUMN_SPEAKER_BIRTHDAY,speakerDto.getBirthday());
        contentValues.put(DBHelper.COLUMN_STATUS, speakerDto.getStatus());
        contentValues.put(DBHelper.COLUMN_CRE_UID, speakerDto.getCreUID());
        contentValues.put(DBHelper.COLUMN_CRE_DATE, speakerDto.getCreDate());
        contentValues.put(DBHelper.COLUMN_MOD_UID, speakerDto.getModUID());
        contentValues.put(DBHelper.COLUMN_MOD_DATE, speakerDto.getModDate());

        mDatabase.update(DBHelper.TABLE_SPEAKER, contentValues, DBHelper.COLUMN_SPEAKER_EMAIL+ " = ?", new String[] { String.valueOf(speakerDto.getEmail()) });
    }
    public boolean isExits(SpeakerDto speakerDto) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SPEAKER + " where " + DBHelper.COLUMN_SPEAKER_EMAIL + "='" + speakerDto.getEmail() + "' and " + DBHelper.COLUMN_SPEAKER_FULL_NAME+ "='" + speakerDto.getEmail() + "' and " + DBHelper.COLUMN_SPEAKER_REGENCY + "='" + speakerDto.getRegency() + "'", null);
        if (cursor != null&&cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        else return false;
    }
    public SpeakerDto getSpeaker(SpeakerDto speakerDto){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_SPEAKER + " where "+DBHelper.COLUMN_SPEAKER_FULL_NAME+ " ='" + speakerDto.getFullName()+"' and "+DBHelper.COLUMN_SPEAKER_EMAIL+ " ='" + speakerDto.getEmail()+"' and "+DBHelper.COLUMN_SPEAKER_BIRTHDAY+ " ='" + speakerDto.getBirthday()+"'", null);
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            Integer speakerId=cursor.getInt(0);
            String fullName=cursor.getString(1);
            String otherName=cursor.getString(2);
            Integer gender=cursor.getInt(3);
            String email=cursor.getString(4);
            String phone=cursor.getString(5);
            String birthday=cursor.getString(6);
            String regency=cursor.getString(7);
            Integer status=cursor.getInt(8);
            Integer creUID=cursor.getInt(9);
            String creDate=cursor.getString(10);
            Integer modUID=cursor.getInt(11);
            String modDate=cursor.getString(12);
            SpeakerDto speakerDto1 = new SpeakerDto(speakerId,fullName,otherName,gender,email,phone,birthday,regency,status,creUID,creDate,modUID,modDate);
            cursor.close();
            return speakerDto1;
        } else return null;
    }
}
