package ui.session.image;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import data.model.AttendanceAdminDto;
import data.model.app.AttendeeDto;
import ui.attendee.AttendeeAdapter;
import ui.base.BaseViewHolder;

public class SessionAttendeeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private SessionAttendeeAdapter.Callback mCallback;
    private List<AttendanceAdminDto> attendeeList;
    private List<AttendanceAdminDto> arr;

    public  boolean isCheck=false;
    public  int statusAttendee=0;
    Context context=null;
    public SessionAttendeeAdapter(List<AttendanceAdminDto> attendeeList) {
        this.attendeeList = attendeeList;
        this.arr= new ArrayList<>();
        this.arr.addAll(attendeeList);
    }

    public void setCallback(SessionAttendeeAdapter.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        return new SessionAttendeeAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_attendee, parent, false));
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
            return 0;
        }
    }

    public void addItems(List<AttendanceAdminDto> repoList) {
        int index = attendeeList.size();
        attendeeList.addAll(repoList);
        notifyItemRangeChanged(index,repoList.size());
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }
    public class ViewHolder extends BaseViewHolder {
        TextView tvName, tvDuty;
        CheckBox cbxAttendee;
        ImageView imvFace;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvDuty = itemView.findViewById(R.id.tv_duty);
            cbxAttendee= itemView.findViewById(R.id.cbx_attendee);
            imvFace=itemView.findViewById(R.id.imv_face);
        }

        @Override
        protected void clear() {
            tvName.setText("");
            tvDuty.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            final AttendanceAdminDto attendeeDto = attendeeList.get(position);
            tvName.setText(attendeeDto.getFull_name());
            tvDuty.setText("Thu ki");
            //setImage ....
            imvFace.setImageBitmap(attendeeDto.getBmp_crop_face());
            if(!attendeeDto.getFull_name().equals("null"))
                cbxAttendee.setChecked(true);
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
