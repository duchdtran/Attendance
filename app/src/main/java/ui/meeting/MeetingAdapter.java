
package ui.meeting;

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

import data.model.MeetingDto;
import ui.base.BaseViewHolder;


public class MeetingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private OnItemClickListener mListener;
    private List<MeetingDto> meetingList;
    ArrayList<MeetingDto> arr;

    public MeetingAdapter(List<MeetingDto> meetingList) {
       this.meetingList = meetingList;
        this.arr=new ArrayList<MeetingDto>();
        this.arr.addAll(meetingList);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
                return new ViewHolder(view, mListener);
            case VIEW_TYPE_EMPTY:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false);
                return new EmptyViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (meetingList != null && meetingList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (meetingList != null && meetingList.size() > 0) {
            return meetingList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<MeetingDto> repoList) {
        meetingList.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }
    public interface OnItemClickListener{
        void onItemClick(int posision);
    }
    public class ViewHolder extends BaseViewHolder {
        View viewStatus;
        TextView tvName,tvTime,tvAddress;
        ImageButton btnMore;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvAddress = itemView.findViewById(R.id.tv_address);
            viewStatus = itemView.findViewById(R.id.view_status);
            btnMore = itemView.findViewById(R.id.btn_more);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        protected void clear() {
            tvName.setText("");
            tvTime.setText("");
            tvAddress.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            MeetingDto meetingDto = meetingList.get(position);
            if (meetingDto.getName() != null) {
                String nameM=meetingDto.getName();
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

            if (meetingDto.getAddress()!= null) {
                String nameM=meetingDto.getAddress();
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
            if (meetingDto.getTimeStart()!= null) {
                tvTime.setText(meetingDto.getTimeStart());
            }
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
        meetingList.clear();
        if(charText.length()==0){
            meetingList.addAll(arr);
        }
        else{
            for(MeetingDto m: arr){
                // tim kiem theo thuoc tinh name
                if(removeAccent(m.getName().toLowerCase(Locale.getDefault())).contains(charText)){
                    meetingList.add(m);
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
