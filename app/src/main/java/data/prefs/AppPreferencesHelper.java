package data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import data.model.app.ConfigRecord;
import data.model.app.UserDto;
import ultils.AppConstants;

public class AppPreferencesHelper implements PreferencesHelper{
    private static final String PREF_KEY_USER_FULLNAME="fullname";
    private static final String PREF_KEY_USER_ADDRESS="address";
    private static final String PREF_KEY_USER_EMAIL="email";
    private static final String PREF_KEY_USER_PHONE="phone";
    private static final String PREF_KEY_USER_ID="id";
    private static final String PREF_KEY_USER_TOKEN="token";
    private static final String PREF_KEY_USER_ROLES="roles";
    private static final String PREF_KEY_CREDATE_RECORD="record";
    private static final String PREF_KEY_CREDATE_IMAGE_USER="image_user";
    private static final String PREF_KEY_CREDATE_MEETING="meeting";
    private static final String PREF_KEY_CREDATE_SESSION="session";
    private static final String PREF_KEY_CREDATE_SPEAKER="speaker";
    private static final String PREF_KEY_CREDATE_ATTENDEE="attendee";
    private static final String PREF_KEY_CONFIG_ISCHECK="ischeck";
    private static final String PREF_KEY_CONFIG_MAXSIZE="maxSize";
    private static final String PREF_KEY_CONFIG_CHANEL="channel";
    private static final String PREF_KEY_CONFIG_FREQUENCE="frequence";
    private static final String PREF_KEY_CONFIG_FORMAT="format";
    private static final String PREF_KEY_CONFIG_MAXTIME="maxTime";
    private static final String PREF_KEY_CONFIG_BITNUMBER="bitNumber";
    private SharedPreferences prefs1,prefs2,prefs3;
    String pref1= AppConstants.PREF_NAME_USER,
            pref2=AppConstants.PREF_NAME_CREDATE,
            pref3=AppConstants.PREF_NAME_CONFIG;

    SharedPreferences.Editor editor1,editor2,editor3;
    Context context;
    public AppPreferencesHelper(Context context) {
        this.context=context;
        prefs1=context.getSharedPreferences(pref1,Context.MODE_PRIVATE);
        prefs2=context.getSharedPreferences(pref2,Context.MODE_PRIVATE);
        prefs3=context.getSharedPreferences(pref3,Context.MODE_PRIVATE);
        editor1=prefs1.edit();
        editor2=prefs2.edit();
        editor3=prefs3.edit();
    }

    @Override
    public void setUser(UserDto user, String token, String roles) {

        editor1.putString(PREF_KEY_USER_FULLNAME,user.getFullName());
        editor1.putString(PREF_KEY_USER_ADDRESS,user.getAddress());
        editor1.putString(PREF_KEY_USER_EMAIL,user.getEmail());
        editor1.putInt(PREF_KEY_USER_ID,user.getUserId());
        editor1.putString(PREF_KEY_USER_PHONE,user.getPhone());
        editor1.putString(PREF_KEY_USER_TOKEN,token);
        editor1.putString(PREF_KEY_USER_ROLES, roles);
        editor1.apply();
    }

    @Override
    public void setRoles(String roles) {
        editor1.putString(PREF_KEY_USER_ROLES,roles);
    }
    @Override
    public void setToken(String token) {
        editor1.putString(PREF_KEY_USER_TOKEN,token);
    }

    @Override
    public UserDto getUser() {
        UserDto user = new UserDto(prefs1.getInt(PREF_KEY_USER_ID,-1),prefs1.getString(PREF_KEY_USER_FULLNAME,null),prefs1.getString(PREF_KEY_USER_EMAIL,null),prefs1.getString(PREF_KEY_USER_PHONE,null),prefs1.getString(PREF_KEY_USER_ADDRESS,null));
        return user;
    }
    @Override
    public String getRoles(){
        return prefs1.getString(PREF_KEY_USER_ROLES,null);
    }
    @Override
    public String getToken(){
        return prefs1.getString(PREF_KEY_USER_TOKEN,null);
    }

