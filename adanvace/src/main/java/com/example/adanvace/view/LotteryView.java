package com.example.adanvace.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.example.adanvace.R;
import com.example.adanvace.util.L;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Function : 自定义抽奖轮盘
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO 1.文字居中
 * Whether solve :
 */

public class LotteryView extends View {


    private int radius_pic;
    private int radius_center;
    private int radius_lottery;

    private Paint paint;


    private int centerX = 0;
    private int centerY = 0;

    private RectF rect_pic;


    private List<Bitmap> bitmapList = new ArrayList<>();
    private String[] titles = {"单反相机", "再来一次", "恭喜发财", "IPAD", "IPHONE", "妹子一只"};

    private Bitmap bitmap_bg;
    private int radius_bg;

    private Bitmap bitmap_arrow_start;

    private Matrix matricx = new Matrix();

    private RectF rectf;

    public LotteryView(Context context) {
        this(context, null);
    }

    public LotteryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LotteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        initPaint();
        initBitmap();

    }

    /**
     * 初始化画笔
     */

    private void initPaint() {

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(50);

    }

    /**
     * 初始化图片
     */

    private void initBitmap() {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.danfan);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.f015);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.f040);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.ipad);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.iphone);
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);

        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
        bitmapList.add(bitmap4);
        bitmapList.add(bitmap5);
        bitmapList.add(bitmap6);


        bitmap_bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
        bitmap_arrow_start = BitmapFactory.decodeResource(getResources(), R.drawable.start);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        initRadius();
        initRect();


        setMeasuredDimension(900, 900);
    }

    /**
     * 初始化三个圆的半径
     */
    private void initRadius() {

        radius_bg = 400;
        radius_pic = 350;
        radius_lottery = 250;
        radius_center = 100;

        centerX = 450;
        centerY = 450;
    }

    private void initRect() {


        rect_pic = new RectF(centerX - radius_pic, centerY - radius_pic, centerX + radius_pic, centerY + radius_pic);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawPic(canvas);

        drawWare(canvas);

        drawText(canvas);

        drawArrow(canvas);

    }

    /**
     * 画最外面的背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {

        RectF rectf = new RectF(centerX - radius_bg, centerY - radius_bg, centerX + radius_bg, centerY + radius_bg);
        canvas.drawBitmap(bitmap_bg, null, rectf, paint);


    }

    /**
     * 画外面的图片圆
     *
     * @param canvas
     */
    private void drawPic(Canvas canvas) {

        canvas.drawCircle(centerX, centerY, radius_pic, paint);

        for (int i = 0; i < 6; i++) {
            canvas.drawArc(rect_pic, i * 60, 60, true, paint);
        }


    }

    private void drawWare(Canvas canvas) {

        RectF rect;

        for (int i = 0; i < 6; i++) {

            double hude = Math.PI * (30.0 + 60 * i) / 180.0;

            int y = (int) (Math.sin(hude) * radius_pic) / 2;
            int x = (int) (Math.cos(hude) * radius_pic) / 2;
            int pic_x = x + centerX;
            int pic_y = y + centerY;

            Log.d("TANG", "x=" + x);
            Log.d("TANG", "y=" + y);

            rect = new RectF(pic_x - radius_pic / 6, pic_y - radius_pic / 6, pic_x + radius_pic / 6, pic_y + radius_pic / 6);

            //    canvas.drawRect(rect, paint);
            canvas.drawBitmap(bitmapList.get(i), null, rect, paint);


        }

    }

    /**
     * 画文字
     */

    private void drawText(Canvas canvas) {

        RectF rectf = new RectF(centerX - 3 * radius_pic / 4, centerY - 3 * radius_pic / 4, centerX + 3 * radius_pic / 4, centerY + 3 * radius_pic / 4);

        Path path;
        for (int i = 0; i < 6; i++) {
            path = new Path();
            path.addArc(rectf, 10 + i * 60, 40);
            //    canvas.drawPath(path, paint);
            canvas.drawTextOnPath(titles[i], path, 0, 0, paint);
        }

    }

    /**
     * 画中奖指针
     *
     * @param canvas
     */
    private void drawArrow(Canvas canvas) {

        rectf = new RectF(centerX - radius_center, centerY - 2 * radius_center, centerX + radius_center, centerY + 2 * radius_center);
        canvas.drawRect(rectf, paint);
    //    canvas.drawBitmap(bitmap_arrow_start, null, rectf, paint);
    //    canvas.drawRect(new RectF(centerX - 80, centerY - 80, centerX + 80, centerY + 80), paint);


        //    canvas.drawBitmap(bitmap_arrow_start,matricx,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        int m = Math.abs(x - centerX);
        int n = Math.abs(y - centerY);

        if (m < 80 && n < 80) {
            L.d("ARROW");
            startArrow();
        }


        return super.onTouchEvent(event);
    }

    /**
     * 开始旋转
     */
    private void startArrow() {


        //    ObjectAnimator anim = ObjectAnimator.ofFloat(bitmap_arrow_start,"rotation",0f,300f);

    }
}
