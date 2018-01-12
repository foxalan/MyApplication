package com.example.adanvace.viewgroup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.adanvace.R;
import com.example.adanvace.util.ImagePiece;
import com.example.adanvace.util.ImageSplitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 24/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JigsawView extends ViewGroup {

    private int mWidth;
    private int mHeight;

    private int paddingLeft = 50;
    private int itemPadding = 5;

    private int itemWidth;

    private int currentCount = 3;

    private int mScreenWidth;

    private Bitmap bitmap_game;
    private List<ImagePiece> imagePieceList = new ArrayList<>();

    public JigsawView(Context context) {
        this(context, null);
    }

    public JigsawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JigsawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);

        mScreenWidth = metrics.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = mScreenWidth - 2 * paddingLeft;

        itemWidth = (mWidth - itemPadding * (currentCount - 1)) / currentCount;

        mHeight = mWidth;

        initBitmap();
        initItem();

        setMeasuredDimension(mWidth, mHeight);
    }

    private void initBitmap() {

        bitmap_game = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        imagePieceList = ImageSplitter.split(bitmap_game, currentCount);
    }

    private void initItem() {
        for (int i = 0; i < 1; i++) {

         //   RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(itemWidth, itemWidth);

//            layoutParam.addRule(RelativeLayout.RIGHT_OF);
            ImageView image = new ImageView(getContext());
        //    layoutParam.width = itemWidth;
        //    layoutParam.height = itemWidth;
            Log.d("TANG", itemWidth + " itemWidth");

            image.setImageBitmap(imagePieceList.get(i).bitmap);

            addView(image);

        }
    }


}
