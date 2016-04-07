package it.moondroid.coverflowdemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.moondroid.coverflowdemo.R;
import it.moondroid.coverflowdemo.models.ImageEntity;


public class GridViewAdapter extends ArrayAdapter<ImageEntity> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ImageEntity> data = new ArrayList<>();

    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<ImageEntity> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        final ImageEntity item = data.get(position);
        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(mContext.getResources(), item.getImage()), 200, 200);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(thumbnail);

        Palette.generateAsync(((BitmapDrawable) holder.image.getDrawable()).getBitmap(),
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrant =
                                palette.getVibrantSwatch();
                        if (vibrant != null) {
                            holder.imageTitle.setBackgroundColor(
                                    vibrant.getRgb());
                            holder.imageTitle.getBackground().setAlpha(80);
                            holder.imageTitle.setTextColor(
                                    vibrant.getTitleTextColor());
                        }
                    }
                });

        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}