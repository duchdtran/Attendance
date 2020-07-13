package ui.home.calendar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import data.model.SessionDto;
import ui.base.BaseViewHolder;
import ultils.AppConstants;

public class CalendarAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private ui.session.SessionAdapter.Callback mCallback;
    private List<SessionDto> sessionList;
    ArrayList<SessionDto> arr;

    public CalendarAdapter(List<SessionDto> sessionList) {
        this.sessionList = sessionList;
        this.arr=new ArrayList<SessionDto>();
        this.arr.addAll(sessionList);
    }

    public void setCallback(ui.session.SessionAdapter.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new CalendarAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new CalendarAdapter.EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_calendar, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (sessionList != null && sessionList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (sessionList != null && sessionList.size() > 0) {
            return sessionList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<SessionDto> repoList) {
        sessionList.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }
    public class ViewHolder extends BaseViewHolder {
        View viewStatus;
        TextView tvName, tvMeeting, tvTime,tvAddress;
        Button btnAttendance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMeeting = itemView.findViewById(R.id.tv_meeting);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvAddress = itemView.findViewById(R.id.tv_address);
            viewStatus = itemView.findViewById(R.id.view_status);
            btnAttendance = itemView.findViewById(R.id.btn_attendance);
        }

        @Override
        protected void clear() {
            tvName.setText("");
            tvMeeting.setText("");
            tvTime.setText("");
            tvAddress.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            SessionDto sessionDto = sessionList.get(position);
            if (sessionDto.getName() != null) {
                String nameM=sessionDto.getName();
                String tmp="";
                if(nameM.length()>30){
                    int j=30;
                    for(;j>=0;j--){
                        char c = nameM.charAt(j);
                        if((int) c==32) break;
                    }
                    tmp+=nameM.substring(0,j);
                    tmp+="...";

                }
                else tmp =nameM;
                tvName.setText(tmp);

            }
            if (sessionDto.getMeetingDto().getName() != null) {
                String nameM=sessionDto.getMeetingDto().getName();
                String tmp="";
                if(nameM.length()>30){
                    int j=30;
                    for(;j>=0;j--){
                        char c = nameM.charAt(j);
                        if((int) c==32) break;
                    }
                    tmp+=nameM.substring(0,j);
                    tmp+="...";

                }
                else tmp =nameM;
                tvMeeting.setText(tmp);

            }

            if (sessionDto.getRoomDto().getRoomName()!= null) {
                String nameM=sessionDto.getRoomDto().getRoomName();
                String tmp="";
                if(nameM.length()>20){
                    int j=20;
                    for(;j>=0;j--){
                        char c = nameM.charAt(j);
                        if((int) c==32) break;
                    }
                    tmp+=nameM.substring(0,j);
                    tmp+="...";

                }
                else tmp = nameM;
                tvAddress.setText(tmp);
            }
            if (sessionDto.getTimeStart()!= null && sessionDto.getTimeEnd() != null) {
                String timeString = "";
                String timeStart, timeEnd;

                try {
                    DateFormat sdfDate = new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT);
                    DateFormat sdfTime = new SimpleDateFormat(AppConstants.TIME_FORMAT);

                    Date date;
                    date = sdfDate.parse(sessionDto.getTimeStart());
                    timeStart = sdfTime.format(date);

                    date = sdfDate.parse(sessionDto.getTimeEnd());
                    timeEnd = sdfTime.format(date);


                    tvTime.setText(timeStart + " - " + timeEnd);
                }catch (ParseException e){
                    Log.e("AAA", "Lỗi chuyển đổi thời gian");
                }
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("AAA",position+"pos");
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }
    public void filter(String charText){
        charText = charText .toLowerCase(Locale.getDefault());
        charText = removeAccent(charText);
        sessionList.clear();
        if(charText.length()==0){
            sessionList.addAll(arr);
        }
        else{
            for(SessionDto m: arr){
                // tim kiem theo thuoc tinh name
                if(removeAccent(m.getName().toLowerCase(Locale.getDefault())).contains(charText)){
                    sessionList.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }
    public String removeAccent(String s){
        String tmp = Normalizer.normalize(s,Normalizer.Form.NFD);
        Pattern pattern =  Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+");
        return pattern.matcher(tmp).replaceAll("");
    }

}


