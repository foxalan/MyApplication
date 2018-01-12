package com.example.audiotest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_photo = (ImageView) findViewById(R.id.iv_photo);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_photo:

                getPhoto();

                break;
            default:
                break;
        }
    }

    /**
     * MediaStore.ACTION_IMAGE_CAPTURE
     * capture:夺取，捕获;
     */

    private void getPhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                iv_photo.setImageBitmap(imageBitmap);
            }
        }

    }

    /**
     * 图片压缩处理
     */
    private BitmapFactory.Options getBitmapOption(int inSampleSize) {
        //    System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }


}
