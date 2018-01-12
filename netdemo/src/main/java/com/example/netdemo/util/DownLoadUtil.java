package com.example.netdemo.util;

import android.util.Log;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownLoadUtil {

    private int total_length;
    private int current_length;
    private String str_url;
    public int length = 0;

    private int start_length;
    private int down_length;
    private String targetFile;

    private MyThread[] thread;

    public DownLoadUtil(int total_length, int current_length, String str_url) {
        this.total_length = total_length;
        this.current_length = current_length;
        this.str_url = str_url;

        targetFile = "/mnt/sdcard/a.jpg";
        thread = new MyThread[10];
    }

    public void downLoad() throws IOException {

        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        file.setLength(total_length);
        file.close();

        for (int i = 0; i < 10; i++) {
            start_length = (total_length / 10) * i;
            down_length = total_length / 10;

            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");

                randomAccessFile.seek(start_length);
                thread[i] = new MyThread(start_length, down_length, randomAccessFile);
                thread[i].start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public int getLength() {

        int length = 0;
        for (int i = 0; i < thread.length; i++) {
            length = thread[i].length + length;
        }

        Log.d("tang",length+"=========================");


        return length;
    }


    class MyThread extends Thread {

        public  int length = 0;
        private int start_length;
        private int download_length;
        private RandomAccessFile randomAccessFile;

        public MyThread(int start_length, int download_length, RandomAccessFile randomAccessFile) {
            this.start_length = start_length;
            this.download_length = download_length;
            this.randomAccessFile = randomAccessFile;
        }


        @Override
        public void run() {
            super.run();

            try {
                URL url = new URL(str_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setConnectTimeout(10000);
                conn.connect();

                InputStream inputStream = conn.getInputStream();


                //   inputStream.skip(start_length);
                skipFully(inputStream, start_length);

                int len = 0;
                byte[] bytes = new byte[512];

                while ((len = inputStream.read(bytes)) != -1 && (length <= download_length)) {
                    randomAccessFile.write(bytes, 0, len);
                    length = length + len;

         //           Log.d("tang",length+"----------------------------"+this.getName());
                }

                inputStream.close();
                randomAccessFile.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void skipFully(InputStream in, long bytes) throws IOException {
        long remainning = bytes;
        long len = 0;
        while (remainning > 0) {

            len = in.skip(remainning);
            remainning -= len;
        }

    }
}
