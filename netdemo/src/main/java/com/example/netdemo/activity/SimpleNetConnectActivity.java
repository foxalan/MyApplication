package com.example.netdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netdemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class SimpleNetConnectActivity extends Activity {


    private EditText tv_url;
    private EditText tv_data;
    private Button bt_send;
    private ImageView iv_pic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_net);

        initViews();
        initEvents();
    }

    /**
     * 先判断是否有网
     */
    private void initEvents() {

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                } else {
                    Toast.makeText(SimpleNetConnectActivity.this,"請检查网络",Toast.LENGTH_LONG).show();
                    return;
                }

                if (tv_url.getText().toString().equals("")){
                    Toast.makeText(SimpleNetConnectActivity.this,"請输入网址",Toast.LENGTH_LONG).show();
                    return;
                }

                NetSignalTask task = new NetSignalTask();
                task.execute(tv_url.getText().toString());

                Log.d("tang",tv_url.getText().toString());

            }
        });
    }

    private void initViews() {

        tv_url = (EditText) findViewById(R.id.tv_url);
        tv_data = (EditText) findViewById(R.id.tv_data);
        bt_send = (Button) findViewById(R.id.bt_send);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);

    }

    /**
     * AsyncTask<String,Void,Integer>
     *     doInBackground 实际的操作者
     *     1.url的格式是否正确
     *
     *     2.从网络上获取图片
     */


    class NetSignalTask extends AsyncTask<String,Void,String >{


        @Override
        protected String doInBackground(String... strings) {
            String string = null;

            String url_pic = "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg";


            try {

                URL url = new URL(url_pic);
                Log.d("tang",strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
                int response = httpURLConnection.getResponseCode();
                Log.d("tang", "The response is: " + response);

                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_pic.setImageBitmap(bitmap);
                    }
                });
                byte[] bytes = new byte[512];
                int len = 0;


                while ((len = inputStream.read(bytes))!= -1){
                    string = new String(bytes,0,len);
                }

                inputStream.close();



            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("tang","urlexcepetion");
            } catch (IOException e) {
                Log.d("tang","url_excepetion");

                e.printStackTrace();
            }


            return string;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            tv_data.setText(s);
        }
    }

}
