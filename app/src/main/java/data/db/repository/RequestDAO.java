package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.Request;
import ultils.SingletonDAO;


public class RequestDAO {
    private Context mcontext;

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    public RequestDAO(Context context){
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

    public  boolean addRequest(Request request){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_RECORD_ID, request.getRecord().getRecordId());
            mDatabase.insert(DBHelper.TABLE_REQUEST,null,contentValues);
            return  true;
        }catch (Exception e){

            e.printStackTrace();
            return  false;
        }

    }
    public ArrayList<Request> getAllItems(){
        ArrayList<Request> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_REQUEST,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Integer requestId = cursor.getInt(0);
                Integer recordId = cursor.getInt(1);
                Request m = new Request();
                m.setIdRequest(requestId);
                m.setRecord(SingletonDAO.getRecordDAOInstance(mcontext).getItemById(recordId));
                arrayList.add(m);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }
    public Request getById(int id){

        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_REQUEST +" where "+ DBHelper.COLUMN_REQUEST_ID+"="+id,null);
        if(cursor!=null && cursor.moveToFirst()){
            Integer requestId = cursor.getInt(0);
            Integer recordId = cursor.getInt(1);
            Request m = new Request();
            m.setIdRequest(requestId);
            m.setRecord(SingletonDAO.getRecordDAOInstance(mcontext).getItemById(recordId));
            cursor.close();
            return  m;
        }
        else return null;
    }
    public  Integer getIdMax(){
        Cursor cursor = mDatabase.rawQuery("select MAX("+ DBHelper.COLUMN_REQUEST_ID +") from " + DBHelper.TABLE_REQUEST,null);
        int count=0;
        if(cursor!=null && cursor.moveToFirst()){
            count=cursor.getInt(0);
        }
        cursor.close();
        return  count;
    }
    public void deleteRequest(int id) {
        mDatabase.delete(DBHelper.TABLE_REQUEST, DBHelper.COLUMN_REQUEST_ID + " = ?", new String[] { String.valueOf(id) });
    }
    public void deleteRequestByIdRecord(int id){
       mDatabase.delete(DBHelper.TABLE_REQUEST, DBHelper.COLUMN_RECORD_ID + " = ?", new String[] { String.valueOf(id) });
    }
}
