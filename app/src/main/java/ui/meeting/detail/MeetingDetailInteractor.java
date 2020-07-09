
package ui.meeting.detail;


import data.network.ApiHelper;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;


public class MeetingDetailInteractor extends BaseInteractor
        implements MeetingDetailMvpInteractor {

    public MeetingDetailInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }

}
