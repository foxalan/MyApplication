package com.example.netdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.netdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class CacheActivity extends Activity {

    private EditText et_cache_net;
    private Button bt_cache_send;
    private ImageView iv_cache_pic;
    private Bitmap bitmap;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                Bitmap map = (Bitmap) msg.obj;
                iv_cache_pic.setImageBitmap(map);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);


        initViews();
        initEvent();

    }

    private void initEvent() {
        bt_cache_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


                /**
                 *  是否是WIFI还是NET            */

                NetworkInfo network = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                boolean isWifiConn = network.isConnected();
                network = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                boolean isMobileConn = networkInfo.isConnected();


                if (networkInfo != null && networkInfo.isConnected()) {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            bitmap = getBitmap();
                        }
                    }.start();
                } else {
                    Toast.makeText(CacheActivity.this,"請检查网络",Toast.LENGTH_LONG).show();
                    return;
                }

                Message message = new Message();
                message.what = 0x123;
                message.obj = bitmap;
                mHandler.sendMessage(message);

            }
        });

    }

    private Bitmap getBitmap() {
        String net = "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg";
        URL url = null;
        Log.d("tang","getBitmap1");
        Bitmap map  = null;
        try {
            url = new URL(net);
            Log.d("tang","getBitmap2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();
            InputStream inputstream = conn.getInputStream();


            File file = new File(getFilesDir(),"my.jpg");
            Log.d("tang",file.getAbsolutePath());

            FileOutputStream outputStream = new FileOutputStream(file);

            Log.d("tang",conn.getResponseCode()+"code");
            if (conn.getResponseCode() == 200){
                byte[] bt = new byte[512];
                int len = 0;
                while ((len = inputstream.read(bt))!= -1){

                    outputStream.write(bt,0,len);
                }

            }
            map  = BitmapFactory.decodeFile(file.getAbsolutePath());

            inputstream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("tang","malformed");
        } catch (FileNotFoundException e) {
            Log.d("tang","filenotfound");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.d("tang","notfound");

            e.printStackTrace();
        } catch (IOException e) {
            Log.d("tang","ioexception");

            e.printStackTrace();
        }


        return map;

    }

    private void initViews() {

        et_cache_net = (EditText) findViewById(R.id.et_cache_net);
        bt_cache_send = (Button) findViewById(R.id.bt_cache_send);
        iv_cache_pic = (ImageView) findViewById(R.id.iv_cache_pic);
    }

    public String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }




}
