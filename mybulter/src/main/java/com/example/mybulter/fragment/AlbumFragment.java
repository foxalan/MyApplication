package com.example.mybulter.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.kymjs.rxvolley.http.VolleyError;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.co.senab.photoview.PhotoViewAttacher;


public class AlbumFragment extends Fragment {

    private static AlbumFragment albumFragment;

    private GridView gv_album;

    private String welfare = null;
    private String url;

    private List<GirlInfo> girlList = new ArrayList<>();
    private AlbumAdapter adapter;

    private CustomDialog girlDialog;

    private PhotoViewAttacher mAttacher;
    private ExecutorService service;

    public static AlbumFragment getInstance() {
        if (albumFragment == null) {
            albumFragment = new AlbumFragment();
        }

        return albumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        initViews(view);
        initData();
        initEvent();

        return view;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x123:
                    getWelfare();
                    break;

                default:
                    break;
            }
        }
    };


    private void initViews(View view) {

        service = Executors.newSingleThreadExecutor();
        gv_album = (GridView) view.findViewById(R.id.gv_album);
    }


    private void initData() {
        try {
            welfare = URLEncoder.encode(getString(R.string.text_welfare), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //url = "http://gank.io/api/search/query/listview/category/" + welfare + "/count/50/page/1";
     //   url = "http://www.mzitu.com/";
    //    url = "http://www.narutom.com/pic/naruto_pic/15502.html";
        url = "http://www.mmjpg.com/";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                service.execute(new Runnable() {
                    @Override
                    public void run() {

                        Document documented = null;
                        try {
                            documented = Jsoup.connect(url).get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Elements elements = documented.select("div.pic").select("img");
                        for (int i = 0; i < elements.size(); i++) {
                            String name = elements.get(i).attr("alt");
                            String url = elements.get(i).attr("src");
                            GirlInfo info = new GirlInfo(name, url);
                            girlList.add(info);
                        }
                        mHandler.sendEmptyMessage(0x123);
                    }
                });
            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);

            }
        });
    }


    /**
     * @param
     */
    private void getWelfare() {
        adapter = new AlbumAdapter(getActivity(), girlList);
        gv_album.setAdapter(adapter);
    }

    private void initEvent() {
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
