package com.example.adanvace.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.adapter.viewholder.CustomViewHolder;
import com.example.adanvace.info.StudentInfo;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class StudentAdapter extends CustomAdapter<StudentInfo> {

    public StudentAdapter(Context mContext, List<StudentInfo> mData,int resID) {
        super(mContext, mData, resID);
    }




    /**
     * 1.简化了Adapter
     * 普通方法配制适配器
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder ;
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.list_student, parent, false);
//
//            viewHolder = new ViewHolder();
//
//            viewHolder.tv_list_id = (TextView) convertView.findViewById(R.id.tv_list_id);
//            viewHolder.tv_list_name = (TextView) convertView.findViewById(R.id.tv_list_name);
//            viewHolder.tv_list_point = (TextView) convertView.findViewById(R.id.tv_list_point);
//
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.tv_list_id.setText(mData.get(position).getId()+" ");
//        viewHolder.tv_list_name.setText(mData.get(position).getName());
//        viewHolder.tv_list_point.setText(mData.get(position).getPoint()+" ");
//
//        return convertView;
//    }
//
//    class ViewHolder {
//
//        TextView tv_list_id;
//        TextView tv_list_name;
//        TextView tv_list_point;
//    }


    /**
     * 2.简化了adapter和ViewHolder
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        CustomViewHolder viewHolder = CustomViewHolder.getViewHolder(convertView,mContext,parent,R.layout.list_student);
//        TextView tv_list_id = viewHolder.getView(R.id.tv_list_id);
//        TextView tv_list_name = viewHolder.getView(R.id.tv_list_name);
//        TextView tv_list_point = viewHolder.getView(R.id.tv_list_point);
//
//        tv_list_id.setText(mData.get(position).getId()+" ");
//        tv_list_name.setText(mData.get(position).getName());
//        tv_list_point.setText(mData.get(position).getPoint()+"");
//
//        return viewHolder.getConvertView();
//    }


    /**
     * 进一步简化customAdapter
     * @param viewHolder
     * @param position
     */

//    @Override
//    public void convert(CustomViewHolder viewHolder, int position) {
//
//        TextView tv_list_id = viewHolder.getView(R.id.tv_list_id);
//        TextView tv_list_name = viewHolder.getView(R.id.tv_list_name);
//        TextView tv_list_point = viewHolder.getView(R.id.tv_list_point);
//
//        tv_list_id.setText(mData.get(position).getId()+" ");
//        tv_list_name.setText(mData.get(position).getName());
//        tv_list_point.setText(mData.get(position).getPoint()+"");
//
//    }

    /**
     * 最后简化ViewHolder
     */
    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        viewHolder.setText(mData.get(position).getId()+"",R.id.tv_list_id);
        viewHolder.setText(mData.get(position).getName(),R.id.tv_list_name);
        viewHolder.setText(mData.get(position).getPoint()+"",R.id.tv_list_point);
    }
}
