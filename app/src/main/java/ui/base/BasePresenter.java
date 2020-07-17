package ui.base;


import android.content.Context;

import java.util.ArrayList;

import ui.login.LoginActivity;
import ultils.CommonUtils;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView, I extends MvpInteractor>
        implements MvpPresenter<V, I> {

    private static final String TAG = "BasePresenter";

    private V mMvpView;
    private I mMvpInteractor;


    public BasePresenter(I mvpInteractor) {
        mMvpInteractor = mvpInteractor;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mMvpInteractor = null;
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    @Override
    public I getInteractor() {
        return mMvpInteractor;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void checkViewAttached() throws MvpViewNotAttachedException {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void setUserAsLoggedOut() {
        getInteractor().setAccessToken(null);
    }

    @Override
    public void setLogout(Context context) {
        if(CommonUtils.isLogut(context))
            LoginActivity.getStartIntent(context);
    }

    @Override
    public String setErrorMessage(ArrayList<Integer> errorNull, ArrayList<Integer> errorExists, ArrayList<Integer> errorTime,int cout) {
        String error="";
        if(errorNull.size()>0){
            error = errorNull.size()+" Dòng thiếu dữ liệu: ";
            for(int i=0;i<errorNull.size();i++){
                error +=errorNull.get(i);
                if(i<errorNull.size()-1) error+=",";
            }
        }
        if(errorExists.size()>0){
            error += "\n" + errorExists.size() + " Dòng đã tồn tại: ";
            for (int i = 0; i < errorExists.size(); i++) {
                error += errorExists.get(i) ;
                if(i<errorExists.size()-1) error+=",";
            }
        }
        if(errorTime.size()>0){
            error+="\n"+errorTime.size() +" Dòng đã lỗi thời gian: ";
            for(int i=0;i<errorTime.size();i++) {
                error +=errorTime.get(i);
                if(i<errorTime.size()-1) error+=",";
            }
        }
        if(error.length()==0) error="Tất cả dữ liệu đều hợp lệ";
        else{
            int valid=  (cout-errorExists.size()-errorNull.size()-errorTime.size());
            if (valid>0)
                error+="\n" + valid + " hàng hợp lệ.";
        }
        error+="\n \n"+"Bạn chắc chắn muốn thêm dữ liệu?";
        return error;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
