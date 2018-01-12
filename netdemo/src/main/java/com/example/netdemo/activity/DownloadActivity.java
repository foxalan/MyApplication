package com.example.netdemo.activity;

import android.app.Activity;
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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.netdemo.R;
import com.example.netdemo.util.DownLoadUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class DownloadActivity extends Activity {

    private EditText et_dl;
    private ProgressBar sb_dl;
    private Button bt_dl;

    private String str = "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg" ;
    private int total_length;
    private InputStream inputStream;
    private int current_length;
    private DownLoadUtil download;
    private Timer timer;



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){

            //    int d = (current_length/total_length)*100 ;

                double v = current_length * 1.0 / total_length * 1.0;

                int d = (int) (v * 100);

                Log.d("tang",current_length+"----");
                Log.d("tang",d+"===========");

                sb_dl.setProgress(d);
            }

            if (msg.what == 0x124){
                int i = download.getLength();

                double v = i * 1.0 / 446470 * 1.0;

                int d = (int) (v * 100);

                Log.d("tang",total_length+"----"+i);
                Log.d("tang",d+"===========");

                if (d == 100){
                    timer.cancel();
                }

                sb_dl.setProgress(d);


            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        initViews();
        initEvent();
    }

    private void initViews() {

        et_dl = (EditText) findViewById(R.id.et_download);
        sb_dl = (ProgressBar) findViewById(R.id.sb_dl);
        bt_dl = (Button) findViewById(R.id.bt_dl);

        download = new DownLoadUtil(446470,0,str);
    }

    private void initEvent(){
        bt_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();

                if (info == null || !info.isConnected()){
                    Toast.makeText(DownloadActivity.this,"请检查网络情况",Toast.LENGTH_LONG).show();
                }else {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            /**
                             * 单线程下载                            */

                   //         startConnect();

                            /**
                             * 多线程下
                             *
                             * */

                            try {
                                artMoreConnect();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                }

            }
        });
    }

    private void artMoreConnect() throws IOException {


        download.downLoad();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0x124;
                mHandler.sendMessageDelayed(message,50);
            }
        },0,50);



    }

    private void startConnect() {



        try {
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();

            if (response == 200){
                total_length = conn.getContentLength();
                Log.d("tang","文件总大小:"+total_length);

                inputStream = conn.getInputStream();

                int len = 0;
                byte[] bytes = new byte[512];

                while ((len = inputStream.read(bytes))!=-1){
                    current_length = current_length + len;

                    Message message = new Message();
                    message.what = 0x123;
                    mHandler.sendMessageDelayed(message,1000);

                }
                inputStream.close();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("tang","malformed");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("tang","ioe");
        }

    }
}
