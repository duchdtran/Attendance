
package ui.home;


import data.network.ApiHelper;
import data.prefs.PreferencesHelper;
import ui.base.BaseInteractor;


public class HomeInteractor extends BaseInteractor
        implements HomeMvpInteractor {

    public HomeInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        super(preferencesHelper, apiHelper);
    }

}
