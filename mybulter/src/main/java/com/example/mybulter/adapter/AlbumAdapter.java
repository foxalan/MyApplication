package com.example.mybulter.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mybulter.R;
import com.example.mybulter.info.GirlInfo;
import com.example.mybulter.util.L;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 15/8/17
 * Issue : 1,获取屏幕的长和宽
 * Whether solve :
 */

public class AlbumAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<GirlInfo> girlInfoList;
    private Context context;

    private WindowManager wm;
    private int width;


    public AlbumAdapter(Context context, List<GirlInfo> girlInfoList) {
        inflater = LayoutInflater.from(context);
        this.girlInfoList = girlInfoList;
        this.context = context;

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
    }


    @Override
    public int getCount() {
        return girlInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return girlInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_album_image,parent,false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_list_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = girlInfoList.get(position).getImageUrl();
        L.d(imageUrl);
        Picasso.with(context).load(imageUrl).resize(width / 2, width / 2).into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}
