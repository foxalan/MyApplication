package com.example.mybulter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.mybulter.R;
import com.example.mybulter.adapter.AlbumAdapter;
import com.example.mybulter.info.GirlInfo;
import com.example.mybulter.view.CustomDialog;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;


public class AlbumFragment extends Fragment {

    private GridView gv_album;

    private String welfare = null;
    private String url;

    private List<GirlInfo> girlList = new ArrayList<>();
    private AlbumAdapter adapter;

    private CustomDialog girlDialog;

    private PhotoViewAttacher mAttacher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album,null);

        initViews(view);
        initData();
        initEvent();

        return view;
    }


    private void initViews(View view) {
        gv_album = (GridView) view.findViewById(R.id.gv_album);
    }


    private void initData() {
        try {
            welfare = URLEncoder.encode(getString(R.string.text_welfare),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url ="http://gank.io/api/search/query/listview/category/"+welfare+"/count/50/page/1";

        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                getWelfare(t);
            }
        });
    }


    /**
     *
     * @param t
     */
    private void getWelfare(String t) {

        try {
            JSONObject jsonobject = new JSONObject(t);
            JSONArray jsonArray = jsonobject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");
                String name = json.getString("who");

                GirlInfo info = new GirlInfo(name,url);
                girlList.add(info);
            }

            adapter = new AlbumAdapter(getActivity(),girlList);
            gv_album.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initEvent(){
        gv_album.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView iv_girl = (ImageView) view.findViewById(R.id.iv_list_image);

                girlDialog = CustomDialog.createGirlDialog(getContext());
                ImageView iv = (ImageView) girlDialog.findViewById(R.id.iv_dialog_image);
                iv.setImageDrawable(iv_girl.getDrawable());
                mAttacher = new PhotoViewAttacher(iv);
                mAttacher.update();
                girlDialog.show();
            }
        });
    }

}
