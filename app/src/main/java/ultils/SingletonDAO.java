package ultils;

import android.content.Context;

import data.db.repository.AttendeeDAO;
import data.db.repository.DutyDAO;
import data.db.repository.ImageUserDAO;
import data.db.repository.MeetingDAO;
import data.db.repository.RecordDAO;
import data.db.repository.RequestDAO;
import data.db.repository.RoomDAO;
import data.db.repository.SessionDAO;
import data.db.repository.SpeakerDAO;


public class SingletonDAO {
    private static MeetingDAO meetingDAO=null;
    private static RecordDAO recordDAO=null;
    private static ImageUserDAO imageUserDAO=null;
    private static RoomDAO roomDAO=null;
    private static SessionDAO sessionDAO=null;
    private static RequestDAO requestDAO=null;
    private static SpeakerDAO speakerDAO=null;
    private static AttendeeDAO attendeeDAO=null;
    private static DutyDAO dutyDAO=null;
    public static MeetingDAO getMeetingDAOInstance(Context mContext) {
        if(meetingDAO==null){
            meetingDAO = new MeetingDAO(mContext);
        }
        return meetingDAO;
    }

    public static RecordDAO getRecordDAOInstance(Context mContext) {
        if(recordDAO==null) recordDAO=new RecordDAO(mContext);
        return recordDAO;
    }

    public static ImageUserDAO getImageUserDAOInstance(Context mContext) {
        if(imageUserDAO==null) imageUserDAO=new ImageUserDAO(mContext);
        return imageUserDAO;
    }

    public static RoomDAO getRoomDAOInstance(Context mContext) {
        if(roomDAO==null) roomDAO=new RoomDAO(mContext);
        return roomDAO;
    }

    public static SessionDAO getSessionDAOInstace(Context mContext) {
        if(sessionDAO==null) sessionDAO=new SessionDAO(mContext);
        return sessionDAO;
    }
    public static RequestDAO getRequestDAOInstace(Context mContext) {
        if(requestDAO==null) requestDAO=new RequestDAO(mContext);
        return requestDAO;
    }
    public static SpeakerDAO getSpeakerDAOInstace(Context mContext) {
        if(speakerDAO==null) speakerDAO=new SpeakerDAO(mContext);
        return speakerDAO;
    }
    public static AttendeeDAO getAttendeeDAOInstace(Context mContext) {
        if(attendeeDAO==null) attendeeDAO = new AttendeeDAO(mContext);
        return attendeeDAO;
    }
    public static DutyDAO getDutyDAOInstace(Context mContext) {
        if(dutyDAO==null) dutyDAO=new DutyDAO(mContext);
        return dutyDAO;
    }
}
