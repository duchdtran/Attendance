package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public void onReceive(final Context context, Intent intent) {
        if( NetworkUtils.isNetworkConnected(context)){
             appApiHelper=new AppApiHelper(context);
            CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                  Log.d("cho","cho");
                }

                @Override
                public void onFinish() {
                    ArrayList<Request> list=new ArrayList<>();
                    ArrayList<Request> tmp= SingletonDAO.getRequestDAOInstace(context).getAllItems();
                    if(tmp!=null) list=tmp;
                    Log.d("size:",list.size()+"");
                    if(list.size()>0){
                        for(int i=0;i<list.size();i++){
                            Log.d("size",list.size()+list.get(i).getRecord().getName());

                            final RecordDto finalRecordDto = list.get(i).getRecord();
                            final int finalI = i;
                            final ArrayList<Request> finalList = list;
                            new Thread(new Runnable() {
                                public void run() {
                                    final Map<String,String> params = new HashMap<>();
                                    String recordP = JsonUltil.getJson(finalRecordDto);
                                    params.put("record",recordP);
                                    response=appApiHelper.uploadFile(context, finalRecordDto,params);
                                    Log.d("response",response+"");
                                    if(response==200) {
                                        finalRecordDto.setStatusInApp("Đã upload");
                                        SingletonDAO.getRequestDAOInstace(context).deleteRequest(finalList.get(finalI).getIdRequest());
                                        SingletonDAO.getRecordDAOInstance(context).updateRecord(finalRecordDto);
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
