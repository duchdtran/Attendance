

package ui.attendee;


import android.content.Context;

import java.util.List;

import data.model.app.AttendeeDto;
import data.model.app.DutyDto;
import data.model.app.MeetingDto;
import data.model.app.SessionDto;
import data.model.app.SpeakerDto;
import data.network.Callback;
import ui.base.MvpInteractor;

public interface AttendeeMvpInteractor extends MvpInteractor {
       List<SessionDto> getSessionByIdMeeting(Integer id);
       SessionDto getDefaultSession(MeetingDto meetingDto);
       List<SpeakerDto> getAllSpeaker();
       List<AttendeeDto> getAllAttendee();
       List<AttendeeDto> getBySession(SessionDto sessionDto);
       List<SpeakerDto> getSpeakerDontMeeting(SessionDto sessionDto);
       List<DutyDto> getAllDuty();
       void creAttendee(AttendeeDto attendeeDto, final Context context, final Callback volley);
}
