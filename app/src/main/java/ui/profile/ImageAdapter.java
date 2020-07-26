package ui.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import data.model.app.ImageUserDto;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageUserDto> listImage;

    public ImageAdapter(Context mContext, List<ImageUserDto> listImage) {
        this.mContext = mContext;
        this.listImage = listImage;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }
    public void addItems(ImageUserDto image) {
        listImage.add(image);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return listImage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext).load(listImage.get(position).getPath()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(300, 300));

        return imageView;
    }
}
