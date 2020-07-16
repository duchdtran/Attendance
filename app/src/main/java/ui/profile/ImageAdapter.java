package ui.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> listImage;

    public ImageAdapter(Context mContext, List<Bitmap> listImage) {
        this.mContext = mContext;
        this.listImage = listImage;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }
    public void addItems(Bitmap bitmap) {
        listImage.add(bitmap);
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
        imageView.setImageBitmap(listImage.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(300, 300));

        return imageView;
    }
}
