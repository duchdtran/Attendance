package ui.session.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ubnd.attendance.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import ui.base.BaseViewHolder;

public class ImageSessionAdapter  extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    Context context=null;
    private List<Bitmap> bitmapList;
    ArrayList<Bitmap> arr;

    public ImageSessionAdapter(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        this.arr=new ArrayList<Bitmap>();
        this.arr.addAll(bitmapList);
    }

    public  void addItems(Bitmap bitmap){
        bitmapList.add(bitmap);
        notifyDataSetChanged();
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
        context = parent.getContext();
        View view;

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams lp = new GridView.LayoutParams(300, 300);
        imageView.setLayoutParams(lp);
        return new ViewHolder(imageView);
    }

    @Override
    public int getItemViewType(int position) {
        if (bitmapList != null && bitmapList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (bitmapList != null && bitmapList.size() > 0) {
            return bitmapList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Bitmap> repoList) {
        bitmapList.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }
    public class ViewHolder extends BaseViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        @Override
        protected void clear() {
            ((ImageView)itemView).setImageBitmap(null);
        }

        public void onBind(final int position) {
            super.onBind(position);
            final Bitmap bitmap = bitmapList.get(position);
            ((ImageView)itemView).setImageBitmap(bitmap);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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

    public String removeAccent(String s){
        String tmp = Normalizer.normalize(s,Normalizer.Form.NFD);
        Pattern pattern =  Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+");
        return pattern.matcher(tmp).replaceAll("");
    }

}