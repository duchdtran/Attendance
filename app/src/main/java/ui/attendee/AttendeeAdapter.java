
package ui.attendee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ubnd.attendance.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import data.model.app.AttendeeDto;
import data.model.app.MeetingDto;
import ui.base.BaseViewHolder;


public class AttendeeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<AttendeeDto> attendeeList;
    private List<AttendeeDto> arr;

    public  boolean isCheck=false;
    public  int statusAttendee=0;
    Context context=null;
    public AttendeeAdapter(List<AttendeeDto> attendeeList) {
       this.attendeeList = attendeeList;
       this.arr= new ArrayList<>();
       this.arr.addAll(attendeeList);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         this.context = parent.getContext();
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendee, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (attendeeList != null && attendeeList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (attendeeList != null && attendeeList.size() > 0) {
            return attendeeList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<AttendeeDto> repoList) {
        int index = attendeeList.size();
        attendeeList.addAll(repoList);
        notifyItemRangeChanged(index,repoList.size());
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }
    public class ViewHolder extends BaseViewHolder {
        TextView tvName, tvDuty, tvPhoneNumber, tvStatus;
                public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name_attendee);
            tvDuty = itemView.findViewById(R.id.tv_duty_attendee);
            tvPhoneNumber= itemView.findViewById(R.id.tv_phone_number_attendee);
            tvStatus=itemView.findViewById(R.id.tv_status_attendee);
        }

        @Override
        protected void clear() {
            tvName.setText("");
            tvDuty.setText("");
            tvPhoneNumber.setText("");
            tvStatus.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            final AttendeeDto attendeeDto = attendeeList.get(position);
             tvName.setText(attendeeDto.getSpeakerDto().getFullName());
             tvDuty.setText(attendeeDto.getDutyDto().getDutyDescription());
             tvPhoneNumber.setText(attendeeDto.getSpeakerDto().getPhone());
             statusAttendee= attendeeDto.getStatus();
             String sttString="";
             if(statusAttendee==0) {
                 sttString = "Không tham dự";
                 isCheck=false;
             }
             else  if(statusAttendee==2||statusAttendee==1) {
                 isCheck = true;
                 sttString="Tham dự";
             }
             tvStatus.setText(sttString);
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

    public void filter(boolean isAttendee){
        attendeeList.clear();
        if(isAttendee){
            for(AttendeeDto m: arr){
                // tim kiem nguoi tham du
                if(m.getStatus() != 0){
                    attendeeList.add(m);
                }
            }
        }
        else{
            for(AttendeeDto m: arr){
                // tim kiem nguoi khong tham du
                if(m.getStatus() == 0){
                    attendeeList.add(m);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void filter(String charText){
        charText = charText .toLowerCase(Locale.getDefault());
        charText = removeAccent(charText);
        attendeeList.clear();
        if(charText.length()==0){
            attendeeList.addAll(arr);
        }
        else{
            for(AttendeeDto m: arr){
                // tim kiem theo thuoc tinh name
                if(removeAccent(m.getSpeakerDto().getFullName().toLowerCase(Locale.getDefault())).contains(charText)){
                    attendeeList.add(m);
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
