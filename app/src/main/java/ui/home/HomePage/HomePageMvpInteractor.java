

package ui.home.HomePage;


import data.model.app.UserDto;
import ui.base.MvpInteractor;

public interface HomePageMvpInteractor extends MvpInteractor {

    public UserDto getDataUser();
    public String getRoles();
}
