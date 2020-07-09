package ultils;

import android.content.Context;

import data.db.repository.MeetingDAO;
import data.db.repository.RecordDAO;
import data.db.repository.RequestDAO;
import data.db.repository.RoomDAO;
import data.db.repository.SessionDAO;

public class SingletonDAO {
    private static MeetingDAO meetingDAO=null;
    private static RecordDAO recordDAO=null;
    private static RoomDAO roomDAO=null;
    private static SessionDAO sessionDAO=null;
    private static RequestDAO requestDAO=null;
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
}