    @Override
    public void logout() {
        editor1.putString(PREF_KEY_USER_TOKEN,null);
        editor1.apply();
    }

    @Override
    public void setCreDateRecord(String date) {
        editor2.putString(PREF_KEY_CREDATE_RECORD,date);
        editor2.apply();
    }

    @Override
    public void setCreDateImageUser(String date) {
        editor2.putString(PREF_KEY_CREDATE_IMAGE_USER,date);
        editor2.apply();
    }

    @Override
    public void setCreDateMeeting(String date) {
        editor2.putString(PREF_KEY_CREDATE_MEETING,date);
        editor2.apply();
    }

    @Override
    public void setCreDateSession(String date) {
        editor2.putString(PREF_KEY_CREDATE_SESSION,date);
        editor2.apply();
    }

    @Override
    public void setCreDateSpeaker(String date) {
        editor2.putString(PREF_KEY_CREDATE_SPEAKER,date);
        editor2.apply();
    }

    @Override
    public void setCreDateAttendee(String date) {
        editor2.putString(PREF_KEY_CREDATE_ATTENDEE,date);
        editor2.apply();
    }

    @Override
    public String getCreDateRecord() {
        return prefs2.getString(PREF_KEY_CREDATE_RECORD,"2017-1-1 00:00:00");
    }

    @Override
    public String getCreDateImageUser() {
        return prefs2.getString(PREF_KEY_CREDATE_IMAGE_USER,"2017-1-1 00:00:00");
    }

    @Override
    public String getCreDateMeeting() {
        return prefs2.getString(PREF_KEY_CREDATE_MEETING,"2017-1-1 00:00:00");
    }

    @Override
    public String getCreDateSession() {
        return prefs2.getString(PREF_KEY_CREDATE_SESSION,"2017-1-1 00:00:00");
    }

    @Override
    public String getCreDateSpeaker() {
        return prefs2.getString(PREF_KEY_CREDATE_SPEAKER,"2017-1-1 00:00:00");
    }

    @Override
    public String getCreDateAttendee() {
        return prefs2.getString(PREF_KEY_CREDATE_ATTENDEE,"2017-1-1 00:00:00");
    }

    @Override
    public void setConfigRecord(ConfigRecord config) {
        editor3.putInt(PREF_KEY_CONFIG_MAXSIZE,config.getMaxSize());
        editor3.putString(PREF_KEY_CONFIG_CHANEL,config.getChannel());
        editor3.putInt(PREF_KEY_CONFIG_FREQUENCE,config.getFrequence());
        editor3.putString(PREF_KEY_CONFIG_FORMAT,config.getFormat());
        editor3.putInt(PREF_KEY_CONFIG_BITNUMBER,config.getBitNumber());
        editor3.putInt(PREF_KEY_CONFIG_MAXTIME,config.getMaxTime());
        editor3.apply();
    }

    @Override
    public ConfigRecord getConfigRecord() {
        ConfigRecord configRecord = new ConfigRecord();
        configRecord.setMaxTime(prefs3.getInt(PREF_KEY_CONFIG_MAXTIME,3600));
        configRecord.setMaxSize(prefs3.getInt(PREF_KEY_CONFIG_MAXSIZE,1024));
        configRecord.setFrequence(prefs3.getInt(PREF_KEY_CONFIG_FREQUENCE,44100));
        configRecord.setFormat(prefs3.getString(PREF_KEY_CONFIG_FORMAT,".mp3"));
        configRecord.setChannel(prefs3.getString(PREF_KEY_CONFIG_CHANEL,"Mono"));
        configRecord.setBitNumber(prefs3.getInt(PREF_KEY_CONFIG_BITNUMBER,32));
        return  configRecord;
    }

    @Override
    public void setCheckConfigRecord(boolean ischeck) {
        editor3.putBoolean(PREF_KEY_CONFIG_ISCHECK,ischeck);
        editor3.apply();
    }

    @Override
    public boolean getCheckConfigRecord() {
        return prefs3.getBoolean(PREF_KEY_CONFIG_ISCHECK,true);
    }
}
