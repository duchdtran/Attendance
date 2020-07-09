
package ui.home.HomePage;


import ui.base.MvpInteractor;
import ui.base.MvpPresenter;


public interface HomePageMvpPresenter<V extends HomePageMvpView, I extends HomePageMvpInteractor & MvpInteractor>
        extends MvpPresenter<V, I> {

}


