
package ui.session.detail;


import data.network.ApiHelper;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;


public class SessionDetailInteractor extends BaseInteractor
        implements SessionDetailMvpInteractor {

    public SessionDetailInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }

}
