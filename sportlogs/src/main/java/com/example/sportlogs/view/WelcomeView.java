package com.example.sportlogs.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.example.sportlogs.util.L;

/**
 * Function : 欢迎界面
 * Author : Alan
 * Modify Date : 28/8/17
 * Issue : TODO
 * Whether solve :
 */

public class WelcomeView extends View {

    private int mScreenWidth;
    private int mScreenHeight;


    private int radius_clock;
    private RectF rect_clock = new RectF();
    private RectF rect_text = new RectF();

    private Paint paint;
    private Paint paint_text;

    private int center_x;
    private int center_y;

    private int radius_mid;
    private int radius_small;

    private String text = "loading";

    private int spaceing = 20;
    private int radiu_point = 10;
    private RectF rectF_a = new RectF();
    private RectF rectF_b = new RectF();
    private RectF rectF_c = new RectF();


    public WelcomeView(Context context) {
        this(context, null);
    }

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WelcomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initScreenLength(context);
        initPaint();
        L.d("Create");

        new Thread(){
            @Override
            public void run() {
                super.run();

            }
        };

    }


    private void initPaint() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setTextSize(25);

        paint_text = new Paint();
        paint_text.setAntiAlias(true);
        paint_text.setTextSize(175);
        paint_text.setStrokeWidth(2);
        paint_text.setStyle(Paint.Style.STROKE);
        paint_text.setColor(Color.WHITE);
        paint_text.setTextAlign(Paint.Align.RIGHT);

    }


    private void initScreenLength(Context context) {

        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        window.getDefaultDisplay().getMetrics(metrics);

        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        initRadius();
        L.d("onMeasure" + mScreenWidth + ":" + mScreenHeight);

        setMeasuredDimension(mScreenWidth, mScreenHeight);
    }

    private void initRadius() {

        center_x = mScreenWidth / 2;
        center_y = mScreenHeight / 3;

        radius_clock = center_x / 2;

        radius_mid = radius_clock / 2;
        radius_small = radius_mid / 4;

        rect_clock.set(center_x - radius_clock, center_y - radius_clock, center_x + radius_clock, center_y + radius_clock);

        int x = center_x + 250;
        int y = center_y * 2;
        int radius = (int) (radius_clock * 1.5);
        rect_text.set(x - radius, y - radius, x + radius, y + radius);

        Paint.FontMetricsInt fontMetrics = paint_text.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        baseline = (int) ((rect_text.bottom + rect_text.top - fontMetrics.bottom - fontMetrics.top) / 2);
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()

        int point_x = (int) rect_text.centerX() + 20;
        int point_y = baseline;
        rectF_a.set(point_x - radiu_point, point_y - radiu_point, point_x + radiu_point, point_y + radiu_point);
        int point_x_b = point_x + 60;

        int point_x_c = point_x_b + 60;

        rectF_b.set(point_x_b - radiu_point, point_y - radiu_point, point_x_b + radiu_point, point_y + radiu_point);
        rectF_c.set(point_x_c - radiu_point, point_y - radiu_point, point_x_c + radiu_point, point_y + radiu_point);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        L.d("onDraw");
        drawRound(canvas);
        drawNumber(canvas);
    }


    private void drawRound(Canvas canvas) {

        canvas.drawCircle(center_x, center_y, radius_clock, paint);
        canvas.drawCircle(center_x, center_y, radius_mid, paint);
        canvas.drawCircle(center_x, center_y, radius_small, paint);

        int right_x = (int) (center_x + radius_small * Math.cos(Math.PI * 30 / 180));
        int right_y = (int) (center_y + radius_small * Math.sin(Math.PI * 30 / 180));
        int left_x = (int) (center_x - radius_small * Math.cos(Math.PI * 30 / 180));

        int nor_x = (int) (center_x + radius_clock * Math.cos(Math.PI * 30 / 180));
        int nor_y = (int) (center_y - radius_clock * Math.sin(Math.PI * 30 / 180));
        int nor_x_left = (int) (center_x - radius_clock * Math.cos(Math.PI * 30 / 180));


        Path path1 = new Path();
        path1.moveTo(center_x, center_y - radius_small);
        path1.lineTo(nor_x, nor_y);
        path1.lineTo(right_x, right_y);
        path1.close();

        Path path2 = new Path();
        path2.moveTo(right_x, right_y);
        path2.lineTo(center_x, center_y + radius_clock);
        path2.lineTo(left_x, right_y);
        path2.close();

        Path path3 = new Path();
        path3.moveTo(center_x, center_y - radius_small);
        path3.lineTo(nor_x_left, nor_y);
        path3.lineTo(left_x, right_y);
        path3.close();

        canvas.drawPath(path1, paint);
        canvas.drawPath(path2, paint);
        canvas.drawPath(path3, paint);

    }

    private int baseline;

    private void drawNumber(Canvas canvas) {

    //    canvas.drawRect(rect_text, paint_text);
        canvas.drawText(text, rect_text.centerX(), baseline, paint_text);

        canvas.drawRect(rectF_a, paint_text);
        canvas.drawRect(rectF_b, paint_text);
        canvas.drawRect(rectF_c, paint_text);


    }
}
