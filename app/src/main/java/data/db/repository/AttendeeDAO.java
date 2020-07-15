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
import data.model.app.AttendeeDto;
import data.model.app.SessionDto;
import ultils.SingletonDAO;


public class AttendeeDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;

    public AttendeeDAO(Context context) {
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

    public boolean addAttendee(AttendeeDto attendeeDto) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_SESSION_ID, attendeeDto.getSessionDto().getSessionId());
            contentValues.put(DBHelper.COLUMN_SPEAKER_ID, attendeeDto.getSpeakerDto().getSpeakerId());
            contentValues.put(DBHelper.DUTY_ID, attendeeDto.getDutyDto().getDutyId());
            contentValues.put(DBHelper.COLUMN_STATUS, attendeeDto.getStatus());
            contentValues.put(DBHelper.COLUMN_CRE_UID, attendeeDto.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, attendeeDto.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, attendeeDto.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE, attendeeDto.getModDate());
            mDatabase.insert(DBHelper.TABLE_ATTENDEE, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AttendeeDto> getAll() {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ATTENDEE, null);
        List<AttendeeDto> attendeeDtos = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int attendeeId = cursor.getInt(0);
                int sessionId = cursor.getInt(1);
                int speakerId = cursor.getInt(2);
                int dutyId = cursor.getInt(3);
                int status = cursor.getInt(4);
                int creUID = cursor.getInt(5);
                String creDate = cursor.getString(6);
                int modUID = cursor.getInt(7);
                String modDate = cursor.getString(8);
                AttendeeDto attendeeDto = new AttendeeDto();
                attendeeDto.setAttendeesId(attendeeId);
                attendeeDto.setSessionDto(SingletonDAO.getSessionDAOInstace(mcontext).getItemById(sessionId));
                attendeeDto.setSpeakerDto(SingletonDAO.getSpeakerDAOInstace(mcontext).getItemById(speakerId));
                attendeeDto.setDutyDto(SingletonDAO.getDutyDAOInstace(mcontext).getItemById(dutyId));
                attendeeDto.setStatus(status);
                attendeeDto.setCreUID(creUID);
                attendeeDto.setCreDate(creDate);
                attendeeDto.setModUID(modUID);
                attendeeDto.setModDate(modDate);
                attendeeDtos.add(attendeeDto);
            }
            cursor.close();
            return attendeeDtos;
        }
        return attendeeDtos;
    }
    public AttendeeDto getAttendee(AttendeeDto attendeeDto) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ATTENDEE+" where "+DBHelper.COLUMN_SESSION_ID+" ="+attendeeDto.getSessionDto().getSessionId()+" and "+ DBHelper.COLUMN_SPEAKER_ID+" ="+attendeeDto.getSpeakerDto().getSpeakerId(), null);
        AttendeeDto attendeeDto1 = new AttendeeDto();
        if (cursor != null&&cursor.moveToFirst()) {
                int attendeeId = cursor.getInt(0);
                int sessionId = cursor.getInt(1);
                int speakerId = cursor.getInt(2);
                int dutyId = cursor.getInt(3);
                int status = cursor.getInt(4);
                int creUID = cursor.getInt(5);
                String creDate = cursor.getString(6);
                int modUID = cursor.getInt(7);
                String modDate = cursor.getString(8);

                attendeeDto1.setAttendeesId(attendeeId);
                attendeeDto1.setSessionDto(SingletonDAO.getSessionDAOInstace(mcontext).getItemById(sessionId));
                attendeeDto1.setSpeakerDto(SingletonDAO.getSpeakerDAOInstace(mcontext).getItemById(speakerId));
                attendeeDto1.setDutyDto(SingletonDAO.getDutyDAOInstace(mcontext).getItemById(dutyId));
                attendeeDto1.setStatus(status);
                attendeeDto1.setCreUID(creUID);
                attendeeDto1.setCreDate(creDate);
                attendeeDto1.setModUID(modUID);
                attendeeDto1.setModDate(modDate);
               cursor.close();
            return attendeeDto1;
        }
        return attendeeDto1;
    }
    public List<AttendeeDto> getBySession(SessionDto sessionDto) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ATTENDEE + " where " + DBHelper.COLUMN_SESSION_ID + "=" + sessionDto.getSessionId(), null);
        List<AttendeeDto> attendeeDtos = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()){
                int attendeeId = cursor.getInt(0);
                int sessionId = cursor.getInt(1);
                int speakerId = cursor.getInt(2);
                int dutyId = cursor.getInt(3);
                int status = cursor.getInt(4);
                int creUID = cursor.getInt(5);
                String creDate = cursor.getString(6);
                int modUID = cursor.getInt(7);
                String modDate = cursor.getString(8);
                AttendeeDto attendeeDto = new AttendeeDto();
                attendeeDto.setAttendeesId(attendeeId);
                attendeeDto.setSessionDto(SingletonDAO.getSessionDAOInstace(mcontext).getItemById(sessionId));
                attendeeDto.setSpeakerDto(SingletonDAO.getSpeakerDAOInstace(mcontext).getItemById(speakerId));
                attendeeDto.setDutyDto(SingletonDAO.getDutyDAOInstace(mcontext).getItemById(dutyId));
                attendeeDto.setStatus(status);
                attendeeDto.setCreUID(creUID);
                attendeeDto.setCreDate(creDate);
                attendeeDto.setModUID(modUID);
                attendeeDto.setModDate(modDate);
                attendeeDtos.add(attendeeDto);
            }
            cursor.close();
            return attendeeDtos;
        } else return attendeeDtos;
    }

    public boolean isExits(AttendeeDto attendeeDto) {
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_ATTENDEE + " where " + DBHelper.COLUMN_SESSION_ID + "=" + attendeeDto.getSessionDto().getSessionId() + " and " + DBHelper.COLUMN_SPEAKER_ID + "=" + attendeeDto.getSpeakerDto().getSpeakerId(), null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else return false;
    }

    public void updateAttendee(AttendeeDto attendeeDto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_SESSION_ID, attendeeDto.getSessionDto().getSessionId());
        contentValues.put(DBHelper.COLUMN_SPEAKER_ID, attendeeDto.getSpeakerDto().getSpeakerId());
        contentValues.put(DBHelper.COLUMN_SPEAKER_ID, attendeeDto.getDutyDto().getDutyId());
        contentValues.put(DBHelper.COLUMN_STATUS, attendeeDto.getStatus());
        contentValues.put(DBHelper.COLUMN_CRE_UID, attendeeDto.getCreUID());
        contentValues.put(DBHelper.COLUMN_CRE_DATE, attendeeDto.getCreDate());
        contentValues.put(DBHelper.COLUMN_MOD_UID, attendeeDto.getModUID());
        contentValues.put(DBHelper.COLUMN_MOD_DATE, attendeeDto.getModDate());

        mDatabase.update(DBHelper.TABLE_ATTENDEE, contentValues, DBHelper.COLUMN_SESSION_ID + " = ? and " + DBHelper.COLUMN_SPEAKER_ID + " = ?", new String[]{String.valueOf(attendeeDto.getSessionDto().getSessionId()), String.valueOf(attendeeDto.getSpeakerDto().getSpeakerId())});
    }

}
