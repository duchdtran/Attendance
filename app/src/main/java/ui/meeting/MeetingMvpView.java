
package ui.meeting;

import java.util.List;

import data.model.app.MeetingDto;
import ui.base.MvpView;

public interface MeetingMvpView extends MvpView {
    void updateMeeting(List<MeetingDto> meetingList);
    void initRecycle(List<MeetingDto> meetingList);
    void updateAdapter();

    void openMeetingDetailActivity(int position);
}
