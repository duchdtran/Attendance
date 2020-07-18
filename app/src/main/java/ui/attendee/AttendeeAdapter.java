
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

import java.util.ArrayList;
import java.util.List;

import data.model.app.AttendeeDto;
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


}
