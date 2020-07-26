package data.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import data.db.DBHelper;
import data.model.app.ImageUserDto;
import data.model.app.MeetingDto;

public class ImageUserDAO {
    private Context mcontext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    public ImageUserDAO(Context context){
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

    public static ArrayList<String> getAllItem() {
        ArrayList<String> imageList = new ArrayList<>();
        return imageList;
    }

    public void open(){
        mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.enableWriteAheadLogging();
    }
    public void close(){
        mDBHelper.close();
    }
    public  boolean addImageUser(ImageUserDto imageUserDto){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COLUMN_NAME_USER, imageUserDto.getNameUser());
            contentValues.put(DBHelper.COLUMN_USER_ID, imageUserDto.getUserID());
            contentValues.put(DBHelper.COLUMN_DATA, imageUserDto.getData());
            contentValues.put(DBHelper.COLUMN_PATH, imageUserDto.getPath());
            contentValues.put(DBHelper.COLUMN_CRE_UID, imageUserDto.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, imageUserDto.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, imageUserDto.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE, imageUserDto.getModDate());
            mDatabase.insert(DBHelper.TABLE_IMAGE_USER,null,contentValues);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public ArrayList<ImageUserDto> getAllItems(){
        ArrayList<ImageUserDto> arrayList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_IMAGE_USER,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                int imageUserId = cursor.getInt(0);
                int userId = cursor.getInt(1);
                String nameUser = cursor.getString(2);
                String path =cursor.getString(3);
                String data =cursor.getString(4);
                int creUID =cursor.getInt(5);
                String creDate =cursor.getString(6);
                int modUID =cursor.getInt(7);
                String modDate =cursor.getString(8);
                ImageUserDto imageUserDto = new ImageUserDto(imageUserId, userId,nameUser,path,data,creUID,creDate,modUID,modDate);
                arrayList.add(imageUserDto);
            }
            cursor.close();
            return  arrayList;
        }
        else return null;
    }

    public ImageUserDto getItemById(int id){
        Cursor cursor =null;
        cursor= mDatabase.rawQuery("select * from " + DBHelper.TABLE_IMAGE_USER +" where "+ DBHelper.COLUMN_IMAGE_USER_ID+"="+id,null);
        if(cursor!=null&&cursor.moveToFirst()){
            int imageUserId = cursor.getInt(0);
            int userId = cursor.getInt(1);
            String nameUser = cursor.getString(2);
            String path =cursor.getString(3);
            String data =cursor.getString(4);
            int creUID =cursor.getInt(5);
            String creDate =cursor.getString(6);
            int modUID =cursor.getInt(7);
            String modDate =cursor.getString(8);
            ImageUserDto imageUserDto = new ImageUserDto(imageUserId, userId,nameUser,path,data,creUID,creDate,modUID,modDate);
            cursor.close();
            return imageUserDto;
        }
        else return null;
    }
    public void updateImageUser(ImageUserDto imageUserDto) {
        ContentValues contentValues = new ContentValues();
             contentValues.put(DBHelper.COLUMN_NAME_USER, imageUserDto.getNameUser());
            contentValues.put(DBHelper.COLUMN_USER_ID, imageUserDto.getUserID());
            contentValues.put(DBHelper.COLUMN_DATA, imageUserDto.getData());
            contentValues.put(DBHelper.COLUMN_PATH, imageUserDto.getPath());
            contentValues.put(DBHelper.COLUMN_CRE_UID, imageUserDto.getCreUID());
            contentValues.put(DBHelper.COLUMN_CRE_DATE, imageUserDto.getCreDate());
            contentValues.put(DBHelper.COLUMN_MOD_UID, imageUserDto.getModUID());
            contentValues.put(DBHelper.COLUMN_MOD_DATE, imageUserDto.getModDate());
        mDatabase.update(DBHelper.TABLE_IMAGE_USER, contentValues, null, new String[] {});
    }

    public ImageUserDto getItemByPath(String path){
        Cursor cursor = mDatabase.rawQuery("select * from " + DBHelper.TABLE_IMAGE_USER +" where "+ DBHelper.COLUMN_PATH+ "='"+path+"'",null);
        cursor.moveToFirst();
        if(cursor!=null){
            int imageUserId = cursor.getInt(0);
            int userId = cursor.getInt(1);
            String nameUser = cursor.getString(2);
            String path1 =cursor.getString(3);
            String data =cursor.getString(4);
            int creUID =cursor.getInt(5);
            String creDate =cursor.getString(6);
            int modUID =cursor.getInt(7);
            String modDate =cursor.getString(8);
            ImageUserDto imageUserDto = new ImageUserDto(imageUserId, userId,nameUser,path1,data,creUID,creDate,modUID,modDate);
            cursor.close();
            return imageUserDto;
        }
        else return null;
    }

}
