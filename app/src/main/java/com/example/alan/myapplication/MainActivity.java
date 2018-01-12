package com.example.alan.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 存储数据的三种方式
 * 1.SharePreferences
 * 2.file
 * 3.database
 */

public class MainActivity extends AppCompatActivity {

    private SharePreferencesUtil sharePreferencesUtil;
    private String SHARED_KEY = "alan";
    private String SHARED_VALUE = "faker";
    private static final String TAG = "tang";

    private DBHelper dbHelper;

    private String url = "https://seller.kuaimayiliao.com/seller/order/fc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePreferencesUtil = new SharePreferencesUtil(this);
        sharePreferencesUtil.writeSting(SHARED_KEY, SHARED_VALUE);

        /**
         * ==============================================================================
         * 文件存储
         * 1.添加读写权限
         * 2.FileOutputStream 是文件的写入流 字节流
         * 3.写完后要关闭流
         */

        //   File file = new File(getFilesDir(),"alan.txt");
        //   File file = new File("/storage/emulated/0/alan.txt");
        File file = new File(getCacheDir(), "alan.txt");
        FileOutputStream outputStream;
        FileInputStream fileInputStream;


        Log.d(TAG, file.getAbsolutePath() + "==================================='");

        String string = "国粞无粮 ,天下飘香,thank for all ";

        try {
            outputStream = openFileOutput(file.getName(), MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferReader;
        try {
            FileReader reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            fileInputStream = openFileInput(file.getName());
            bufferReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
            while ((line = bufferReader.readLine()) != null) {
                Log.d(TAG, line);
            }

            fileInputStream.close();
            bufferReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        byte[] b = new byte[16];
//        int len = 0;
//        try {
//            BufferedInputStream burrerStream = new BufferedInputStream(openFileInput(file.getName()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


//        try {
//            fileInputStream = openFileInput(file.getName());
//            while ((len= fileInputStream.read(b))!=-1){
//                String str = new String(b,0,len);
//                Log.d(TAG,str);
//            }
//            fileInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        /**
//         *
//         * =================================================================================
//         * 数据库存储
//         */
//        dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("value","20170628");
//        contentValues.put("content","run 1700");
//        dbHelper.insert(contentValues,database);
//
//        String[] date = {"20170628"};
//
//        Cursor cursor = dbHelper.query(database,date);
//        cursor.moveToFirst();
//        if (cursor == null){
//            Log.d(TAG,"获取失败");
//        }else {
//                String str =cursor.getString(cursor.getColumnIndex("content"));
//            Log.d(TAG,str+"-------------------------------------------");
//        }


        final HashMap paramMap = new HashMap();
        paramMap.put("mid", "1704100050");
        paramMap.put("t", "15");

        new Thread() {
            @Override
            public void run() {
                super.run();

                //    commonRequest(paramMap,url);//
                String str = submitPostData(url, paramMap, "utf-8");
                Log.d("ALAN", str);
            }
        }.start();


    }







    public static String submitPostData(String strUrlPath, Map<String, String> params, String encode) {

        byte[] data = getRequestData(params, encode).toString().getBytes();//获得请求体
        try {

            //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
            URL url = new URL(strUrlPath);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }

    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }


}
