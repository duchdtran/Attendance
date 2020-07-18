package ui.attendee;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import data.db.repository.AttendeeDAO;
import data.db.repository.DutyDAO;
import data.db.repository.SessionDAO;
import data.db.repository.SpeakerDAO;
import data.model.app.AttendeeDto;
import data.model.app.DutyDto;
import data.model.app.MeetingDto;
import data.model.app.SessionDto;
import data.model.app.SpeakerDto;
import data.network.ApiHelper;
import data.network.Callback;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;
import ultils.SingletonDAO;

public class AttendeeInteractor extends BaseInteractor
        implements AttendeeMvpInteractor {

       SessionDAO sessionDAO;
       SpeakerDAO speakerDAO;
       AttendeeDAO attendeeDAO;
       DutyDAO dutyDAO;
       Context context;
       List<SpeakerDto> speakerDtos;
       List<AttendeeDto> attendeeDtos;
    public AttendeeInteractor(PreferencesHelper preferencesHelper,
                              ApiHelper apiHelper, Context context) {
        super(preferencesHelper, apiHelper);
        this.context=context;
        this.sessionDAO= SingletonDAO.getSessionDAOInstace(context);
        this.speakerDAO = SingletonDAO.getSpeakerDAOInstace(context);
        this.attendeeDAO = SingletonDAO.getAttendeeDAOInstace(context);
        this.dutyDAO = SingletonDAO.getDutyDAOInstace(context);
    }



    @Override
    public List<SessionDto> getSessionByIdMeeting(Integer id) {
        return sessionDAO.getSessionByIdMeeting(id);
    }

    @Override
    public SessionDto getDefaultSession(MeetingDto meetingDto) {
        return sessionDAO.getSessionDefault(meetingDto);
    }

    @Override
    public List<SpeakerDto> getAllSpeaker() {
        return speakerDAO.getAllItems();
    }

    @Override
    public List<AttendeeDto> getAllAttendee() {
        return attendeeDAO.getAll();
    }

    @Override
    public List<AttendeeDto> getBySession(SessionDto sessionDto) {
        return attendeeDAO.getBySession(sessionDto);
    }

    @Override
    public List<SpeakerDto> getSpeakerDontMeeting(SessionDto sessionDto) {
        List<SpeakerDto> list = new ArrayList<>();
        attendeeDtos = getBySession(sessionDto);
        speakerDtos = getAllSpeaker();
        for(SpeakerDto item:speakerDtos){
            SpeakerDto tg = checkExitst(attendeeDtos,item);
            if(tg!=null) list.add(tg);
        }
        return  list;
    }

    @Override
    public List<DutyDto> getAllDuty() {
        return dutyDAO.getAll();
    }

    @Override
    public void creAttendee(AttendeeDto attendeeDto, Context context, Callback volley) {
        getApiHelper().creAttendee(attendeeDto,context,volley);
    }

    private SpeakerDto checkExitst(List<AttendeeDto> list, SpeakerDto speakerDto){
        SpeakerDto speakerDto1=speakerDto;
        for(AttendeeDto item:list){
            if(item.getSpeakerDto().getFullName().equals(speakerDto.getFullName())&&item.getSpeakerDto().getEmail().equals(speakerDto.getEmail())&&item.getSpeakerDto().getBirthday().equals(speakerDto.getBirthday())) {
                return  null;
            }
        }
        return  speakerDto1;
    }



}
