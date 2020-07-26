package data.prefs;

import data.model.app.ConfigRecord;
import data.model.app.UserDto;


public interface PreferencesHelper {
    public void setUser(UserDto user,String token, String roles);
    public void setRoles(String roles);
    public void setToken(String token);
    public UserDto getUser();
    public String getRoles();
    public String getToken();
    public void logout();
    public void setCreDateRecord(String date);
    public void setCreDateImageUser(String date);
    public void setCreDateMeeting(String date);
    public void setCreDateSession(String date);
    public void setCreDateSpeaker(String date);
    public void setCreDateAttendee(String date);
    public String getCreDateRecord();
    public String getCreDateImageUser();
    public String getCreDateMeeting();
    public String getCreDateSession();
    public String getCreDateSpeaker();
    public String getCreDateAttendee();
    public void setConfigRecord(ConfigRecord config);
    public ConfigRecord getConfigRecord();
    public void setCheckConfigRecord(boolean ischeck);
    public boolean getCheckConfigRecord();
}
