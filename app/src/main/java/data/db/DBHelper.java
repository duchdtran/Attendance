package data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ultils.AppConstants;

public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String COMA_SEP = ",";
    //column of meeting table
    public static final String TABLE_MEETING = "meeting";
    public static final String COLUMN_MEETING_ID = "Meeting_id";
    public static final String COLUMN_NAME_MEETING = "Name";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_TIME_START= "Time_start";
    public static final String COLUMN_TIME_END= "Time_end";
    public static final String COLUMN_DESCRIPTION= "Description";
    public static final String COLUMN_STATUS= "Status";
    public static final String COLUMN_CRE_UID= "Cre_UID";
    public static final String COLUMN_CRE_DATE= "Cre_Date";
    public static final String COLUMN_MOD_UID= "Mod_UID";
    public static final String COLUMN_MOD_DATE= "Mod_Date";
    //column of recored table
    public static final String TABLE_RECORD = "record";
    public static final String COLUMN_RECORD_ID = "Record_id";
    public static final String COLUMN_NAME_RECORD = "Name";
    public static final String COLUMN_PATH = "Path";
    public static final String COLUMN_LENGT = "Lenght";
    public static final String COLUMN_PROCESSING_INFOR = "Processing_Infor";
    public static final String COLUMN_STATUS_APP= "Status_in_app";
    //column of session table
    public static final String TABLE_SESSION = "session";
    public static final String COLUMN_SESSION_NAME= "Name";
    public static final String COLUMN_SESSION_ID= "Session_id";
    public static final String COLUMN_ROOM_ID = "Room_ID";
    //comlumn of room table
    public static final String TABLE_ROOM="room";
    public static final String COLUMN_ROOM_NAME="Room_Name";
    //comlumn of request table
    public static final String TABLE_REQUEST="request";
    public static final String COLUMN_REQUEST_ID="Request_Id";
    //create table meeting
    public static final String SQLITE_CREATE_TABLE_MEETING = "CREATE TABLE " + TABLE_MEETING + " (Meeting_id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT" + COMA_SEP +
            COLUMN_NAME_MEETING + " TEXT" + COMA_SEP +
            COLUMN_ADDRESS + " TEXT" + COMA_SEP +
            COLUMN_TIME_START + " TEXT"+ COMA_SEP+
            COLUMN_TIME_END + " TEXT"+ COMA_SEP+
            COLUMN_DESCRIPTION + " TEXT"+ COMA_SEP+
            COLUMN_STATUS + " INTEGER"+ COMA_SEP+
            COLUMN_CRE_UID + " INTEGER"+ COMA_SEP+
            COLUMN_CRE_DATE + " TEXT"+ COMA_SEP+
            COLUMN_MOD_UID + " INTEGER"+ COMA_SEP+
            COLUMN_MOD_DATE + " TEXT )";
    //create table session
    public static final String SQLITE_CREATE_TABLE_SESSION= "CREATE TABLE " + TABLE_SESSION + " ("+COLUMN_SESSION_ID +" INTEGER PRIMARY KEY " +
            "AUTOINCREMENT" + COMA_SEP +
            COLUMN_MEETING_ID + " INTEGER" + COMA_SEP +
            COLUMN_SESSION_NAME + " TEXT" + COMA_SEP +
            COLUMN_ROOM_ID + " INTEGER" + COMA_SEP +
            COLUMN_TIME_START + " TEXT"+ COMA_SEP+
            COLUMN_TIME_END + " TEXT"+ COMA_SEP+
            COLUMN_DESCRIPTION + " TEXT"+ COMA_SEP+
            COLUMN_STATUS + " INTEGER"+ COMA_SEP+
            COLUMN_CRE_UID + " INTEGER"+ COMA_SEP+
            COLUMN_CRE_DATE + " TEXT"+ COMA_SEP+
            COLUMN_MOD_UID + " INTEGER"+ COMA_SEP+
            COLUMN_MOD_DATE + " TEXT "+
            ")";
    //create table record
    public static final String SQLITE_CREATE_TABLE_RECORD = "CREATE TABLE " + TABLE_RECORD + " ("+ COLUMN_RECORD_ID +" INTEGER PRIMARY KEY " +
            "AUTOINCREMENT" + COMA_SEP +
            COLUMN_SESSION_ID + " INTEGER" + COMA_SEP +
            COLUMN_NAME_RECORD + " TEXT" + COMA_SEP +
            COLUMN_LENGT + " REAL" + COMA_SEP +
            COLUMN_PATH + " TEXT" + COMA_SEP +
            COLUMN_STATUS + " TEXT"+ COMA_SEP+
            COLUMN_STATUS_APP + " TEXT"+ COMA_SEP+
            COLUMN_PROCESSING_INFOR + " TEXT"+ COMA_SEP+
            COLUMN_CRE_UID + " INTEGER"+ COMA_SEP+
            COLUMN_CRE_DATE + " TEXT"+ COMA_SEP+
            COLUMN_MOD_UID + " INTEGER"+ COMA_SEP+
            COLUMN_MOD_DATE + " TEXT "+
            ")";
    public static final String SQLITE_CREATE_TABLE_ROOM = "CREATE TABLE " + TABLE_ROOM+ " ("+ COLUMN_ROOM_ID +" INTEGER PRIMARY KEY " +
            "AUTOINCREMENT" + COMA_SEP +
            COLUMN_ROOM_NAME + " TEXT" + COMA_SEP +
            COLUMN_DESCRIPTION + " TEXT"+ COMA_SEP+
            COLUMN_STATUS + " INTEGER"+
            ")";
    //create table request
    public static final String SQLITE_CREATE_TABLE_REQUEST = "CREATE TABLE " + TABLE_REQUEST+ " ("+ COLUMN_REQUEST_ID +" INTEGER PRIMARY KEY " +
            "AUTOINCREMENT" + COMA_SEP +
            COLUMN_RECORD_ID + " INTEGER" +
            ")";

    public DBHelper(Context context) {
        super(context, AppConstants.DB_NAME, null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLITE_CREATE_TABLE_MEETING);
        db.execSQL(SQLITE_CREATE_TABLE_RECORD);
        db.execSQL(SQLITE_CREATE_TABLE_SESSION);
        db.execSQL(SQLITE_CREATE_TABLE_ROOM);
        db.execSQL(SQLITE_CREATE_TABLE_REQUEST);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUEST);
        onCreate(db);
    }
}

