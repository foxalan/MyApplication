package com.example.adanvace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.info.ContactInfo;

import java.util.List;

/**
 * Function : 联系人ListView的Adapter
 * Author : Alan
 * Modify Date : 9/8/17
 * Issue : TODO
 * Whether solve :
 */

public class ContactAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ContactInfo> contactInfoList;

    public ContactAdapter(Context context, List<ContactInfo> contactInfoList) {
        inflater = LayoutInflater.from(context);
        this.contactInfoList = contactInfoList;
    }

    @Override
    public int getCount() {
        return contactInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_contact, null);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_list_contact);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_list_contact_name);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_list_contact_number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (contactInfoList.get(position).isShow()) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        viewHolder.checkBox.setChecked(contactInfoList.get(position).isDelete());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contactInfoList.get(position).setDelete(isChecked);
            }
        });

        viewHolder.tv_name.setText(contactInfoList.get(position).getName());
        viewHolder.tv_number.setText(contactInfoList.get(position).getNumber());

        return convertView;
    }

    class ViewHolder {
        CheckBox checkBox;
        TextView tv_name;
        TextView tv_number;
    }
}
