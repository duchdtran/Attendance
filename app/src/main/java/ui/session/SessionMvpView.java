
package ui.session;

import java.util.List;

import data.model.SessionDto;
import ui.base.MvpView;

public interface SessionMvpView extends MvpView {
    void updateSession(List<SessionDto> sessionList);
    void initRecycle(List<SessionDto> sessionList);
    void updateAdapter();
}
