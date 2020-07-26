package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.ubnd.attendance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.model.app.ImageUserDto;
import data.model.app.RecordDto;
import data.model.app.Request;
import data.network.AppApiHelper;
import ultils.JsonUltil;
import ultils.NetworkUtils;
import ultils.SingletonDAO;

public class NetworkChangeReceiver extends BroadcastReceiver {
    int response=0;
    AppApiHelper appApiHelper;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if( NetworkUtils.isNetworkConnected(context)){
            appApiHelper=new AppApiHelper(context);
            CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("cho","cho123");
                }

                @Override
                public void onFinish() {
                    ArrayList<Request> list=new ArrayList<>();
                    ArrayList<Request> tmp= SingletonDAO.getRequestDAOInstace(context).getAllItems();
                    if(tmp!=null) list=tmp;
                    if(list.size()>0){
                        for(int i=0;i<list.size();i++){
                            final ImageUserDto imageUserDto = list.get(i).getImageUserDto();
                            final int finalI = i;
                            final ArrayList<Request> finalList = list;
                            new Thread(new Runnable() {
                                public void run() {
                                    final Map<String,String> params = new HashMap<>();
                                    String imageUser = JsonUltil.getJson(imageUserDto);
                                    params.put("image_user",imageUser);

                                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");

                                    response=appApiHelper.uploadFileImage(context, imageUserDto,params,bitmap);
                                    if(response==200) {
                                        SingletonDAO.getRequestDAOInstace(context).deleteRequest(finalList.get(finalI).getIdRequest());
                                        SingletonDAO.getImageUserDAOInstance(context).updateImageUser(imageUserDto);
                                        Context context1 = context;
                                    }
                                    else Log.d("AAA","erro");
                                }
                            }).start();
                        }

                    }
                }
            }.start();
        }
        else Toast.makeText(context, "No Internet. Try it,please!", Toast.LENGTH_SHORT).show();

    }
}
