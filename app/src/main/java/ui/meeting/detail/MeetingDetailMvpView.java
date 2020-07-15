
package ui.meeting.detail;

import java.util.List;

import data.model.app.SessionDto;
import ui.base.MvpView;

public interface MeetingDetailMvpView extends MvpView {
    void initRecycle(List<SessionDto> sessionList);
    void updateSession(List<SessionDto> sessionList);
}
